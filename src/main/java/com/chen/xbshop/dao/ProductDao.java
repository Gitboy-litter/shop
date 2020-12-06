package com.chen.xbshop.dao;

import com.chen.xbshop.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @Description product 数据访问接口
 * @date 2020-12-05 16:50:23
 */
public interface ProductDao extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findTop4ByOrderByPublishDateDesc();

    List<Product> findTop4ByOrderByTurnover();

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
            "s.shopName as shopName from Product p left join Collect c on p.id=c.pid left join Shop s on s.id=p.shopId where c.uid=?1")
    Page<Map<String, Object>> findByUid(Integer id, Pageable p);
}

