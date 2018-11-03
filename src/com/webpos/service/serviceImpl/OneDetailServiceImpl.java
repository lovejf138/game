package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webpos.dao.OneDetailMapper;
import com.webpos.entity.OneDetail;
import com.webpos.entity.OneDetailExample;
import com.webpos.service.OneDetailService;
import com.webpos.tools.Pagination;

@Service
public class OneDetailServiceImpl implements OneDetailService {
	@Resource
	private OneDetailMapper detailsDao;

	public OneDetail selectByPrimaryKey(Long id) {
		return this.detailsDao.selectByPrimaryKey(id);
	}

	public int insert(OneDetail record) {
		return this.detailsDao.insert(record);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(OneDetailExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.detailsDao.countByExample(example));
		p.setList(this.detailsDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<OneDetail> getObjectList(OneDetailExample example) {
		return this.detailsDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(OneDetailExample example) {
		return Integer.valueOf(this.detailsDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(OneDetailExample example) {
		return null;
	}

	public int countByExample(OneDetailExample example) {
		return this.detailsDao.countByExample(example);
	}

	public List<OneDetail> selectByExample(OneDetailExample example) {
		return null;
	}

}
