package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.webpos.dao.AwardMapper;
import com.webpos.entity.Award;
import com.webpos.service.AwardService;

@Service
public class AwardServiceImpl implements AwardService {
	@Resource
	private AwardMapper awardDao;

	public int insert(Award record) {
		return this.awardDao.insert(record);
	}

	
	public List<Award> getLastList() {
		return this.awardDao.getLastList();
	}
	
	public Award getLast() {
		return this.awardDao.getLast();
	}


	public Award getByName(String name) {
		return this.awardDao.getByName(name);
	}

}
