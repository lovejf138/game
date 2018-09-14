package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Room;
import com.webpos.entity.RoomExample;

public abstract interface RoomMapper
{
  
  public abstract Room selectByPrimaryKey(Long paramLong);
	  
  public abstract int countByExample(RoomExample paramAccountExample);
  
  public abstract List<Room> selectByExampleWithBLOBs(RoomExample paramAccountExample);
  
  public abstract List<Room> selectByExampleWithBLOBsAndPage(RoomExample paramAccountExample);
  
  public abstract int updateByPrimaryKeySelective(Room paramAccount);
  public abstract int initRoom(Room paramAccount);
  
}
