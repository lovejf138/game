package com.webpos.service;import java.util.List;import com.webpos.entity.Detail2;import com.webpos.entity.Detail2Example;import com.webpos.tools.Pagination;public abstract interface Detail2Service{  public abstract Detail2 selectByPrimaryKey(Long paramLong);    public abstract int insert(Detail2 paramDetails);    public abstract Integer getObjectListCount(Detail2Example paramDetailExample);    public abstract List<Detail2> getObjectList(Detail2Example paramDetailExample);    public abstract List<Detail2> selectByExample(Detail2Example paramDetailExample);    public abstract Pagination getObjectListWithPage(Detail2Example paramDetailExample);    public abstract Pagination getObjectListWithPageAdmin(Detail2Example paramDetailExample);    public abstract int countByExample(Detail2Example paramDetailExample);  }