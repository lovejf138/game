package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webpos.dao.RoomMapper;
import com.webpos.entity.Room;
import com.webpos.entity.RoomExample;
import com.webpos.service.RoomService;
import com.webpos.tools.Pagination;

@Service
public class RoomServiceImpl implements RoomService {
	@Resource
	private RoomMapper roomDao;

	public Room selectByPrimaryKey(Long id) {
		return this.roomDao.selectByPrimaryKey(id);
	}

	public int insert(Room m) {
		return 0;
	}
	
	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(RoomExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.roomDao.countByExample(example));
		p.setList(this.roomDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Room> getObjectList(RoomExample example) {
		return this.roomDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(RoomExample example) {
		return Integer.valueOf(this.roomDao.countByExample(example));
	}

	public Pagination getObjectListWithPageAdmin(RoomExample example) {
		return null;
	}

	public int countByExample(RoomExample example) {
		return this.roomDao.countByExample(example);
	}

	public List<Room> selectByExample(RoomExample example) {
		return null;
	}

}
