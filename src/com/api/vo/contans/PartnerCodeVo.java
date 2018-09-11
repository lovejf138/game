package com.api.vo.contans;

public class PartnerCodeVo
{
  private String urls;
  private String key;
  
  public PartnerCodeVo(String urls, String key)
  {
    this.urls = urls;
    this.key = key;
  }
  
  public String getUrls()
  {
    return this.urls;
  }
  
  public String getKey()
  {
    return this.key;
  }
}
