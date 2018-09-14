package com.webpos.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.webpos.dao.AwardMapper;
import com.webpos.dao.DetailMapper;
import com.webpos.dao.RoomMapper;
import com.webpos.dao.UserMapper;
import com.webpos.entity.Award;
import com.webpos.entity.Detail;
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
	private UserMapper userDao;

	public int insert(Award record) {
		return this.awardDao.insert(record);
	}

	public List<Award> getLastList() {
		return this.awardDao.getLastList();
	}

	public Award getLast() {
		return this.awardDao.getLast();
	}

	public Award getByName(String name) {
		return this.awardDao.getByName(name);
	}

	@Transactional
	public String kaijiang(int[] finals, String qiname) {
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
			
			
			
			/*************初始化房间********************/
			roomDao.initRoom(null);
			
			
			Detail temp = new Detail();
			temp.setKjnumber(finals[0]+","+finals[1]+","+finals[2]+","+finals[3]+","+finals[4]+","+
					finals[5]+","+finals[6]+","+finals[7]+","+finals[8]+","+finals[9]+","+finals[10]);
			temp.setQiname(qiname);
			temp.setStatus("finish");
			detailDao.finishByname(temp);
			
			
			/****************取出押注的详情***********************/
			List<Detail> ds = detailDao.selectByQiname(qiname);
			if(ds==null||ds.size()<=0) {
				return "SUCCESS";
			}
			else {
				List<List<Detail>> dss = CommUtil.getListByRoomid(ds);
				//根据中奖号码将每个房间的奖都处理好
				for(List<Detail> details:dss) {
					//计算总奖池
					Double sum_amount=0.0;
					for(Detail _d:details) {
						sum_amount = CommUtil.add(sum_amount, _d.getAmount());
					}
					int size = details.size();
					//按开奖顺序发派奖金
					for(int k:finals) {
						
						if(size>=k) {
							if(sum_amount>0.0) {
								Detail award_detail = details.get(k-1);
								
								Double award = award_detail.getAmount();
								
								Double award2 = CommUtil.mul(award,2.0);
								if(sum_amount<=award2) {
									//奖金池少于该得奖金
									//用户获得剩下奖金池
									Double f_award = CommUtil.mul(sum_amount,0.99);//0.99倍
									User u = userDao.selectByUserId(award_detail.getUserid());
									u.setBalance(""+CommUtil.add(u.getBalance(),f_award));
									userDao.updateByPrimaryKeySelective(u);
									
									award_detail.setAward(sum_amount);
									detailDao.updateByPrimaryKeySelective(award_detail);
									
									sum_amount=0.0;
								}else {
									Double f_award = CommUtil.mul(award2,0.99);//0.99倍
									
									User u = userDao.selectByUserId(award_detail.getUserid());
									u.setBalance(""+CommUtil.add(u.getBalance(),f_award));
									userDao.updateByPrimaryKeySelective(u);
									
									award_detail.setAward(award2);
									detailDao.updateByPrimaryKeySelective(award_detail);
									
									sum_amount=CommUtil.subtract(sum_amount, award2);
								}
								
							}else {//奖金池没有了
								break;
							}
						}
					}
				}
			}
			
			
			return "SUCCESS";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "FAIL:"+e.getMessage().toString();
		}
		
	}

	
}
