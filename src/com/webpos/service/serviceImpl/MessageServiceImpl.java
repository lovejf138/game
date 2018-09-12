package com.webpos.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webpos.dao.MessageMapper;
import com.webpos.entity.Message;
import com.webpos.entity.MessageExample;
import com.webpos.service.MessageService;
import com.webpos.tools.Pagination;

@Service
public class MessageServiceImpl implements MessageService {
	@Resource
	private MessageMapper messageDao;

	public int insert(Message record) {
		return this.messageDao.insert(record);
	}

	@Transactional(readOnly = true)
	public Pagination getObjectListWithPage(MessageExample example) {
		Pagination p = new Pagination(example.getPageNo(), example.getPageSize(),
				this.messageDao.countByExample(example));
		p.setList(this.messageDao.selectByExampleWithBLOBsAndPage(example));
		return p;
	}

	@Transactional(readOnly = true)
	public List<Message> getObjectList(MessageExample example) {
		return this.messageDao.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public Integer getObjectListCount(MessageExample example) {
		return Integer.valueOf(this.messageDao.countByExample(example));
	}

	
	public int countByExample(MessageExample example) {
		return this.messageDao.countByExample(example);
	}

	
}
