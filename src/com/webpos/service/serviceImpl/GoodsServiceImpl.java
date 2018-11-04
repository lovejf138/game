package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webpos.dao.GoodsMapper;
import com.webpos.entity.Goods;
import com.webpos.entity.GoodsExample;
import com.webpos.service.GoodsService;
import com.webpos.tools.Pagination;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsDao;

	public Goods selectByPrimaryKey(Long id) {
		return this.goodsDao.selectByPrimaryKey(id);
	}

	public int insert(Goods m) {
		return this.goodsDao.insert(m);
	}
	public int update(Goods m) {
		return this.goodsDao.updateByPrimaryKeySelective(m);
	}
	
	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(GoodsExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.goodsDao.countByExample(example));
		p.setList(this.goodsDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Goods> getObjectList(GoodsExample example) {
		return this.goodsDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(GoodsExample example) {
		return Integer.valueOf(this.goodsDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(GoodsExample example) {
		return null;
	}

	public int countByExample(GoodsExample example) {
		return this.goodsDao.countByExample(example);
	}

	public List<Goods> selectByExample(GoodsExample example) {
		return null;
	}

}
