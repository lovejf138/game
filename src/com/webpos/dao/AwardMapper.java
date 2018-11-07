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
	
	/**
	 * 调用结算存储过程
	 * @param kaijianghaoma
	 * @param qiname
	 * @return
	 */
	public abstract void jiesuandetail2(String no1,String no2,String no3,String no4,String no5,String ctime,String haoma,String kjjg1,String kjjg2,String qiname);
	  

}
