package com.chen.xbshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chen.xbshop.pojo.ProductType;

/**
 * @Description product_type 数据访问接口
 * @date 2020-12-05 16:50:23
*/
public interface ProductTypeDao extends JpaRepository<ProductType,String>,JpaSpecificationExecutor<ProductType>{

}

