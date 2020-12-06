package com.chen.xbshop.service;

import com.chen.xbshop.dao.AreaDao;
import com.chen.xbshop.pojo.Area;
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
 * @Description area 服务层
 * @author czw
 * @date 2020-12-05 16:50:23
*/
@Service
public class AreaService {

	@Autowired
	private AreaDao areaDao;


	/**
	* 查询全部列表
	* @return
	*/
	public List<Area> findAll() {
		return areaDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Area> findSearch(Map whereMap, int page, int size) {
		Specification<Area> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return areaDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Area> findSearch(Map whereMap) {
		Specification<Area> specification = createSpecification(whereMap);
		return areaDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Area findById(Integer id) {
		return areaDao.findById(id).get();
	}

	/**
	* 增加
	* @param area
	*/
	public void add(Area area) {
		areaDao.save(area);
	}

	/**
	* 修改
	* @param area
	*/
	public void update(Area area) {
		areaDao.save(area);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(Integer id) {
		areaDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Area> createSpecification(Map searchMap) {

		return new Specification<Area>() {

			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//收货人
				if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
					predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
				}
				//收货电话
				if (searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))) {
					predicateList.add(cb.like(root.get("phone").as(String.class), "%"+(String)searchMap.get("phone")+"%"));
				}
				//收货地址
				if (searchMap.get("area")!=null && !"".equals(searchMap.get("area"))) {
					predicateList.add(cb.like(root.get("area").as(String.class), "%"+(String)searchMap.get("area")+"%"));
				}
				//详细地址
				if (searchMap.get("detail")!=null && !"".equals(searchMap.get("detail"))) {
					predicateList.add(cb.like(root.get("detail").as(String.class), "%"+(String)searchMap.get("detail")+"%"));
				}
				//邮政编码
				if (searchMap.get("emailCode")!=null && !"".equals(searchMap.get("emailCode"))) {
					predicateList.add(cb.like(root.get("emailCode").as(String.class), "%"+(String)searchMap.get("emailCode")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
