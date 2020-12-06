package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description shoplist实体类
 * @author czw
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name="shoplist")
public class Shoplist implements Serializable{

	@Id
	private Integer id;//主键ID

	private String name; //商品列表

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}



