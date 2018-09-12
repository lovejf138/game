package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Message;
import com.webpos.entity.MessageExample;

public abstract interface MessageMapper
{
  
  public abstract int insert(Message paramAccount);
  
  public abstract int countByExample(MessageExample paramAccountExample);
  
  public abstract List<Message> selectByExampleWithBLOBs(MessageExample paramAccountExample);
  
  public abstract List<Message> selectByExampleWithBLOBsAndPage(MessageExample paramAccountExample);
  
}
