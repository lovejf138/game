package com.webpos.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.webpos.dao.InfoMapper;

import com.webpos.entity.Info;
import com.webpos.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {
	@Resource
	private InfoMapper infoDao;
	
	public Info selectByPrimaryKey(Long id) {
		return this.infoDao.selectByPrimaryKey(id);
	}

	public int insert(Info m) {
		return this.infoDao.insert(m);
	}
	public int update(Info m) {
		return this.infoDao.updateByPrimaryKeySelective(m);
	}

	@Override
	public Info selectByUserid(Long paramLong) {
		// TODO Auto-generated method stub
		return this.infoDao.selectByUserid(paramLong);
	}
	

}
