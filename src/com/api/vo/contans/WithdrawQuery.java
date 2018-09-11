package com.api.vo.contans;

public class WithdrawQuery
{
  private String user_id;
  private String status;
  private String type;
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
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
