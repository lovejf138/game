package com.webpos.service;import java.util.List;import com.webpos.entity.Goodsdeal;import com.webpos.entity.GoodsdealExample;import com.webpos.tools.Pagination;public abstract interface GoodsdealService{  public abstract Goodsdeal selectByPrimaryKey(Long paramLong);    public abstract int insert(Goodsdeal paramDetails);  public abstract int update(Goodsdeal paramDetails);    public abstract Integer getObjectListCount(GoodsdealExample paramDetailExample);    public abstract List<Goodsdeal> getObjectList(GoodsdealExample paramDetailExample);    public abstract List<Goodsdeal> selectByExample(GoodsdealExample paramDetailExample);    public abstract Pagination getObjectListWithPage(GoodsdealExample paramDetailExample);    public abstract Pagination getObjectListWithPageAdmin(GoodsdealExample paramDetailExample);    public abstract int countByExample(GoodsdealExample paramDetailExample);   }