package com.chen.xbshop.service;

import com.chen.xbshop.dao.ShoppingCatDao;
import com.chen.xbshop.pojo.ShoppingCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author czw
 * @Description shopping_cat 服务层
 * @date 2020-12-05 16:50:23
 */
@Service
public class ShoppingCatService {

    @Autowired
    private ShoppingCatDao shoppingCatDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ShoppingCat> findAll() {
        return shoppingCatDao.findAll();
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ShoppingCat> findSearch(Map whereMap, int page, int size) {
        Specification<ShoppingCat> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return shoppingCatDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ShoppingCat> findSearch(Map whereMap) {
        Specification<ShoppingCat> specification = createSpecification(whereMap);
        return shoppingCatDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public ShoppingCat findById(String id) {
        return shoppingCatDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param shoppingCat
     */
    public void add(ShoppingCat shoppingCat) {
        shoppingCatDao.save(shoppingCat);
    }

    /**
     * 修改
     *
     * @param shoppingCat
     */
    public void update(ShoppingCat shoppingCat) {
        shoppingCatDao.save(shoppingCat);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        shoppingCatDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ShoppingCat> createSpecification(Map searchMap) {

        return new Specification<ShoppingCat>() {

            @Override
            public Predicate toPredicate(Root<ShoppingCat> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //数量
                if (searchMap.get("productCount") != null && !"".equals(searchMap.get("productCount"))) {
                    predicateList.add(cb.like(root.get("productCount").as(String.class), "%" + (String) searchMap.get("productCount") + "%"));
                }
                //商品支付状态 1:在购物车中 2:待支付
                if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(root.get("status").as(String.class), "%" + (String) searchMap.get("status") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    public ShoppingCat findByUidAndPid(Integer uid, Integer pid) {
        return shoppingCatDao.findByUidAndPid(uid, pid);
    }

    public List<Map<String, Object>> findByUid(Integer id, Integer status) {
        return shoppingCatDao.findByUid(id, status);
    }

    @Transactional
    public void deleteByIdAndUid(Integer id, Integer userId) {
        shoppingCatDao.deleteByIdAndUid(id, userId);
    }

    @Transactional
    public void updateStatus(Integer id, Integer userId, Integer status) {
        shoppingCatDao.updateStatus(id, userId, status);
    }

    @Transactional
    public void updateOrderStatus(Integer uid, Integer pid) {
        shoppingCatDao.updateOrderStatus(uid, pid);
    }

    public List<Map<String, Object>> findByIsApprise(Integer id) {
        return shoppingCatDao.findByIsApprise(id);
    }

    public Integer countAllByStatusAndUid(Integer uid) {
        return shoppingCatDao.countAllByStatusAndUid(0,uid);
    }
}
