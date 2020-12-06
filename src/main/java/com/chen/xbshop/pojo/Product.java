package com.chen.xbshop.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author czw
 * @Description product实体类
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    private Integer id;//主键ID

    private String productName; //商品名称
    private String title; //商品内容
    private String look; //浏览量
    private Double price; //商品价格
    private String pic; //商品图片
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date publishDate; //发布时间
    private Integer level; //星级 0-5低到高
    private String type; //商品类型
    private Integer turnover; //成交量
    private Integer shopId; //店铺id

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer aValue) {
        this.id = aValue;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public String getLook() {
        return this.look;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPublishDate(java.util.Date publishDate) {
        this.publishDate = publishDate;
    }

    public java.util.Date getPublishDate() {
        return this.publishDate;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public Integer getTurnover() {
        return this.turnover;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getShopId() {
        return this.shopId;
    }

}



