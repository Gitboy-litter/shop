package com.chen.xbshop.service;

import com.chen.xbshop.dao.UserDao;
import com.chen.xbshop.pojo.User;
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
 * @Description user 服务层
 * @date 2020-12-05 16:50:23
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(Integer id) {
        return userDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param user
     */
    public void add(User user) {
        userDao.save(user);
    }

    /**
     * 修改
     *
     * @param user
     */
    public void update(User user) {
        userDao.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //用户名
                if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.like(root.get("username").as(String.class), "%" + (String) searchMap.get("username") + "%"));
                }
                //密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                //qq登陆唯一标识
                if (searchMap.get("qqId") != null && !"".equals(searchMap.get("qqId"))) {
                    predicateList.add(cb.like(root.get("qqId").as(String.class), "%" + (String) searchMap.get("qqId") + "%"));
                }
                //新浪登陆唯一标识
                if (searchMap.get("xlId") != null && !"".equals(searchMap.get("xlId"))) {
                    predicateList.add(cb.like(root.get("xlId").as(String.class), "%" + (String) searchMap.get("xlId") + "%"));
                }
                //wx登陆唯一标识
                if (searchMap.get("wxId") != null && !"".equals(searchMap.get("wxId"))) {
                    predicateList.add(cb.like(root.get("wxId").as(String.class), "%" + (String) searchMap.get("wxId") + "%"));
                }
                //年龄
                if (searchMap.get("age") != null && !"".equals(searchMap.get("age"))) {
                    predicateList.add(cb.like(root.get("age").as(String.class), "%" + (String) searchMap.get("age") + "%"));
                }
                //性别
                if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
                }
                //手机号
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }
                //邮箱
                if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
                }
                //头像
                if (searchMap.get("pic") != null && !"".equals(searchMap.get("pic"))) {
                    predicateList.add(cb.like(root.get("pic").as(String.class), "%" + (String) searchMap.get("pic") + "%"));
                }
                //注册时间
                if (searchMap.get("registerTime") != null && !"".equals(searchMap.get("registerTime"))) {
                    predicateList.add(cb.like(root.get("registerTime").as(String.class), "%" + (String) searchMap.get("registerTime") + "%"));
                }
                //上次登录时间
                if (searchMap.get("loginTime") != null && !"".equals(searchMap.get("loginTime"))) {
                    predicateList.add(cb.like(root.get("loginTime").as(String.class), "%" + (String) searchMap.get("loginTime") + "%"));
                }
                //默认收货地址
                if (searchMap.get("defaultArea") != null && !"".equals(searchMap.get("defaultArea"))) {
                    predicateList.add(cb.like(root.get("defaultArea").as(String.class), "%" + (String) searchMap.get("defaultArea") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    public User findByEmail(String email) {

        return userDao.findByEmail(email);
    }

    @Transactional
    public void updateByEmail(String email, String password) {
        userDao.updateByEmail(email, password);
    }

    public User findByEmailAndUsername(String email, String username) {
        return userDao.findByEmailAndUsername(email, username);
    }

    public User findByUsername(String name) {
        return userDao.findByUsername(name);
    }

    public Map<String, Object> findUserAndAddresss(Integer id) {

        return userDao.findUserAndAddresss(id);
    }
}
