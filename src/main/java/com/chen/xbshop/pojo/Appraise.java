package com.chen.xbshop.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author czw
 * @Description appraise实体类
 * @date 2020-12-05 16:50:23
 */
@Entity
@IdClass(Appraise.class)
@Table(name = "appraise")
public class Appraise implements Serializable {

    @Id
    private Integer uid;//主键ID
    @Id
    private Integer pid;//商品id
    private String content; //评价内容
    private java.util.Date appraiseDate; //评价时间
    private Integer score; //评分，0-5又高到低

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer aValue) {
        this.uid = aValue;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setAppraiseDate(java.util.Date appraiseDate) {
        this.appraiseDate = appraiseDate;
    }

    public java.util.Date getAppraiseDate() {
        return this.appraiseDate;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

}



