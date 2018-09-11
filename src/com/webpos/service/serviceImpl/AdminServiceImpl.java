package com.webpos.service.serviceImpl;

import com.webpos.dao.AdminMapper;
import com.webpos.entity.Admin;
import com.webpos.service.AdminService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl
  implements AdminService
{
  @Resource
  private AdminMapper adminDao;
  
  public Admin selectByName(String name)
  {
    return this.adminDao.selectByName(name);
  }
}
