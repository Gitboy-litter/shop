package com.chen.xbshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chen.xbshop.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

/**
 * @Description user 数据访问接口
 * @date 2020-12-05 16:50:23
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUsernameAndPassword(String username, String password);

    User findByEmail(String email);

    @Modifying
    @Query("update User set password=?2 where email=?1")
    void updateByEmail(String email, String password);

    User findByEmailAndUsername(String email, String username);

    User findByUsername(String name);

    @Query("select " +
            "u.id as id," +
            "u.username as username," +
            "u.password as password," +
            "u.qqId  as qqId," +
            "u.xlId  as xlId," +
            "u.wxId  as wxId," +
            "u.age  as age," +
            "u.sex  as sex," +
            "u.phone  as phone," +
            "u.email  as email ," +
            "u.pic  as pic," +
            "u.registerTime  as registerTime," +
            "u.loginTime  as loginTime," +
            "u.defaultArea  as defaultArea," +
            "a.area as area," +
            "a.detail as detail from User u left join Area a on u.defaultArea=a.id where u.id=?1")
    Map<String, Object> findUserAndAddresss(Integer id);

}

