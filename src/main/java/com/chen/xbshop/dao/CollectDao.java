package com.chen.xbshop.dao;

import com.chen.xbshop.pojo.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @ProjectName: shop
 * @Package: com.chen.xbshop.dao
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/12/5 17:03
 * @Version: 1.0
 */
public interface CollectDao extends JpaRepository<Collect, Integer>, JpaSpecificationExecutor<Collect> {
    Collect findByUidAndPid(Integer id, Integer pid);

    @Query("delete from Collect where uid=?1 and pid=?2")
    @Modifying
    void remove(Integer uid, Integer pid);
}
