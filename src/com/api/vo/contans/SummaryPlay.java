package com.api.vo.contans;

public class SummaryPlay
{
  private String result;
  private String amount_sum;
  private String award_sum;
  
  public String getAward_sum() {
	return award_sum;
}

public void setAward_sum(String award_sum) {
	this.award_sum = award_sum;
}

public String getResult()
  {
    return this.result;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getAmount_sum()
  {
    return this.amount_sum;
  }
  
  public void setAmount_sum(String amount_sum)
  {
    this.amount_sum = amount_sum;
  }
}
