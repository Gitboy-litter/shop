package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description product_type实体类
 * @author czw
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name="product_type")
public class ProductType implements Serializable{

	@Id
	private Integer pid;//主键ID


	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer aValue) {
		this.pid = aValue;
	}
	

}



