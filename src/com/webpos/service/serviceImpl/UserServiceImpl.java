package com.webpos.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.api.vo.contans.SummaryUsers;
import com.api.vo.contans.UserQuery;
import com.webpos.dao.AccountMapper;
import com.webpos.dao.DetailMapper;
import com.webpos.dao.DetailsMapper;
import com.webpos.dao.RoomMapper;
import com.webpos.dao.UserMapper;
import com.webpos.entity.Account;
import com.webpos.entity.Detail;
import com.webpos.entity.User;
import com.webpos.entity.UserExample;
import com.webpos.service.UserService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.Md5Encrypt;
import com.webpos.tools.Pagination;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userDao;
	@Resource
	private DetailsMapper detailsDao;
	@Resource
	private AccountMapper accountDao;
	@Resource
	private RoomMapper roomDao;
	@Resource
	private DetailMapper detailDao;

	public int insert(User record) {
		return this.userDao.insert(record);
	}

	public int updateByPrimaryKeySelective(User record) {
		return this.userDao.updateByPrimaryKeySelective(record);
	}

	public User selectByUserId(String user_id) {
		return this.userDao.selectByUserId(user_id);
	}

	public User selectByParent(String parent) {
		return this.userDao.selectByParent(parent);
	}

//	@Transactional
//	public boolean buyeth(String user_id, String amount, boolean result, double beishu) {
//		try {
//			User u = selectByUserId(user_id);
//			if ((u.getParent() != null) && (!u.getParent().equals(""))) {
//				User u_p = selectByUserId(u.getParent());
//				if (u_p != null) {
//					Double fin_amount = Double.valueOf(CommUtil.mul(amount, Double.valueOf(0.01D)));
//
//					u_p.setBalance("" + CommUtil.add(u_p.getBalance(), fin_amount));
//					this.userDao.updateByPrimaryKeySelective(u_p);
//				}
//			}
//			double final_amount = CommUtil.subtract(u.getBalance(), amount);
//
//			Date now_date = new Date();
//
//			Details d = new Details();
//			d.setCtime(now_date);
//			d.setRemark("");
//			d.setUser_id(user_id);
//			d.setType("join");
//			d.setResult("-" + amount);
//			d.setBalance("" + final_amount);
//			d.setShort_id(u.getId_short());
//			d.setParent_id(u.getParent());
//			this.detailsDao.insert(d);
//
//			double sum_play = CommUtil.add(u.getPlay_sum(), amount);
//			u.setPlay_sum(Double.valueOf(sum_play));
//			u.setBalance("" + final_amount);
//			this.userDao.updateByPrimaryKeySelective(u);
//			if (result) {
//				User u2 = this.userDao.selectByUserId(user_id);
//				double win_amount = CommUtil.mul(amount, Double.valueOf(2.0D));
//				double final_amount2 = CommUtil.add(u2.getBalance(), Double.valueOf(win_amount));
//
//				Details d2 = new Details();
//				d2.setCtime(new Date(now_date.getTime() + 2000L));
//				d2.setRemark("");
//				d2.setUser_id(user_id);
//				d2.setType("win");
//				d2.setResult("" + win_amount);
//				d2.setShort_id(u.getId_short());
//				d2.setParent_id(u.getParent());
//				d2.setBalance("" + final_amount2);
//				this.detailsDao.insert(d2);
//
//				u2.setBalance(""+final_amount2);
//				this.userDao.updateByPrimaryKeySelective(u2);
//			}
//		} catch (Exception e) {
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			return false;
//		}
//		return true;
//	}

	@Transactional
	public boolean withdraw(String user_id, Integer amount) {
		try {
			User u = this.userDao.selectByUserId(user_id);

			Integer final_amount = u.getBalance()-amount;
			u.setBalance(final_amount);
			u.setWithdraw_sum(u.getWithdraw_sum()+amount);

			this.userDao.updateByPrimaryKeySelective(u);

			Account d = new Account();
			d.setCtime(new Date());
			d.setUser_id(user_id);
			d.setType("withdraw");
			d.setAll_eth(u.getAll_eth());
			d.setStatus("request");
			d.setAmount(amount);
			d.setIs_machine(u.getIs_machine());
			d.setBalance(u.getBalance());
			d.setChild_sum(u.getChild_sum());
			d.setPlay_sum(u.getPlay_sum());
			d.setWithdraw_sum(u.getWithdraw_sum());
			d.setRecharge_sum(u.getRecharge_sum());

			this.accountDao.insert(d);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	public List<User> selectByExample(UserExample example) {
		return this.userDao.selectByExample(example);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(UserExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(), this.userDao.countByExample(example));
		p.setList(this.userDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<User> getObjectList(UserExample example) {
		return this.userDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(UserExample example) {
		return Integer.valueOf(this.userDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(UserExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(), this.userDao.countByExample(example));
		p.setList(this.userDao.selectByExampleWithBLOBsAndPageAdmin(example));
		return p;
	}

	public int countByExample(UserExample example) {
		return this.userDao.countByExample(example);
	}

	public SummaryUsers summaryUsers(UserQuery userQuery) {
		return this.userDao.summaryUsers(userQuery);
	}

	@Transactional(rollbackFor = { Exception.class })
	public String recharge(String user_id, String amount) {
		String r = "SUCCESS";
		try {
			User user_last = this.userDao.selectByUserId(user_id);
			if (user_last == null) {
				if (user_id.startsWith("vip_")) {
					User new_user = new User();
					double amo = CommUtil.mul(1000, amount);
					int num = (new Double(amo)).intValue();
					new_user.setBalance(num);
					new_user.setRecharge_sum(num);
					new_user.setPlay_sum(0);
					new_user.setChild_sum(Integer.valueOf(0));
					new_user.setWithdraw_sum(0);

					new_user.setCtime(new Date());
					new_user.setId_md5(Md5Encrypt.md5(user_id));
					new_user.setId_short(user_id.substring(0, 3) + "*****"
							+ user_id.substring(user_id.length() - 4, user_id.length()));
					new_user.setUser_id(user_id);
					new_user.setParent("");
					new_user.setAll_eth(Double.valueOf(CommUtil.add("0", "0")));
					this.userDao.insert(new_user);

					Account d = new Account();
					d.setCtime(new Date());
					d.setUser_id(user_id);
					d.setType("in");
					d.setBalance(new_user.getBalance());
					d.setChild_sum(new_user.getChild_sum());
					d.setPlay_sum(new_user.getPlay_sum());
					d.setWithdraw_sum(new_user.getWithdraw_sum());
					d.setRecharge_sum(new_user.getRecharge_sum());
					d.setStatus("success");
					d.setIs_machine(new_user.getIs_machine());
					d.setAmount(num);
					this.accountDao.insert(d);
				} else {
					return "user_error";
				}
			} else {
				double amo = CommUtil.mul(1000, amount);
				int num = (new Double(amo)).intValue();
				int final_amount = user_last.getBalance()+num;
			
				user_last.setBalance(final_amount);
				user_last.setRecharge_sum(user_last.getRecharge_sum()+num);
				this.userDao.updateByPrimaryKeySelective(user_last);

				Account d = new Account();
				d.setCtime(new Date());
				d.setUser_id(user_id);
				d.setType("in");

				d.setBalance(user_last.getBalance());
				d.setChild_sum(user_last.getChild_sum());
				d.setPlay_sum(user_last.getPlay_sum());
				d.setWithdraw_sum(user_last.getWithdraw_sum());
				d.setRecharge_sum(user_last.getRecharge_sum());
				d.setIs_machine(user_last.getIs_machine());
				d.setStatus("success");
				d.setAmount(num);
				this.accountDao.insert(d);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			r = "fail";
		}
		return r;
	}

	@Transactional
	public User register(String loginname,String pass, String parent, String remark) {
		User new_user = new User();
		try {
			String rp = "";
			if (parent != null) {
				User u_p = this.userDao.selectByParent(parent);
				if (u_p != null) {
					rp = u_p.getUser_id();
					u_p.setChild_sum(Integer.valueOf(u_p.getChild_sum().intValue() + 1));
					this.userDao.updateByPrimaryKeySelective(u_p);
				}
			}
			new_user.setBalance(0);
			new_user.setPlay_sum(0);
			new_user.setChild_sum(Integer.valueOf(0));
			new_user.setRecharge_sum(0);
			new_user.setWithdraw_sum(0);
			new_user.setPass(pass);
			new_user.setCtime(new Date());
			new_user.setId_md5(Md5Encrypt.md5(loginname));
			new_user.setId_short(loginname.substring(0, 8) + "*****"
					+ loginname.substring(loginname.length() - 4, loginname.length()));
			new_user.setUser_id(loginname);
			new_user.setParent(rp);
			if ((remark != null) && (remark != "")) {
				new_user.setAll_eth(Double.valueOf(CommUtil.subtract(remark, "0")));
			}
			this.userDao.insert(new_user);

//			Details d = new Details();
//			d.setCtime(new Date());
//			d.setRemark("new user");
//			d.setResult("0.01");
//			d.setUser_id(loginname);
//			d.setType("gift");
//			this.detailsDao.insert(d);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return null;
		}
		return new_user;
	}

	@Override
	@Transactional
	public String join(Long roomid, String userid, String parentid,String shortid, String qiname, int amount,int number) {
		String r = "SUCCESS";
		try {
//			Room room = roomDao.selectByPrimaryKey(roomid);
////			if(room.getProgress()>=11) {
////				return "抱歉！当前房间下注已满11人，请等下一期或前往其他房间";
////			}
////			int progress = room.getProgress()+1;
//			//room.setProgress(progress);
//			room.setAmount(CommUtil.add(room.getAmount(), amount));
//			roomDao.updateByPrimaryKeySelective(room);
			
			Detail old_d = detailDao.selectByNameAndUserAndNumber(userid, qiname,""+number);
			if(old_d==null) {
				Detail d = new Detail();
				d.setAmount(amount);
				d.setAward(0);
				d.setCtime(new Date());
				d.setNumber(number);
				d.setQiname(qiname);
				d.setRoomid(roomid);
				d.setUserid(userid);
				d.setShortid(shortid);
				d.setParentid(parentid);
				d.setStatus("wait");
				detailDao.insert(d);
			}else {
				old_d.setAmount(old_d.getAmount()+amount);
				detailDao.updateByPrimaryKeySelective(old_d);
			}
			
			User u = selectByUserId(userid);
			u.setPlay_sum(u.getPlay_sum()+amount);
			u.setBalance(u.getBalance()-amount);
			this.userDao.updateByPrimaryKeySelective(u);
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "未知错误:"+e.getMessage().toString();
		}
		return r;
	}

	@Override
	public List<User> getPaihangplay() {
		// TODO Auto-generated method stub
		return userDao.getPaihangplay();
	}

	@Override
	public List<User> getPaihangaward() {
		// TODO Auto-generated method stub
		return userDao.getPaihangaward();
	}
}
