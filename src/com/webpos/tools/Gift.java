package com.webpos.tools;

public class Gift
{
  private int id;
  private String name;
  private double prob;
  private double beishu;
  
  public Gift(int id, String name, double prob, double beishu)
  {
    this.id = id;
    this.name = name;
    this.prob = prob;
    this.beishu = beishu;
  }
  
  public double getBeishu()
  {
    return this.beishu;
  }
  
  public void setBeishu(double beishu)
  {
    this.beishu = beishu;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public double getProb()
  {
    return this.prob;
  }
  
  public void setProb(double prob)
  {
    this.prob = prob;
  }
}
