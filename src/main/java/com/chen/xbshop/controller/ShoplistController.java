package com.chen.xbshop.controller;

import com.chen.xbshop.pojo.PageResult;
import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.pojo.Shoplist;
import com.chen.xbshop.service.ShoplistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author czw
 * @Description shoplist 控制器层
 * @date 2020-12-05 16:50:23
 */
@RestController
@CrossOrigin
@RequestMapping("/shoplist")
public class ShoplistController {

    @Autowired
    private ShoplistService shoplistService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, "查询成功", shoplistService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, "查询成功", shoplistService.findById(id));
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
        Page<Shoplist> pageList = shoplistService.findSearch(searchMap, page, size);
        return new Result(true, "查询成功", new PageResult<Shoplist>(pageList.getTotalPages(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, "查询成功", shoplistService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param shoplist
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Shoplist shoplist) {
        shoplistService.add(shoplist);
        return new Result(true, "增加成功");
    }

    /**
     * 修改
     *
     * @param shoplist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Shoplist shoplist, @PathVariable Integer id) {
        shoplist.setId(id);
        shoplistService.update(shoplist);
        return new Result(true, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        shoplistService.deleteById(id);
        return new Result(true, "删除成功");
    }

}
