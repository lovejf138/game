package com.webpos.dao;

import com.api.vo.contans.PlayQuery;
import com.api.vo.contans.SelfPlayQuery;
import com.api.vo.contans.SharePlayQuery;
import com.api.vo.contans.SummaryPlay;
import com.api.vo.contans.SummarySelfPlay;
import com.api.vo.contans.SummarySharePlay;
import com.webpos.entity.DetailExample;
import com.webpos.entity.Details;
import java.util.List;

public abstract interface DetailsMapper
{
  public abstract Details selectByPrimaryKey(Long paramLong);
  
  public abstract int insert(Details paramDetails);
  
  public abstract int countByExample(DetailExample paramDetailExample);
  
  public abstract List<Details> selectByExampleWithBLOBs(DetailExample paramDetailExample);
  
  public abstract List<Details> selectByExampleWithBLOBsAndPage(DetailExample paramDetailExample);
  
  public abstract SummaryPlay summaryPlay(PlayQuery paramPlayQuery);
  
  public abstract SummarySharePlay summarySharePlay(SharePlayQuery paramSharePlayQuery);
  
  public abstract SummarySelfPlay summarySelfPlay(SelfPlayQuery paramSelfPlayQuery);
}
