package com.webpos.entity;import java.io.Serializable;import java.util.Date;public class Room implements Serializable {	private static final long serialVersionUID = 1L;	private Long id;	private String name;	private Date ctime;	private Integer progress;	private Double amount;	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Date getCtime() {		return ctime;	}	public void setCtime(Date ctime) {		this.ctime = ctime;	}	public Integer getProgress() {		return progress;	}	public void setProgress(Integer progress) {		this.progress = progress;	}	public Double getAmount() {		return amount;	}	public void setAmount(Double amount) {		this.amount = amount;	}	}