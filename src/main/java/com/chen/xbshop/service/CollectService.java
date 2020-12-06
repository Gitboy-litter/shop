package com.chen.xbshop.service;

import com.chen.xbshop.dao.CollectDao;
import com.chen.xbshop.pojo.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: shop
 * @Package: com.chen.xbshop.service
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/12/5 17:05
 * @Version: 1.0
 */
@Service
public class CollectService {
    @Autowired
    private CollectDao collectDao;

    public void add(Collect collect) {
        collectDao.save(collect);
    }

    public Collect findByUidAndPid(Integer id, Integer pid) {
        return collectDao.findByUidAndPid(id, pid);
    }

    @Transactional
    public void remove(Collect collect) {
        collectDao.remove(collect.getUid(), collect.getPid());
    }
}
