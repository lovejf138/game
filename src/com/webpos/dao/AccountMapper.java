package com.webpos.dao;

import com.api.vo.contans.SummaryWithdraw;
import com.api.vo.contans.WithdrawQuery;
import com.webpos.entity.Account;
import com.webpos.entity.AccountExample;
import java.util.List;

public abstract interface AccountMapper
{
  public abstract Account getById(Long paramLong);
  
  public abstract int insert(Account paramAccount);
  
  public abstract int countByExample(AccountExample paramAccountExample);
  
  public abstract List<Account> selectByExampleWithBLOBs(AccountExample paramAccountExample);
  
  public abstract List<Account> selectByExampleWithBLOBsAndPage(AccountExample paramAccountExample);
  
  public abstract int updateByPrimaryKeySelective(Account paramAccount);
  
  public abstract SummaryWithdraw summaryWithdraw(WithdrawQuery paramWithdrawQuery);
}
