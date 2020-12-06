package com.chen.xbshop.service;

import com.chen.xbshop.dao.ProductTypeDao;
import com.chen.xbshop.pojo.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description product_type 服务层
 * @author czw
 * @date 2020-12-05 16:50:23
*/
@Service
public class ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<ProductType> findAll() {
		return productTypeDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<ProductType> findSearch(Map whereMap, int page, int size) {
		Specification<ProductType> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return productTypeDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<ProductType> findSearch(Map whereMap) {
		Specification<ProductType> specification = createSpecification(whereMap);
		return productTypeDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public ProductType findById(String id) {
		return productTypeDao.findById(id).get();
	}

	/**
	* 增加
	* @param productType
	*/
	public void add(ProductType productType) {
		productTypeDao.save(productType);
	}

	/**
	* 修改
	* @param productType
	*/
	public void update(ProductType productType) {
		productTypeDao.save(productType);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		productTypeDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<ProductType> createSpecification(Map searchMap) {

		return new Specification<ProductType>() {

			@Override
			public Predicate toPredicate(Root<ProductType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
