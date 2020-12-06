package com.chen.xbshop.service;

import com.chen.xbshop.dao.ShopDao;
import com.chen.xbshop.pojo.Shop;
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
 * @Description shop 服务层
 * @author czw
 * @date 2020-12-05 16:50:23
*/
@Service
public class ShopService {

	@Autowired
	private ShopDao shopDao;


	/**
	* 查询全部列表
	* @return
	*/
	public List<Shop> findAll() {
		return shopDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Shop> findSearch(Map whereMap, int page, int size) {
		Specification<Shop> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return shopDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Shop> findSearch(Map whereMap) {
		Specification<Shop> specification = createSpecification(whereMap);
		return shopDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Shop findById(String id) {
		return shopDao.findById(id).get();
	}

	/**
	* 增加
	* @param shop
	*/
	public void add(Shop shop) {
		shopDao.save(shop);
	}

	/**
	* 修改
	* @param shop
	*/
	public void update(Shop shop) {
		shopDao.save(shop);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		shopDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Shop> createSpecification(Map searchMap) {

		return new Specification<Shop>() {

			@Override
			public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//店铺名称
				if (searchMap.get("shopName")!=null && !"".equals(searchMap.get("shopName"))) {
					predicateList.add(cb.like(root.get("shopName").as(String.class), "%"+(String)searchMap.get("shopName")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
