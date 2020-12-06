package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * @ProjectName: shop
 * @Package: com.chen.xbshop.pojo
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/12/5 17:04
 * @Version: 1.0
 */
@Entity
@IdClass(Collect.class)
public class Collect implements Serializable {
    @Id
    private Integer uid;
    @Id
    private Integer pid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
