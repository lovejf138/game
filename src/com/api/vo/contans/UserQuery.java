package com.api.vo.contans;

public class UserQuery
{
  private String user_id;
  private String parent;
  private String startDate;
  private String endDate;
  
  public String getUser_id()
  {
    return this.user_id;
  }
  
  public void setUser_id(String user_id)
  {
    this.user_id = user_id;
  }
  
  public String getParent()
  {
    return this.parent;
  }
  
  public void setParent(String parent)
  {
    this.parent = parent;
  }
  
  public String getStartDate()
  {
    return this.startDate;
  }
  
  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }
  
  public String getEndDate()
  {
    return this.endDate;
  }
  
  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }
}
