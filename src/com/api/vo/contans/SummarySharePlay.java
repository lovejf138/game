package com.api.vo.contans;

import com.webpos.tools.CommUtil;
import java.text.DecimalFormat;

public class SummarySharePlay
{
  private String result;
  private String amount;
  private String sum_amount;
  
  public String getSum_amount()
  {
    double x = 0.0D;
    String y = this.amount.replaceAll("-", "");
    x = CommUtil.mul(y, Double.valueOf(0.01D));
    DecimalFormat df = new DecimalFormat("0.000000");
    return df.format(x);
  }
  
  public void setSum_amount(String sum_amount)
  {
    this.sum_amount = sum_amount;
  }
  
  public String getResult()
  {
    return this.result;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
}
