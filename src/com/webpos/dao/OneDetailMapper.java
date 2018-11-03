package com.webpos.dao;

import java.util.List;

import com.webpos.entity.OneDetail;
import com.webpos.entity.OneDetailExample;

public abstract interface OneDetailMapper
{
  public abstract OneDetail selectByPrimaryKey(Long paramLong);
  
  public abstract OneDetail selectByNameAndUser(String userid,String qiname);
  
  public abstract int insert(OneDetail paramDetails);
  
  public abstract int countByExample(OneDetailExample paramDetailExample);
  
  public abstract List<OneDetail> selectByExampleWithBLOBs(OneDetailExample paramDetailExample);
  
  public abstract List<OneDetail> selectByExampleWithBLOBsAndPage(OneDetailExample paramDetailExample);
  
//  /**
//   * 通过期号取出所有记录
//   * @param name
//   * @return
//   */
//  public abstract List<Detail> selectByQiname(String name);
  
  /**
   * 将详情设置为已结算
   * @param name
   * @return
   */
  public abstract int finishByname(OneDetail paramDetails);
  
  public abstract int updateByPrimaryKeySelective(OneDetail paramUser);
  
  //public abstract SummaryPlay summaryPlay(PlayQuery paramPlayQuery);
  
}
