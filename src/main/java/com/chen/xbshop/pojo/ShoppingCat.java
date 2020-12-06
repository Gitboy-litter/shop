package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author czw
 * @Description shopping_cat实体类
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name = "shopping_cat")
@IdClass(ShoppingCat.class)
public class ShoppingCat implements Serializable {

    @Id
    private Integer pid;
    @Id
    private Integer uid;
    private Integer productCount; //数量
    private Integer status; //商品支付状态 1:在购物车中 2:待支付
    private Integer orderStatus;
    private Integer isApprise;

    public Integer getIsApprise() {
        return isApprise;
    }

    public void setIsApprise(Integer isApprise) {
        this.isApprise = isApprise;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer aValue) {
        this.pid = aValue;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getProductCount() {
        return this.productCount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

}



