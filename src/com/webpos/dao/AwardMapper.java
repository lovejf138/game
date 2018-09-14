package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Award;

public abstract interface AwardMapper {
	
	public abstract Award getLast();

	public abstract List<Award> getLastList();

	public abstract int insert(Award param);

}
