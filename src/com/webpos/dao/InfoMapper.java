package com.webpos.dao;

import com.webpos.entity.Info;

public abstract interface InfoMapper
{
  
 public abstract int insert(Info info);
	 
  public abstract Info selectByPrimaryKey(Long paramLong);
  public abstract Info selectByUserid(Long userid);
	  
  public abstract int updateByPrimaryKeySelective(Info info);
  
}
