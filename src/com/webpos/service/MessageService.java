package com.webpos.service;import java.util.List;import com.webpos.entity.Message;import com.webpos.entity.MessageExample;import com.webpos.tools.Pagination;public abstract interface MessageService{    public abstract int insert(Message paramAccount);    public abstract Integer getObjectListCount(MessageExample paramAccountExample);    public abstract List<Message> getObjectList(MessageExample paramAccountExample);   // public abstract List<Message> selectByExample(MessageExample paramUserExample);    public abstract Pagination getObjectListWithPage(MessageExample paramAccountExample);    public abstract int countByExample(MessageExample paramAccountExample);  }