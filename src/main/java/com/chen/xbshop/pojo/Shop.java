package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description shop实体类
 * @author czw
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name="shop")
public class Shop implements Serializable{

	@Id
	private Integer id;//主键ID

	private String shopName; //店铺名称

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopName() {
		return this.shopName;
	}

}



