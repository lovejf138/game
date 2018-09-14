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
  
  /**
   * 通过期号取出所有记录
   * @param name
   * @return
   */
  public abstract List<Detail> selectByQiname(String name);
  
  /**
   * 将详情设置为已结算
   * @param name
   * @return
   */
  public abstract int finishByname(Detail paramDetails);
  
  public abstract int updateByPrimaryKeySelective(Detail paramUser);
  
}
