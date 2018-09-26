package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Award;

public abstract interface AwardMapper {
	
	public abstract Award getLast();
	
	public abstract Award getByName(String name);

	public abstract List<Award> getLastList(int _size);

	public abstract int insert(Award param);

}
