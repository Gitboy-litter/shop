package com.chen.xbshop.controller;

import com.chen.xbshop.pojo.PageResult;
import com.chen.xbshop.pojo.ProductType;
import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author czw
 * @Description product_type 控制器层
 * @date 2020-12-05 16:50:23
 */
@RestController
@CrossOrigin
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, "查询成功", productTypeService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, "查询成功", productTypeService.findById(id));
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
        Page<ProductType> pageList = productTypeService.findSearch(searchMap, page, size);
        return new Result(true, "查询成功", new PageResult<ProductType>(pageList.getTotalPages(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, "查询成功", productTypeService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param productType
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody ProductType productType) {
        productTypeService.add(productType);
        return new Result(true, "增加成功");
    }


    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        productTypeService.deleteById(id);
        return new Result(true, "删除成功");
    }

}
