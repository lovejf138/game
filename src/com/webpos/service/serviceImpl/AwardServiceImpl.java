package com.webpos.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.webpos.dao.AwardMapper;
import com.webpos.dao.DetailMapper;
import com.webpos.dao.RoomMapper;
import com.webpos.dao.TestDetailMapper;
import com.webpos.dao.UserMapper;
import com.webpos.entity.Award;
import com.webpos.entity.Detail;
import com.webpos.entity.TestDetail;
import com.webpos.entity.User;
import com.webpos.service.AwardService;
import com.webpos.tools.CommUtil;

@Service
public class AwardServiceImpl implements AwardService {
	@Resource
	private AwardMapper awardDao;
	@Resource
	private RoomMapper roomDao;
	@Resource
	private DetailMapper detailDao;
	@Resource
	private TestDetailMapper testdetailDao;
	@Resource
	private UserMapper userDao;

	public int insert(Award record) {
		return this.awardDao.insert(record);
	}

	public List<Award> getLastList(int size) {
		return this.awardDao.getLastList(size);
	}

	public Award getLast() {
		return this.awardDao.getLast();
	}

	public Award getByName(String name) {
		return this.awardDao.getByName(name);
	}

	/**
	 * 
	 * @param finals
	 * @param qiname
	 * @param ifJiesuan 是否结算
	 * @return
	 */
	@Transactional
	public String kaijiang(int[] finals, String qiname, boolean ifJiesuan) {
		try {
			/************ 添加开奖记录 ***************/
			Award a = new Award();
			String t = CommUtil.getTimeFromQishu(qiname);
			Date d = CommUtil.formatDate(t, "yyyy-MM-dd HH:mm:ss");
			a.setCtime(d);
			a.setName(qiname);
			a.setNo1(finals[0]);
			a.setNo2(finals[1]);
			a.setNo3(finals[2]);
			a.setNo4(finals[3]);
			a.setNo5(finals[4]);
			a.setNo6(finals[5]);
			a.setNo7(finals[6]);
			a.setNo8(finals[7]);
			a.setNo9(finals[8]);
			a.setNo10(finals[9]);
			a.setNo11(finals[10]);
			awardDao.insert(a);
			if (!ifJiesuan) {
				return "SUCCESS";
			}

			/**************** 取出押注的详情 ***********************/
			List<Detail> ds = detailDao.selectByQiname(qiname);
			if (ds == null || ds.size() <= 0) {
				return "SUCCESS";
			} else {
				Map<Integer, List<Detail>> map_detail = new HashMap<Integer, List<Detail>>();
				Integer sum_all_amount = 0;
				// List<SumDetail> sumdetail_list = new ArrayList<SumDetail>();//存放每个号码的合计
				for (int i = 1; i <= 11; i++) {
					List<Detail> map_ds = new ArrayList<Detail>();
					List<Detail> temp_ds = new ArrayList<Detail>();
					for (Detail t_d : ds) {

						if (t_d.getNumber() == i) {//
							sum_all_amount = sum_all_amount + t_d.getAmount();
							map_ds.add(t_d);
							temp_ds.add(t_d);
						} else {
							break;// 数据库取出来就已经按顺序排了
						}
					}
					map_detail.put(i, map_ds);
					ds.removeAll(temp_ds);
				}

				/* ————————————————输出测试—————————————————————————— */
//				for(int i=1;i<=11;i++) {
//					List<Detail> out_detail = map_detail.get(i);
//					System.out.println("number:"+i+"---------------------");
//					for(Detail ad:out_detail) {
//						if(ad.getAmount()>0) {
//						System.out.println("id:"+ad.getId()+",user:"+ad.getUserid()+",amount:"+ad.getAmount());
//						}
//					}
//				}

				List<Detail> result_details = new ArrayList<Detail>();// 存放计算结果

				List<Detail> first_numberdetail = new ArrayList<Detail>();
				int first_number = 1;
				/*************** 获取第一个 ******************/
				for (int i = 0; i <= 10; i++) {
					int kj_number = finals[i];

					List<Detail> out_detail = map_detail.get(kj_number);
					if (out_detail.size() <= 0) {
						continue;
					} else {
						first_numberdetail.addAll(out_detail);
						first_number = i;
						break;
					}

				}

				/*************** 计算第一个 ******************/
				for (Detail first_detail : first_numberdetail) {
					Integer amount = first_detail.getAmount();
					sum_all_amount = sum_all_amount - amount;
					first_detail.setAward(amount);
				}

				for (Detail first_detail : first_numberdetail) {
					Integer amount = first_detail.getAmount();
					Integer award = first_detail.getAward();
					// System.out.println("award:"+award);
					if (sum_all_amount <= 0) {

					} else if (sum_all_amount > amount) {
						sum_all_amount = sum_all_amount - amount;
						first_detail.setAward(award + amount);
					} else {
						first_detail.setAward(award + sum_all_amount);
						sum_all_amount = sum_all_amount - amount;
					}

					result_details.add(first_detail);
				}
				/*************** 结束第一个计算 ******************/

				// System.out.println("sum_all_amount:"+sum_all_amount);

				/*************** 计算后面 ******************/
				for (int i = first_number + 1; i <= 10; i++) {
					if (sum_all_amount <= 0) {
						break;
					}
					int kj_number = finals[i];

					// System.out.println("i:"+i+",kj_number:"+kj_number+",sum_all_amount:"+sum_all_amount);

					List<Detail> out_detail = map_detail.get(kj_number);
					if (out_detail.size() <= 0) {
						continue;
					} else {

						for (Detail other_detail : out_detail) {

							Integer amount = other_detail.getAmount();
							Integer doubleamount = amount + amount;
							// Double award = other_detail.getAward();
							if (sum_all_amount <= 0) {
								break;
							} else if (sum_all_amount > doubleamount) {
								sum_all_amount = sum_all_amount - doubleamount;
								// System.out.println("sum_d:"+sum_all_amount);
								other_detail.setAward(doubleamount);
								result_details.add(other_detail);
							} else {

								other_detail.setAward(sum_all_amount);
								// System.out.println("sum_d:"+0);
								sum_all_amount = 0;
								result_details.add(other_detail);
								break;
							}
						}
					}

				}
				/*************** 计算后面结束 ******************/

				/******************* 写入到数据库 *************************/
				for (Detail ad : result_details) {
//						System.out.println("id:"+ad.getId()+"number:"+ad.getNumber()+",user:"+ad.getUserid()+",amount:"+ad.getAmount()
//						+",award:"+ad.getAward());

					if (ad.getAward() <= 0) {
						continue;
					} else if (ad.getAward() <= ad.getAmount()) {
						User u = userDao.selectByUserId(ad.getUserid());
						u.setBalance(u.getBalance() + ad.getAward());
						u.setAward_sum(u.getAward_sum() + ad.getAward());
						ad.setFinalaward(ad.getAward());
						userDao.updateByPrimaryKeySelective(u);
					} else {

						Integer yingli = ad.getAward() - ad.getAmount();
						Integer lirun = (new Double(CommUtil.mul(yingli, 0.03))).intValue();
						Integer prun = (new Double(CommUtil.mul(yingli, 0.005))).intValue();
						Integer final_award = ad.getAward() - lirun;
						ad.setFinalaward(final_award);
						ad.setParentaward(prun);
						User u = userDao.selectByUserId(ad.getUserid());
						u.setBalance(u.getBalance() + final_award);
						u.setAward_sum(u.getAward_sum() + ad.getAward());
						userDao.updateByPrimaryKeySelective(u);

						if (u.getParent() != null && !u.getParent().equals("")) {
							User up = userDao.selectByUserId(u.getParent());
							up.setBalance(up.getBalance() + prun);
							userDao.updateByPrimaryKeySelective(up);
						}

					}
					detailDao.updateByPrimaryKeySelective(ad);

				}
			}
			/************* 初始化房间 ********************/
			// roomDao.initRoom(null);
			Detail temp = new Detail();
			temp.setKjnumber(
					finals[0] + "," + finals[1] + "," + finals[2] + "," + finals[3] + "," + finals[4] + "," + finals[5]
							+ "," + finals[6] + "," + finals[7] + "," + finals[8] + "," + finals[9] + "," + finals[10]);
			temp.setQiname(qiname);
			temp.setStatus("finish");
			detailDao.finishByname(temp);

			return "SUCCESS";

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "FAIL:" + e.getMessage().toString();
		}

	}

	/**
	 * 
	 * @param finals
	 * @param qiname
	 * @param ifJiesuan 是否结算
	 * @return
	 */
	@Transactional
	public String testkaijiang(int[] finals, String qiname, boolean ifJiesuan) {
		try {

			/**************** 取出押注的详情 ***********************/
			List<TestDetail> ds = testdetailDao.selectByQiname(qiname);
			if (ds == null || ds.size() <= 0) {
				return "SUCCESS";
			} else {
				Map<Integer, List<TestDetail>> map_detail = new HashMap<Integer, List<TestDetail>>();
				Integer sum_all_amount = 0;
				// List<SumDetail> sumdetail_list = new ArrayList<SumDetail>();//存放每个号码的合计
				for (int i = 1; i <= 11; i++) {
					List<TestDetail> map_ds = new ArrayList<TestDetail>();
					List<TestDetail> temp_ds = new ArrayList<TestDetail>();
					for (TestDetail t_d : ds) {

						if (t_d.getNumber() == i) {//
							sum_all_amount = sum_all_amount + t_d.getAmount();
							map_ds.add(t_d);
							temp_ds.add(t_d);
						} else {
							break;// 数据库取出来就已经按顺序排了
						}
					}
					map_detail.put(i, map_ds);
					ds.removeAll(temp_ds);
				}

				/* ————————————————输出测试—————————————————————————— */
//				for(int i=1;i<=11;i++) {
//					List<Detail> out_detail = map_detail.get(i);
//					System.out.println("number:"+i+"---------------------");
//					for(Detail ad:out_detail) {
//						if(ad.getAmount()>0) {
//						System.out.println("id:"+ad.getId()+",user:"+ad.getUserid()+",amount:"+ad.getAmount());
//						}
//					}
//				}

				List<TestDetail> result_details = new ArrayList<TestDetail>();// 存放计算结果

				List<TestDetail> first_numberdetail = new ArrayList<TestDetail>();
				int first_number = 1;
				/*************** 获取第一个 ******************/
				for (int i = 0; i <= 10; i++) {
					int kj_number = finals[i];

					List<TestDetail> out_detail = map_detail.get(kj_number);
					if (out_detail.size() <= 0) {
						continue;
					} else {
						first_numberdetail.addAll(out_detail);
						first_number = i;
						break;
					}

				}

				/*************** 计算第一个 ******************/
				for (TestDetail first_detail : first_numberdetail) {
					Integer amount = first_detail.getAmount();
					sum_all_amount = sum_all_amount - amount;
					first_detail.setAward(amount);
				}

				for (TestDetail first_detail : first_numberdetail) {
					Integer amount = first_detail.getAmount();
					Integer award = first_detail.getAward();
					// System.out.println("award:"+award);
					if (sum_all_amount <= 0) {

					} else if (sum_all_amount > amount) {
						sum_all_amount = sum_all_amount - amount;
						first_detail.setAward(award + amount);
					} else {
						first_detail.setAward(award + sum_all_amount);
						sum_all_amount = sum_all_amount - amount;
					}

					result_details.add(first_detail);
				}
				/*************** 结束第一个计算 ******************/

				// System.out.println("sum_all_amount:"+sum_all_amount);

				/*************** 计算后面 ******************/
				for (int i = first_number + 1; i <= 10; i++) {
					if (sum_all_amount <= 0) {
						break;
					}
					int kj_number = finals[i];

					// System.out.println("i:"+i+",kj_number:"+kj_number+",sum_all_amount:"+sum_all_amount);

					List<TestDetail> out_detail = map_detail.get(kj_number);
					if (out_detail.size() <= 0) {
						continue;
					} else {

						for (TestDetail other_detail : out_detail) {

							Integer amount = other_detail.getAmount();
							Integer doubleamount = amount + amount;
							// Double award = other_detail.getAward();
							if (sum_all_amount <= 0) {
								break;
							} else if (sum_all_amount > doubleamount) {
								sum_all_amount = sum_all_amount - doubleamount;
								// System.out.println("sum_d:"+sum_all_amount);
								other_detail.setAward(doubleamount);
								result_details.add(other_detail);
							} else {

								other_detail.setAward(sum_all_amount);
								// System.out.println("sum_d:"+0);
								sum_all_amount = 0;
								result_details.add(other_detail);
								break;
							}
						}
					}

				}
				/*************** 计算后面结束 ******************/

				/******************* 写入到数据库 *************************/
				for (TestDetail ad : result_details) {
//						System.out.println("id:"+ad.getId()+"number:"+ad.getNumber()+",user:"+ad.getUserid()+",amount:"+ad.getAmount()
//						+",award:"+ad.getAward());

					ad.setFinalaward(ad.getAward());
					if (ad.getAward() <= 0) {
						continue;
					}
					testdetailDao.updateByPrimaryKeySelective(ad);
				}
			}
//			/*************初始化房间********************/
//			//roomDao.initRoom(null);
//			TestDetail temp = new TestDetail();
//			temp.setKjnumber(finals[0]+","+finals[1]+","+finals[2]+","+finals[3]+","+finals[4]+","+
//					finals[5]+","+finals[6]+","+finals[7]+","+finals[8]+","+finals[9]+","+finals[10]);
//			temp.setQiname(qiname);
//			temp.setStatus("finish");
//			testdetailDao.finishByname(temp);

			return "SUCCESS";

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "FAIL:" + e.getMessage().toString();
		}

	}

}
