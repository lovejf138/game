package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.vo.contans.PlayQuery;
import com.api.vo.contans.SelfPlayQuery;
import com.api.vo.contans.SharePlayQuery;
import com.api.vo.contans.SummaryPlay;
import com.api.vo.contans.SummarySelfPlay;
import com.api.vo.contans.SummarySharePlay;
import com.webpos.dao.DetailsMapper;
import com.webpos.entity.DetailExample;
import com.webpos.entity.Details;
import com.webpos.entity.DetailsExample;
import com.webpos.service.DetailsService;
import com.webpos.tools.Pagination;

@Service
public class DetailsServiceImpl
  implements DetailsService
{
  @Resource
  private DetailsMapper detailsDao;
  
  public Details selectByPrimaryKey(Long id)
  {
    return this.detailsDao.selectByPrimaryKey(id);
  }
  
  public int insert(Details record)
  {
    return this.detailsDao.insert(record);
  }
  
  @Transactional(readOnly=true)
  public Pagination getObjectListWithPage(DetailsExample example)
  {
    Pagination p = new Pagination(example.getPageNo(), example.getPageSize(), this.detailsDao.countByExample(example));
    p.setList(this.detailsDao.selectByExampleWithBLOBsAndPage(example));
    return p;
  }
  
  @Transactional(readOnly=true)
  public List<Details> getObjectList(DetailsExample example)
  {
    return this.detailsDao.selectByExampleWithBLOBs(example);
  }
  
  @Transactional(readOnly=true)
  public Integer getObjectListCount(DetailsExample example)
  {
    return Integer.valueOf(this.detailsDao.countByExample(example));
  }
  
  public Pagination getObjectListWithPageAdmin(DetailsExample example)
  {
    return null;
  }
  
  public int countByExample(DetailsExample example)
  {
    return this.detailsDao.countByExample(example);
  }
  
  public List<Details> selectByExample(DetailsExample example)
  {
    return null;
  }
  
  public SummaryPlay summaryPlay(PlayQuery userQuery)
  {
    return this.detailsDao.summaryPlay(userQuery);
  }
  
  public SummarySharePlay summarySharePlay(SharePlayQuery userQuery)
  {
    return this.detailsDao.summarySharePlay(userQuery);
  }
  
  public SummarySelfPlay summarySelfPlay(SelfPlayQuery userQuery)
  {
    return this.detailsDao.summarySelfPlay(userQuery);
  }
}
