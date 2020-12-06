package com.chen.xbshop.controller;

import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.pojo.ShoppingCat;
import com.chen.xbshop.service.ShoppingCatService;
import com.chen.xbshop.utlis.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author czw
 * @Description shopping_cat 控制器层
 * @date 2020-12-05 16:50:23
 */
@RestController
@CrossOrigin
@RequestMapping("/shoppingCat")
public class ShoppingCatController {

    @Autowired
    private ShoppingCatService shoppingCatService;

    /**
     * 增加
     *
     * @param shoppingCat
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody ShoppingCat shoppingCat) {
        ShoppingCat sc = shoppingCatService.findByUidAndPid(LoginUtil.getId(), shoppingCat.getPid());
        if (sc != null) {
            return new Result(true, "已经存在购物车", false);
        }
        shoppingCat.setUid(LoginUtil.getId());
        shoppingCat.setStatus(1);
        shoppingCat.setStatus(0);
        shoppingCatService.add(shoppingCat);
        return new Result(true, "增加成功", true);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        shoppingCatService.deleteByIdAndUid(id, LoginUtil.getId());
        return new Result(true, "删除成功", true);
    }

    @RequestMapping(value = "payment/{id}", method = RequestMethod.PUT)
    public Result payment(@PathVariable Integer id) {
        shoppingCatService.updateStatus(id, LoginUtil.getId(), 2);
        return new Result(true, "修改成功", true);
    }

    @RequestMapping(value = "mycar", method = RequestMethod.GET)
    public Result mycar() {
        List<Map<String, Object>> list = shoppingCatService.findByUid(LoginUtil.getId(), 1);
        return new Result(true, "查询成功", list);
    }

    @RequestMapping(value = "mywaitpay", method = RequestMethod.GET)
    public Result mywaitpay() {
        List<Map<String, Object>> list = shoppingCatService.findByUid(LoginUtil.getId(), 2);
        return new Result(true, "查询成功", list);
    }

    @RequestMapping(value = "myorder", method = RequestMethod.GET)
    public Result myorder() {
        List<Map<String, Object>> list = shoppingCatService.findByUid(LoginUtil.getId(), 0);
        return new Result(true, "查询成功", list);
    }

    @RequestMapping(value = "isapprise", method = RequestMethod.GET)
    public Result isapprise() {
        List<Map<String, Object>> list = shoppingCatService.findByIsApprise(LoginUtil.getId());
        return new Result(true, "查询成功", list);
    }

    @RequestMapping(value = "realpayment/{id}", method = RequestMethod.PUT)
    public Result realpayment(@PathVariable Integer id) {
        shoppingCatService.updateStatus(id, LoginUtil.getId(), 0);
        return new Result(true, "修改成功", true);
    }

    @RequestMapping(value = "confirproduct/{id}", method = RequestMethod.PUT)
    public Result confirproduct(@PathVariable Integer id) {
        shoppingCatService.updateOrderStatus(LoginUtil.getId(), id);
        return new Result(true, "修改成功");
    }

    @GetMapping("findcount")
    public Result findcount() {
        Integer count = shoppingCatService.countAllByStatusAndUid(LoginUtil.getId());
        return new Result(true, "查询成功", count);
    }
}
