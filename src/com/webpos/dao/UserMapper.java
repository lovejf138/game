package com.webpos.dao;

import com.api.vo.contans.SummaryUsers;
import com.api.vo.contans.UserQuery;
import com.webpos.entity.User;
import com.webpos.entity.UserExample;
import java.util.List;

public abstract interface UserMapper
{
  public abstract User selectByUserId(String paramString);
  
  public abstract User selectById(long id);
  
  public abstract User selectByParent(String paramString);
  
  public abstract List<User> getPaihangplay();//参与排行榜
  
  public abstract List<User> getPaihangaward();//奖金排行榜
  
  public abstract int insert(User paramUser);
  
  public abstract int insertWithId(User paramUser);
  
  public abstract int updateByPrimaryKeySelective(User paramUser);
  
  public abstract List<User> selectByExample(UserExample paramUserExample);
  
  public abstract List<User> selectByExampleWithBLOBs(UserExample paramUserExample);
  
  public abstract List<User> selectByExampleWithBLOBsAndPage(UserExample paramUserExample);
  
  public abstract List<User> selectByExampleWithBLOBsAndPageAdmin(UserExample paramUserExample);
  
  public abstract int countByExample(UserExample paramUserExample);
  
  public abstract SummaryUsers summaryUsers(UserQuery paramUserQuery);
}
