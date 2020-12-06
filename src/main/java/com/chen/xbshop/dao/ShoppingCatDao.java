package com.chen.xbshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chen.xbshop.pojo.ShoppingCat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @Description shopping_cat 数据访问接口
 * @date 2020-12-05 16:50:23
 */
public interface ShoppingCatDao extends JpaRepository<ShoppingCat, String>, JpaSpecificationExecutor<ShoppingCat> {

    ShoppingCat findByUidAndPid(Integer uid, Integer pid);

    @Query("select p.id as id," +
            "p.productName as productName, " +
            "p.title as title , " +
            "p.look as look, " +
            "p.price as price, " +
            "p.pic as pic, " +
            "p.publishDate as publishDate, " +
            "p.level as level, " +
            "p.type as type, " +
            "p.turnover as turnover, " +
            "p.shopId as shopId, " +
            "sc.uid as uid, " +
            "sc.productCount as productCount, " +
            "sc.status as status," +
            "sc.orderStatus as orderStatus  from ShoppingCat sc left join Product p on sc.pid=p.id where sc.uid=?1 and sc.status=?2")
    List<Map<String, Object>> findByUid(Integer id, Integer status);

    @Modifying
    @Query("delete from  ShoppingCat where uid=?2 and  pid=?1")
    void deleteByIdAndUid(Integer id, Integer userId);

    @Modifying
    @Query("update ShoppingCat set status=?3 where uid=?2 and  pid=?1")
    void updateStatus(Integer id, Integer userId, Integer status);

    @Query("update ShoppingCat set orderStatus=1,isApprise=0 where uid=?1 and  pid=?2  ")
    @Modifying
    void updateOrderStatus(Integer uid, Integer pid);

    @Query("select p.id as id," +
            "p.productName as productName, " +
            "p.title as title , " +
            "p.look as look, " +
            "p.price as price, " +
            "p.pic as pic, " +
            "p.publishDate as publishDate, " +
            "p.level as level, " +
            "p.type as type, " +
            "p.turnover as turnover, " +
            "p.shopId as shopId, " +
            "sc.uid as uid, " +
            "sc.productCount as productCount, " +
            "sc.status as status," +
            "sc.orderStatus as orderStatus," +
            "sc.isApprise as isApprise from ShoppingCat sc left join Product p on sc.pid=p.id where sc.uid=?1 and sc.isApprise=0")
    List<Map<String, Object>> findByIsApprise(Integer id);

    Integer countAllByStatusAndUid(int i, Integer uid);
}

