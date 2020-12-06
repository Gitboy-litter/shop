package com.chen.xbshop.service;

import com.chen.xbshop.dao.AppraiseDao;
import com.chen.xbshop.pojo.Appraise;
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
 * @Description appraise 服务层
 * @author czw
 * @date 2020-12-05 16:50:23
*/
@Service
public class AppraiseService {

	@Autowired
	private AppraiseDao appraiseDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<Appraise> findAll() {
		return appraiseDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Appraise> findSearch(Map whereMap, int page, int size) {
		Specification<Appraise> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return appraiseDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Appraise> findSearch(Map whereMap) {
		Specification<Appraise> specification = createSpecification(whereMap);
		return appraiseDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Appraise findById(String id) {
		return appraiseDao.findById(id).get();
	}

	/**
	* 增加
	* @param appraise
	*/
	public void add(Appraise appraise) {
		appraiseDao.save(appraise);
	}

	/**
	* 修改
	* @param appraise
	*/
	public void update(Appraise appraise) {
		appraiseDao.save(appraise);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		appraiseDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Appraise> createSpecification(Map searchMap) {

		return new Specification<Appraise>() {

			@Override
			public Predicate toPredicate(Root<Appraise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//评价内容
				if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
					predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
				}
				//评价时间
				if (searchMap.get("appraiseDate")!=null && !"".equals(searchMap.get("appraiseDate"))) {
					predicateList.add(cb.like(root.get("appraiseDate").as(String.class), "%"+(String)searchMap.get("appraiseDate")+"%"));
				}
				//评分，0-5又高到低
				if (searchMap.get("score")!=null && !"".equals(searchMap.get("score"))) {
					predicateList.add(cb.like(root.get("score").as(String.class), "%"+(String)searchMap.get("score")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
