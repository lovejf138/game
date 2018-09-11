package com.webpos.service;

import com.webpos.entity.Admin;

public abstract interface AdminService
{
  public abstract Admin selectByName(String paramString);
}
