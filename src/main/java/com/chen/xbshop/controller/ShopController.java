package com.chen.xbshop.controller;

import com.chen.xbshop.pojo.PageResult;
import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.pojo.Shop;
import com.chen.xbshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
* @Description shop 控制器层
* @author czw
* @date 2020-12-05 16:50:23
*/
@RestController
@CrossOrigin
@RequestMapping("/shop")
public class ShopController{

@Autowired
private ShopService shopService;

/**
* 查询全部数据
* @return
*/
@RequestMapping(method= RequestMethod.GET)
public Result findAll(){
return new Result(true,"查询成功",shopService.findAll());
}

/**
* 根据ID查询
* @param id ID
* @return
*/
@RequestMapping(value="/{id}",method= RequestMethod.GET)
public Result findById(@PathVariable String id){
return new Result(true,"查询成功",shopService.findById(id));
}

/**
* 分页+多条件查询
* @param searchMap 查询条件封装
* @param page 页码
* @param size 页大小
* @return 分页结果
*/
@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
Page<Shop> pageList = shopService.findSearch(searchMap, page, size);
return  new Result(true,"查询成功",  new PageResult<Shop>(pageList.getTotalPages(), pageList.getContent()) );
}

/**
* 根据条件查询
* @param searchMap
* @return
*/
@RequestMapping(value="/search",method = RequestMethod.POST)
public Result findSearch( @RequestBody Map searchMap){
return new Result(true,"查询成功", shopService.findSearch(searchMap));
}

/**
* 增加
* @param shop
*/
@RequestMapping(method=RequestMethod.POST)
public Result add(@RequestBody  Shop  shop  ){
shopService.add( shop);
return new Result(true,"增加成功");
}

/**
* 修改
* @param shop
*/
@RequestMapping(value="/{id}",method= RequestMethod.PUT)
public Result update(@RequestBody  Shop  shop, @PathVariable Integer id ){
shop.setId(id);
shopService.update( shop);
return new Result(true,"修改成功");
}

/**
* 删除
* @param id
*/
@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
public Result delete(@PathVariable String id ){
shopService.deleteById(id);
return new Result(true,"删除成功");
}

}
