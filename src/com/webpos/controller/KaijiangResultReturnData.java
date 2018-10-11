package com.webpos.controller;

import java.util.List;

import com.webpos.entity.Award;
import com.webpos.entity.Detail;

public class KaijiangResultReturnData {
	private String result;
	private Award awards;
	private List<Detail> details;
	private Double amount;//下注金额
	private Double award;//奖金金额
	
	public Double getAward() {
		return award;
	}

	public Award getAwards() {
		return awards;
	}

	public void setAwards(Award awards) {
		this.awards = awards;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setAward(Double award) {
		this.award = award;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

}
