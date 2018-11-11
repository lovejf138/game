package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.webpos.dao.AccountMapper;
import com.webpos.dao.Detail2Mapper;
import com.webpos.dao.GoodsMapper;
import com.webpos.dao.GoodsdealMapper;
import com.webpos.dao.UserMapper;
import com.webpos.entity.Detail2;
import com.webpos.entity.Goodsdeal;
import com.webpos.entity.GoodsdealExample;
import com.webpos.service.GoodsdealService;
import com.webpos.tools.Pagination;

@Service
public class GoodsdealServiceImpl implements GoodsdealService {
	@Resource
	private GoodsMapper goodsDao;
	@Resource
	private UserMapper userDao;
	@Resource
	private AccountMapper accountDao;
	@Resource
	private Detail2Mapper detailDao;
	@Resource
	private GoodsdealMapper goodsdealMapper;

	public Goodsdeal selectByPrimaryKey(Long id) {
		return this.goodsdealMapper.selectByPrimaryKey(id);
	}

	public int insert(Goodsdeal m) {
		return this.goodsdealMapper.insert(m);
	}

	public int update(Goodsdeal m) {
		return this.goodsdealMapper.updateByPrimaryKeySelective(m);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(GoodsdealExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.goodsdealMapper.countByExample(example));
		p.setList(this.goodsdealMapper.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Goodsdeal> getObjectList(GoodsdealExample example) {
		return this.goodsdealMapper.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(GoodsdealExample example) {
		return Integer.valueOf(this.goodsdealMapper.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(GoodsdealExample example) {
		return null;
	}

	public int countByExample(GoodsdealExample example) {
		return this.goodsdealMapper.countByExample(example);
	}

	@Override
	public List<Goodsdeal> selectByExample(GoodsdealExample paramDetailExample) {
		return null;
	}

	@Override
	@Transactional
	public String deal(Goodsdeal gd, String type) {
		try {
			if (type.equals("2")) {
				gd.setStatus("finish");
			} else {
				gd.setStatus("send");
			}

			goodsdealMapper.updateByPrimaryKeySelective(gd);

			Detail2 d2 = detailDao.selectByPrimaryKey(gd.getDetailid());
			if (type.equals("2")) {
				d2.setStatus("finish");
			} else {
				d2.setStatus("hassend");
			}

			detailDao.updateByPrimaryKeySelective(d2);
			return "success";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "fail";
		}
	}

}
