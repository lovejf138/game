package com.webpos.dao;

import com.webpos.entity.DSystem;

public abstract interface SystemMapper
{
  public abstract DSystem selectAll();
  
  public abstract int updateAll(DSystem paramDSystem);
}
