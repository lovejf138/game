package com.webpos.service;import java.util.List;import com.webpos.entity.Award;public abstract interface AwardService{  public abstract Award getByName(String name);    public abstract int insert(Award paramAccount);    public abstract Award getLast();    public abstract List<Award> getLastList(int size);      /**   * 开奖   * @param numbers   * @param qiname   * @param ifJiesuan   * @return   */  public abstract String kaijiang(int[] numbers,String qiname,boolean ifJiesuan);    public abstract String testkaijiang(int[] numbers,String qiname,boolean ifJiesuan);}