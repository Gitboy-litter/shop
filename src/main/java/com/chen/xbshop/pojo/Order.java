package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description order实体类
 * @author czw
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name="order")
public class Order implements Serializable{

	@Id
	private Integer pid;//主键ID

	private String orderStatus; //订单状态 0:没确认收货 1:已经确认收货

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer aValue) {
		this.pid = aValue;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

}



