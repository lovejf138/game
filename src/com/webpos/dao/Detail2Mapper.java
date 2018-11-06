package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Detail2;
import com.webpos.entity.Detail2Example;

public abstract interface Detail2Mapper
{
  public abstract Detail2 selectByPrimaryKey(Long paramLong);
  
  public abstract int insert(Detail2 paramDetails);
  
  public abstract int countByExample(Detail2Example paramDetailExample);
  
  public abstract List<Detail2> selectByExampleWithBLOBs(Detail2Example paramDetailExample);
  
  public abstract List<Detail2> selectByExampleWithBLOBsAndPage(Detail2Example paramDetailExample);
  
  public abstract int updateByPrimaryKeySelective(Detail2 paramUser);

  
}
