package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.vo.contans.PlayQuery;
import com.api.vo.contans.SummaryPlay;
import com.webpos.dao.TestDetailMapper;
import com.webpos.entity.TestDetail;
import com.webpos.entity.TestDetailExample;
import com.webpos.service.TestDetailService;
import com.webpos.tools.Pagination;

@Service
public class TestDetailServiceImpl implements TestDetailService {
	@Resource
	private TestDetailMapper testdetailsDao;

	public TestDetail selectByPrimaryKey(Long id) {
		return this.testdetailsDao.selectByPrimaryKey(id);
	}

	public int insert(TestDetail record) {
		return this.testdetailsDao.insert(record);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(TestDetailExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.testdetailsDao.countByExample(example));
		p.setList(this.testdetailsDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<TestDetail> getObjectList(TestDetailExample example) {
		return this.testdetailsDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(TestDetailExample example) {
		return Integer.valueOf(this.testdetailsDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(TestDetailExample example) {
		return null;
	}

	public int countByExample(TestDetailExample example) {
		return this.testdetailsDao.countByExample(example);
	}

	public List<TestDetail> selectByExample(TestDetailExample example) {
		return null;
	}

	@Override
	public SummaryPlay summaryPlay(PlayQuery paramPlayQuery) {
		 return this.testdetailsDao.summaryPlay(paramPlayQuery);
	}

}
