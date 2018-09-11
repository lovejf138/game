package com.webpos.service.serviceImpl;

import com.webpos.dao.SystemMapper;
import com.webpos.entity.DSystem;
import com.webpos.service.SystemService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl
  implements SystemService
{
  @Resource
  private SystemMapper systemDao;
  
  public DSystem selectAll()
  {
    return this.systemDao.selectAll();
  }
  
  public int updateAll(DSystem record)
  {
    return this.systemDao.updateAll(record);
  }
}
