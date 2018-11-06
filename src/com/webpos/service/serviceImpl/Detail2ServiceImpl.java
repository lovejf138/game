package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webpos.dao.Detail2Mapper;
import com.webpos.entity.Detail2;
import com.webpos.entity.Detail2Example;
import com.webpos.service.Detail2Service;
import com.webpos.tools.Pagination;

@Service
public class Detail2ServiceImpl implements Detail2Service {
	@Resource
	private Detail2Mapper detailsDao;

	public Detail2 selectByPrimaryKey(Long id) {
		return this.detailsDao.selectByPrimaryKey(id);
	}

	public int insert(Detail2 record) {
		return this.detailsDao.insert(record);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(Detail2Example example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.detailsDao.countByExample(example));
		p.setList(this.detailsDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Detail2> getObjectList(Detail2Example example) {
		return this.detailsDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(Detail2Example example) {
		return Integer.valueOf(this.detailsDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(Detail2Example example) {
		return null;
	}

	public int countByExample(Detail2Example example) {
		return this.detailsDao.countByExample(example);
	}

	public List<Detail2> selectByExample(Detail2Example example) {
		return null;
	}

	

}
