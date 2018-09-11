package com.api.utils;

import java.util.ArrayList;
import java.util.List;

public class Paging<T>
{
  private int pageNo = 1;
  private int pageSize = 10;
  private long total = 0L;
  private long pageTotal = 0L;
  private List<T> list = new ArrayList();
  
  public Paging() {}
  
  public Paging(int pageNo, int pageSize)
  {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getPageNo()
  {
    return this.pageNo;
  }
  
  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }
  
  public List<T> getList()
  {
    return this.list;
  }
  
  public void setList(List<T> list)
  {
    this.list = list;
  }
  
  public long getTotal()
  {
    return this.total;
  }
  
  public void setTotal(long total)
  {
    this.total = total;
    setPageTotalFromTotal();
  }
  
  public void setPageTotalFromTotal()
  {
    setPageTotal(this.total % this.pageSize == 0L ? this.total / this.pageSize : this.total / this.pageSize + 1L);
  }
  
  public long getPageTotal()
  {
    return this.pageTotal;
  }
  
  public void setPageTotal(long pageTotal)
  {
    this.pageTotal = pageTotal;
  }
}
