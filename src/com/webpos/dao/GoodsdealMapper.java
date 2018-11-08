package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Goodsdeal;
import com.webpos.entity.GoodsdealExample;

public abstract interface GoodsdealMapper
{
  
   public abstract int insert(Goodsdeal paramDetails);
	 
  public abstract Goodsdeal selectByPrimaryKey(Long paramLong);
  public abstract Goodsdeal selectByUserid(Long paramLong);
	  
  public abstract int countByExample(GoodsdealExample paramAccountExample);
  
  public abstract List<Goodsdeal> selectByExampleWithBLOBs(GoodsdealExample paramAccountExample);
  
  public abstract List<Goodsdeal> selectByExampleWithBLOBsAndPage(GoodsdealExample paramAccountExample);
  
  public abstract int updateByPrimaryKeySelective(Goodsdeal paramAccount);
  
}
