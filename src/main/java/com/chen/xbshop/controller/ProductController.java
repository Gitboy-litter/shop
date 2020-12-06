package com.chen.xbshop.controller;

import com.chen.xbshop.pojo.PageResult;
import com.chen.xbshop.pojo.Product;
import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.service.AppraiseService;
import com.chen.xbshop.service.ProductService;
import com.chen.xbshop.utlis.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author czw
 * @Description product 控制器层
 * @date 2020-12-05 16:50:23
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AppraiseService appraiseService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, "查询成功", productService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable Integer id) {
        return new Result(true, "查询成功", productService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Product> pageList = productService.findSearch(searchMap, page, size);
        return new Result(true, "查询成功", new PageResult<Product>(pageList.getTotalPages(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, "查询成功", productService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param product
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Product product) {
        productService.add(product);
        return new Result(true, "增加成功");
    }

    /**
     * 修改
     *
     * @param product
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Product product, @PathVariable Integer id) {
        product.setId(id);
        productService.update(product);
        return new Result(true, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        productService.deleteById(id);
        return new Result(true, "删除成功");
    }

    @RequestMapping(value = "/findProduct", method = RequestMethod.GET)
    public Result findProduct() {
        List<Product> products = productService.findTop4ByOrderByPublishDateDesc();
        return new Result(true, "查询成功", products);
    }

    @RequestMapping(value = "/findHot", method = RequestMethod.GET)
    public Result findHot() {
        List<Product> products = productService.findTop4ByOrderByTurnover();
        return new Result(true, "查询成功", products);
    }

    @RequestMapping(value = "/mycollect/{pagenum}", method = RequestMethod.GET)
    public Result mycollect(@PathVariable Integer pagenum) {
        Page<Map<String, Object>> mycollect = productService.findByUid(LoginUtil.getId(), pagenum);
        return new Result(true, "查询成功", new PageResult<>(mycollect.getTotalPages(), mycollect.getContent()));
    }
}
