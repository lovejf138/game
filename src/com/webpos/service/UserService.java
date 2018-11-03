package com.webpos.service;import com.api.vo.contans.SummaryUsers;import com.api.vo.contans.UserQuery;import com.webpos.entity.User;import com.webpos.entity.UserExample;import com.webpos.tools.Pagination;import java.util.List;public abstract interface UserService{  public abstract User selectByUserId(String paramString);    public abstract User selectByPhone(String paramString);    public abstract User selectById(long id);    public abstract User selectByParent(String paramString);    public abstract int insert(User paramUser);    public abstract int insertWithId(User paramUser);    public abstract int updateByPrimaryKeySelective(User paramUser);   // public abstract boolean buyeth(String paramString1, String paramString2, boolean paramBoolean, double paramDouble);    //下注  public abstract String join(Long roomid, String userid,String parentid,String shortid,String qiname,int amount,int number);    //猜  public abstract String onejoin(String userid,String parentid,String qiname,double amount,int number);    public abstract String testjoin(Long roomid, String userid,String qiname,int amount,int number);    public abstract boolean withdraw(String userid, double amount);    public abstract List<User> selectByExample(UserExample paramUserExample);    public abstract Pagination getObjectListWithPage(UserExample paramUserExample);    public abstract List<User> getObjectList(UserExample paramUserExample);    public abstract Integer getObjectListCount(UserExample paramUserExample);    public abstract Pagination getObjectListWithPageAdmin(UserExample paramUserExample);    public abstract int countByExample(UserExample paramUserExample);    public abstract SummaryUsers summaryUsers(UserQuery paramUserQuery);    public abstract String recharge(String paramString1, double paramString2);    public abstract User register(String paramString1,String pass, String paramString2);    public abstract List<User> getPaihangplay();//参与排行榜    public abstract List<User> getPaihangaward();//奖金排行榜}