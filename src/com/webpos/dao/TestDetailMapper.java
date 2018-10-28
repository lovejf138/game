package com.webpos.dao;

import java.util.List;

import com.api.vo.contans.PlayQuery;
import com.api.vo.contans.SummaryPlay;
import com.webpos.entity.TestDetail;
import com.webpos.entity.TestDetailExample;

public abstract interface TestDetailMapper
{
  public abstract TestDetail selectByPrimaryKey(Long paramLong);
  
  public abstract TestDetail selectByNameAndUserAndNumber(String userid,String qiname,String number);
  
  public abstract int insert(TestDetail paramDetails);
  
  public abstract int countByExample(TestDetailExample paramDetailExample);
  
  public abstract List<TestDetail> selectByExampleWithBLOBs(TestDetailExample paramDetailExample);
  
  public abstract List<TestDetail> selectByExampleWithBLOBsAndPage(TestDetailExample paramDetailExample);
  
  /**
   * 通过期号取出所有记录
   * @param name
   * @return
   */
  public abstract List<TestDetail> selectByQiname(String name);
  
  /**
   * 将详情设置为已结算
   * @param name
   * @return
   */
  public abstract int finishByname(TestDetail paramDetails);
  
  public abstract int updateByPrimaryKeySelective(TestDetail paramUser);
  
  public abstract SummaryPlay summaryPlay(PlayQuery paramPlayQuery);
  
}
