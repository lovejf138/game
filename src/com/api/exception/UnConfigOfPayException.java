package com.api.exception;

import com.webpos.common.PayType;

public class UnConfigOfPayException
  extends RuntimeException
{
  private static final long serialVersionUID = 7095942583639918854L;
  private PayType payType;
  
  public UnConfigOfPayException(PayType payType)
  {
    this.payType = payType;
  }
  
  public String getMessage()
  {
    return String.format("%s接口未配置", new Object[] { this.payType.getDesc() });
  }
}
