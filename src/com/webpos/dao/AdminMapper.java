package com.webpos.dao;

import com.webpos.entity.Admin;

public abstract interface AdminMapper
{
  public abstract Admin selectByName(String paramString);
}
