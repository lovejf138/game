package com.webpos.service.serviceImpl;

import com.api.vo.contans.SummaryWithdraw;
import com.api.vo.contans.WithdrawQuery;
import com.webpos.dao.AccountMapper;
import com.webpos.dao.UserMapper;
import com.webpos.entity.Account;
import com.webpos.entity.AccountExample;
import com.webpos.entity.User;
import com.webpos.entity.UserExample;
import com.webpos.service.AccountService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.Pagination;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountMapper accountDao;
	@Resource
	private UserMapper userDao;

	public int insert(Account record) {
		return this.accountDao.insert(record);
	}

	public int updateByPrimaryKeySelective(Account record) {
		return this.accountDao.updateByPrimaryKeySelective(record);
	}

	public List<Account> selectByExample(UserExample example) {
		return null;
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(AccountExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.accountDao.countByExample(example));
		p.setList(this.accountDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Account> getObjectList(AccountExample example) {
		return this.accountDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(AccountExample example) {
		return Integer.valueOf(this.accountDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(AccountExample example) {
		return null;
	}

	public int countByExample(AccountExample example) {
		return this.accountDao.countByExample(example);
	}

	public Account getById(Long id) {
		return this.accountDao.getById(id);
	}

	public Account getByRemark(String remark) {
		return this.accountDao.getByRemark(remark);
	}

	public SummaryWithdraw summaryWithdraw(WithdrawQuery userQuery) {
		return this.accountDao.summaryWithdraw(userQuery);
	}

	@Override
	@Transactional
	public String in(Account account, double amount) {
		try {
			account.setStatus("success");
			accountDao.updateByPrimaryKeySelective(account);

			User user_last = userDao.selectByPhone(account.getUser_id());
			user_last.setBalance(CommUtil.add(user_last.getBalance(), amount));
			userDao.updateByPrimaryKeySelective(user_last);
			return "success";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "fail";
		}

		//return null;
	}
}
