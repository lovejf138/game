package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Goods;
import com.webpos.entity.GoodsExample;

public abstract interface GoodsMapper
{
  
	 public abstract int insert(Goods paramDetails);
	 
  public abstract Goods selectByPrimaryKey(Long paramLong);
	  
  public abstract int countByExample(GoodsExample paramAccountExample);
  
  public abstract List<Goods> selectByExampleWithBLOBs(GoodsExample paramAccountExample);
  
  public abstract List<Goods> selectByExampleWithBLOBsAndPage(GoodsExample paramAccountExample);
  
  public abstract int updateByPrimaryKeySelective(Goods paramAccount);
  
}
