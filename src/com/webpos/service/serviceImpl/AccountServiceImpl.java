package com.webpos.service.serviceImpl;

import com.api.vo.contans.SummaryWithdraw;
import com.api.vo.contans.WithdrawQuery;
import com.webpos.dao.AccountMapper;
import com.webpos.entity.Account;
import com.webpos.entity.AccountExample;
import com.webpos.entity.UserExample;
import com.webpos.service.AccountService;
import com.webpos.tools.Pagination;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl
  implements AccountService
{
  @Resource
  private AccountMapper accountDao;
  
  public int insert(Account record)
  {
    return this.accountDao.insert(record);
  }
  
  public int updateByPrimaryKeySelective(Account record)
  {
    return this.accountDao.updateByPrimaryKeySelective(record);
  }
  
  public List<Account> selectByExample(UserExample example)
  {
    return null;
  }
  
  @Transactional(readOnly=true)
  public Pagination getObjectListWithPage(AccountExample example)
  {
    Pagination p = new Pagination(example.getPageNo(), example.getPageSize(), this.accountDao.countByExample(example));
    p.setList(this.accountDao.selectByExampleWithBLOBsAndPage(example));
    return p;
  }
  
  @Transactional(readOnly=true)
  public List<Account> getObjectList(AccountExample example)
  {
    return this.accountDao.selectByExampleWithBLOBs(example);
  }
  
  @Transactional(readOnly=true)
  public Integer getObjectListCount(AccountExample example)
  {
    return Integer.valueOf(this.accountDao.countByExample(example));
  }
  
  public Pagination getObjectListWithPageAdmin(AccountExample example)
  {
    return null;
  }
  
  public int countByExample(AccountExample example)
  {
    return this.accountDao.countByExample(example);
  }
  
  public Account getById(Long id)
  {
    return this.accountDao.getById(id);
  }
  
  public SummaryWithdraw summaryWithdraw(WithdrawQuery userQuery)
  {
    return this.accountDao.summaryWithdraw(userQuery);
  }
}
