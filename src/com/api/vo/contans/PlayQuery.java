package com.api.vo.contans;

public class PlayQuery
{
  private String user_id;
  private String type;
  private String startDate;
  private String endDate;
  
  private Long roomid;
  private String qiname;
  
  
  public Long getRoomid() {
	return roomid;
}

public void setRoomid(Long roomid) {
	this.roomid = roomid;
}

public String getQiname() {
	return qiname;
}

public void setQiname(String qiname) {
	this.qiname = qiname;
}

public String getUser_id()
  {
    return this.user_id;
  }
  
  public void setUser_id(String user_id)
  {
    this.user_id = user_id;
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
