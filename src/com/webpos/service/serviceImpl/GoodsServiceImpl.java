package com.webpos.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.webpos.dao.Detail2Mapper;
import com.webpos.dao.GoodsMapper;
import com.webpos.dao.UserMapper;
import com.webpos.entity.Detail2;
import com.webpos.entity.Goods;
import com.webpos.entity.GoodsExample;
import com.webpos.entity.User;
import com.webpos.service.GoodsService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.Pagination;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsDao;
	@Resource
	private UserMapper userDao;
	@Resource
	private Detail2Mapper detailDao;

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

	@Override
	@Transactional
	public String buy2(User user, Goods goods, double actualprice, String nexname, String fangan) {
		String r = "SUCCESS";
		try {
			
			Detail2 d = new Detail2();
			d.setUserid(user.getPhone());
			d.setQiname(nexname);
			d.setCtime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        d.setKjtime(sdf.parse(CommUtil.getTimeFromQishu(nexname)));
			d.setPrice(goods.getPrice());
			d.setNowprice(goods.getNowprice());
			d.setNumber1(Integer.parseInt(fangan));
			d.setParentid(user.getParent());
			d.setGoodsid(Integer.parseInt(""+goods.getId()));
			d.setGoodsname(goods.getName());
			d.setType(2);
			d.setActualprice(actualprice);
			detailDao.insert(d);
			
			User u = userDao.selectByPhone(user.getPhone());
			u.setPlay_sum(u.getPlay_sum()+actualprice);
			u.setBalance(u.getBalance()-actualprice);
			this.userDao.updateByPrimaryKeySelective(u);
			
			if(u.getParent()!=null&&u.getParent().length()>5) {
				User up = userDao.selectByParent(user.getParent());
				Double b = CommUtil.mul(actualprice, 0.01);
				up.setBalance(CommUtil.add(up.getBalance(), b));
				this.userDao.updateByPrimaryKeySelective(up);
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "未知错误:"+e.getMessage().toString();
		}
		return r;
	}

	@Override
	@Transactional
	public String buy6(User user, Goods goods, double actualprice, String nexname, String fangan) {

		String r = "SUCCESS";
		try {
			
			Detail2 d = new Detail2();
			d.setUserid(user.getPhone());
			d.setQiname(nexname);
			d.setCtime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        d.setKjtime(sdf.parse(CommUtil.getTimeFromQishu(nexname)));
			d.setPrice(goods.getPrice());
			d.setNowprice(goods.getNowprice());
			d.setNumber2(fangan);
			d.setParentid(user.getParent());
			d.setGoodsid(Integer.parseInt(""+goods.getId()));
			d.setGoodsname(goods.getName());
			d.setType(6);
			d.setActualprice(actualprice);
			detailDao.insert(d);
			
			User u = userDao.selectByPhone(user.getPhone());
			u.setPlay_sum(u.getPlay_sum()+actualprice);
			u.setBalance(u.getBalance()-actualprice);
			this.userDao.updateByPrimaryKeySelective(u);
			
			if(u.getParent()!=null&&u.getParent().length()>5) {
				User up = userDao.selectByParent(user.getParent());
				Double b = CommUtil.mul(actualprice, 0.01);
				up.setBalance(CommUtil.add(up.getBalance(), b));
				this.userDao.updateByPrimaryKeySelective(up);
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "未知错误:"+e.getMessage().toString();
		}
		return r;
	}

	@Override
	@Transactional
	public String buy250(User user, Goods goods, double actualprice, String nexname, String fangan) {
		String r = "SUCCESS";
		try {
			
			Detail2 d = new Detail2();
			d.setUserid(user.getPhone());
			d.setQiname(nexname);
			d.setCtime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        d.setKjtime(sdf.parse(CommUtil.getTimeFromQishu(nexname)));
			d.setPrice(goods.getPrice());
			d.setNowprice(goods.getNowprice());
			d.setNumber3(fangan);
			d.setParentid(user.getParent());
			d.setGoodsid(Integer.parseInt(""+goods.getId()));
			d.setGoodsname(goods.getName());
			d.setType(250);
			d.setActualprice(actualprice);
			detailDao.insert(d);
			
			User u = userDao.selectByPhone(user.getPhone());
			u.setPlay_sum(u.getPlay_sum()+actualprice);
			u.setBalance(u.getBalance()-actualprice);
			this.userDao.updateByPrimaryKeySelective(u);
			
			if(u.getParent()!=null&&u.getParent().length()>5) {
				User up = userDao.selectByParent(user.getParent());
				Double b = CommUtil.mul(actualprice, 0.01);
				up.setBalance(CommUtil.add(up.getBalance(), b));
				this.userDao.updateByPrimaryKeySelective(up);
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "未知错误:"+e.getMessage().toString();
		}
		return r;
	}

}
