package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description area实体类
 * @author czw
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name="area")
public class Area implements Serializable{

	@Id
	private Integer id;//主键ID

	private String name; //收货人
	private Integer phone; //收货电话
	private String area; //收货地址
	private String detail; //详细地址
	private Integer emailCode; //邮政编码

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
	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getPhone() {
		return this.phone;
	}
	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return this.area;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return this.detail;
	}
	public void setEmailCode(Integer emailCode) {
		this.emailCode = emailCode;
	}

	public Integer getEmailCode() {
		return this.emailCode;
	}

}



