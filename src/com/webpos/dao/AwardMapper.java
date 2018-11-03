package com.webpos.dao;

import java.util.List;

import com.webpos.entity.Award;
import com.webpos.entity.Detail;

public abstract interface AwardMapper {
	
	public abstract Award getLast();
	
	public abstract Award getByName(String name);

	public abstract List<Award> getLastList(int _size);

	public abstract int insert(Award param);
	
	/**
	 * 调用结算存储过程
	 * @param kaijianghaoma
	 * @param qiname
	 * @return
	 */
	public abstract int jiesuanOnedetail(String kaijianghaoma,String qiname);
	  

}
