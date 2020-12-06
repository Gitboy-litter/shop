package com.chen.xbshop.service;

import com.chen.xbshop.dao.ProductDao;
import com.chen.xbshop.pojo.PageResult;
import com.chen.xbshop.pojo.Product;
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
 * @author czw
 * @Description product 服务层
 * @date 2020-12-05 16:50:23
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Product> findSearch(Map whereMap, int page, int size) {
        Specification<Product> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Product> findSearch(Map whereMap) {
        Specification<Product> specification = createSpecification(whereMap);
        return productDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Product findById(Integer id) {
        return productDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param product
     */
    public void add(Product product) {
        productDao.save(product);
    }

    /**
     * 修改
     *
     * @param product
     */
    public void update(Product product) {
        productDao.save(product);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Product> createSpecification(Map searchMap) {

        return new Specification<Product>() {

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //商品名称
                if (searchMap.get("productName") != null && !"".equals(searchMap.get("productName"))) {
                    predicateList.add(cb.like(root.get("productName").as(String.class), "%" + (String) searchMap.get("productName") + "%"));
                }
                //商品内容
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                //浏览量
                if (searchMap.get("look") != null && !"".equals(searchMap.get("look"))) {
                    predicateList.add(cb.like(root.get("look").as(String.class), "%" + (String) searchMap.get("look") + "%"));
                }
                //商品价格
                if (searchMap.get("price") != null && !"".equals(searchMap.get("price"))) {
                    predicateList.add(cb.like(root.get("price").as(String.class), "%" + (String) searchMap.get("price") + "%"));
                }
                //商品图片
                if (searchMap.get("pic") != null && !"".equals(searchMap.get("pic"))) {
                    predicateList.add(cb.like(root.get("pic").as(String.class), "%" + (String) searchMap.get("pic") + "%"));
                }
                //发布时间
                if (searchMap.get("publishDate") != null && !"".equals(searchMap.get("publishDate"))) {
                    predicateList.add(cb.like(root.get("publishDate").as(String.class), "%" + (String) searchMap.get("publishDate") + "%"));
                }
                //星级 0-5低到高
                if (searchMap.get("level") != null && !"".equals(searchMap.get("level"))) {
                    predicateList.add(cb.like(root.get("level").as(String.class), "%" + (String) searchMap.get("level") + "%"));
                }
                //商品类型
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
                }
                //成交量
                if (searchMap.get("turnover") != null && !"".equals(searchMap.get("turnover"))) {
                    predicateList.add(cb.like(root.get("turnover").as(String.class), "%" + (String) searchMap.get("turnover") + "%"));
                }
                //店铺id
                if (searchMap.get("shopId") != null && !"".equals(searchMap.get("shopId"))) {
                    predicateList.add(cb.like(root.get("shopId").as(String.class), "%" + (String) searchMap.get("shopId") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    public List<Product> findTop4ByOrderByPublishDateDesc() {
        return productDao.findTop4ByOrderByPublishDateDesc();
    }

    public List<Product> findTop4ByOrderByTurnover() {
        return productDao.findTop4ByOrderByTurnover();
    }

    public Page<Map<String,Object>> findByUid(Integer id, Integer pagenum) {
        return productDao.findByUid(id, PageRequest.of(pagenum - 1, PageResult.PAGE_SIZE));
    }
}
