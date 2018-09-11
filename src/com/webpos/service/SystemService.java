package com.webpos.service;

import com.webpos.entity.DSystem;

public abstract interface SystemService
{
  public abstract DSystem selectAll();
  
  public abstract int updateAll(DSystem paramDSystem);
}
