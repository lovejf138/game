package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Detail;
import com.webpos.entity.DetailExample;

public abstract interface DetailMapper
{
  public abstract Detail selectByPrimaryKey(Long paramLong);
  
  public abstract int insert(Detail paramDetails);
  
  public abstract int countByExample(DetailExample paramDetailExample);
  
  public abstract List<Detail> selectByExampleWithBLOBs(DetailExample paramDetailExample);
  
  public abstract List<Detail> selectByExampleWithBLOBsAndPage(DetailExample paramDetailExample);
  
}
