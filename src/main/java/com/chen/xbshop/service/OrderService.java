package com.chen.xbshop.service;

import com.chen.xbshop.dao.OrderDao;
import com.chen.xbshop.pojo.Order;
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
 * @Description order 服务层
 * @author czw
 * @date 2020-12-05 16:50:23
*/
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Order> findSearch(Map whereMap, int page, int size) {
		Specification<Order> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return orderDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Order> findSearch(Map whereMap) {
		Specification<Order> specification = createSpecification(whereMap);
		return orderDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Order findById(String id) {
		return orderDao.findById(id).get();
	}

	/**
	* 增加
	* @param order
	*/
	public void add(Order order) {

		orderDao.save(order);
	}

	/**
	* 修改
	* @param order
	*/
	public void update(Order order) {
		orderDao.save(order);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		orderDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Order> createSpecification(Map searchMap) {

		return new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//订单状态 0:没确认收货 1:已经确认收货
				if (searchMap.get("orderStatus")!=null && !"".equals(searchMap.get("orderStatus"))) {
					predicateList.add(cb.like(root.get("orderStatus").as(String.class), "%"+(String)searchMap.get("orderStatus")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
