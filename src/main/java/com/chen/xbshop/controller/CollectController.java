package com.chen.xbshop.controller;

import com.chen.xbshop.pojo.Collect;
import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.service.CollectService;
import com.chen.xbshop.utlis.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author czw
 * @Description appraise 控制器层
 * @date 2020-12-05 16:50:23
 */
@RestController
@CrossOrigin
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping(value = "/changecollect/{pid}", method = RequestMethod.POST)
    public Result changecollect(@PathVariable Integer pid) {
        Collect collect = collectService.findByUidAndPid(LoginUtil.getId(), pid);
        if (collect == null) {
            //加收藏
            collect = new Collect();
            collect.setPid(pid);
            collect.setUid(LoginUtil.getId());
            collectService.add(collect);
            return new Result(true, "收藏成功", true);
        }
        collectService.remove(collect);
        return new Result(true, "取消收藏成功", false);
    }

    @RequestMapping(value = "/iscollect/{pid}", method = RequestMethod.GET)
    public Result iscollect(@PathVariable Integer pid) {
        Collect collect = collectService.findByUidAndPid(LoginUtil.getId(), pid);
        if (collect == null) {
            return new Result(true, "没有收藏", false);
        }
        return new Result(true, "已经收藏", true);
    }
}
