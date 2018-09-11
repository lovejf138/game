package com.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
  static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
  
  public static String getCurrDate()
  {
    String currDate = sdf1.format(new Date());
    
    return currDate;
  }
  
  public static String getStartDate()
  {
    String startDate = sdf2.format(new Date());
    startDate = startDate + " 00:00:00";
    return startDate;
  }
  
  public static String getEndDate()
  {
    String endDate = sdf2.format(new Date());
    endDate = endDate + " 23:59:59";
    return endDate;
  }
}
