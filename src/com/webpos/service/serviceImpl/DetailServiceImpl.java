package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.vo.contans.PlayQuery;
import com.api.vo.contans.SummaryPlay;
import com.webpos.dao.DetailMapper;
import com.webpos.entity.Detail;
import com.webpos.entity.DetailExample;
import com.webpos.service.DetailService;
import com.webpos.tools.Pagination;

@Service
public class DetailServiceImpl implements DetailService {
	@Resource
	private DetailMapper detailsDao;

	public Detail selectByPrimaryKey(Long id) {
		return this.detailsDao.selectByPrimaryKey(id);
	}

	public int insert(Detail record) {
		return this.detailsDao.insert(record);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(DetailExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.detailsDao.countByExample(example));
		p.setList(this.detailsDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Detail> getObjectList(DetailExample example) {
		return this.detailsDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(DetailExample example) {
		return Integer.valueOf(this.detailsDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(DetailExample example) {
		return null;
	}

	public int countByExample(DetailExample example) {
		return this.detailsDao.countByExample(example);
	}

	public List<Detail> selectByExample(DetailExample example) {
		return null;
	}

	@Override
	public SummaryPlay summaryPlay(PlayQuery paramPlayQuery) {
		 return this.detailsDao.summaryPlay(paramPlayQuery);
	}

}
