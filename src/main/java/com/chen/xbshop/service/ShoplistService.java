package com.chen.xbshop.service;

import com.chen.xbshop.dao.ShoplistDao;
import com.chen.xbshop.pojo.Shoplist;
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
 * @Description shoplist 服务层
 * @author czw
 * @date 2020-12-05 16:50:23
*/
@Service
public class ShoplistService {

	@Autowired
	private ShoplistDao shoplistDao;


	/**
	* 查询全部列表
	* @return
	*/
	public List<Shoplist> findAll() {
		return shoplistDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Shoplist> findSearch(Map whereMap, int page, int size) {
		Specification<Shoplist> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return shoplistDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Shoplist> findSearch(Map whereMap) {
		Specification<Shoplist> specification = createSpecification(whereMap);
		return shoplistDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Shoplist findById(String id) {
		return shoplistDao.findById(id).get();
	}

	/**
	* 增加
	* @param shoplist
	*/
	public void add(Shoplist shoplist) {
		shoplistDao.save(shoplist);
	}

	/**
	* 修改
	* @param shoplist
	*/
	public void update(Shoplist shoplist) {
		shoplistDao.save(shoplist);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		shoplistDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Shoplist> createSpecification(Map searchMap) {

		return new Specification<Shoplist>() {

			@Override
			public Predicate toPredicate(Root<Shoplist> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//商品列表
				if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
					predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
