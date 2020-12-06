package com.chen.xbshop.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author czw
 * @Description user实体类
 * @date 2020-12-05 16:50:23
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键ID

    private String username; //用户名
    private String password; //密码
    private String qqId; //qq登陆唯一标识
    private String xlId; //新浪登陆唯一标识
    private String wxId; //wx登陆唯一标识
    private Integer age; //年龄
    private Integer sex; //性别
    private String phone; //手机号
    private String email; //邮箱
    private String pic; //头像
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date registerTime; //注册时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date loginTime; //上次登录时间
    private Integer defaultArea; //默认收货地址

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer aValue) {
        this.id = aValue;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public String getQqId() {
        return this.qqId;
    }

    public void setXlId(String xlId) {
        this.xlId = xlId;
    }

    public String getXlId() {
        return this.xlId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxId() {
        return this.wxId;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return this.pic;
    }

    public void setRegisterTime(java.util.Date registerTime) {
        this.registerTime = registerTime;
    }

    public java.util.Date getRegisterTime() {
        return this.registerTime;
    }

    public void setLoginTime(java.util.Date loginTime) {
        this.loginTime = loginTime;
    }

    public java.util.Date getLoginTime() {
        return this.loginTime;
    }

    public void setDefaultArea(Integer defaultArea) {
        this.defaultArea = defaultArea;
    }

    public Integer getDefaultArea() {
        return this.defaultArea;
    }

}



