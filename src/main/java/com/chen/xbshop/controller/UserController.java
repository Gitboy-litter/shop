package com.chen.xbshop.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.chen.xbshop.pojo.Result;
import com.chen.xbshop.pojo.User;
import com.chen.xbshop.service.UserService;
import com.chen.xbshop.utlis.Code;
import com.chen.xbshop.utlis.EmailUtil;
import com.chen.xbshop.utlis.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author czw
 * @Description user 控制器层
 * @date 2020-12-05 16:50:23
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Kaptcha kaptcha;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpServletResponse response;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, Object> map) {
        if (!kaptcha.validate((String) map.get("code"))) {
            return new Result(false, "验证码错误");
        }
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            return new Result(false, "用户名或者密码错误");
        }
        user.setLoginTime(new Date());
        userService.update(user);
        Boolean check = (Boolean) map.get("check");
        if (check == null) {
            check = false;
        }
        Cookie cookie = new Cookie("userId", user.getId() + "");
        cookie.setPath("/");
        if (check) {
            cookie.setMaxAge(7 * 24 * 60 * 60);
        }
        response.addCookie(cookie);
        redisTemplate.opsForValue().set("user:loginUser:" + user.getId(), user);
        return new Result(true, "登陆成功");
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
    public Result sendEmail(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new Result(false, "邮箱不存在");
        }
        String code = Code.code();
        EmailUtil.sendEmail(email, code);
        redisTemplate.opsForValue().set("user:login:emailcode" + email, code, 3, TimeUnit.MINUTES);
        return new Result(true, "发送成功");
    }

    @RequestMapping(value = "/forget", method = RequestMethod.PUT)
    public Result forget(@RequestBody Map<String, String> map) {
        String email = map.get("email");
        String password = map.get("password");
        String username = map.get("username");
        User user = userService.findByEmailAndUsername(email, username);
        if (user == null) {
            return new Result(false, "邮箱和用户不匹配");
        }
        String code = map.get("code");
        String emailCode = (String) redisTemplate.opsForValue().get("user:updatePasswordCode:" + email);
        if (emailCode != "" && emailCode != null) {
            if (!emailCode.equals(code)) {
                return new Result(false, "验证码不准确");
            }
        }
        userService.updateByEmail(email, password);
        return new Result(true, "修改成功");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        user.setRegisterTime(new Date());
        userService.add(user);
        return new Result(true, "添加成功");
    }

    @GetMapping("checkEmail/{email}")
    public Result checkEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return new Result(false, " 邮箱已经注册");
        }
        return new Result(true, "可以注册");
    }

    @GetMapping("checkUsername/{name}")
    public Result checkUsername(@PathVariable String name) {
        User user = userService.findByUsername(name);
        if (user != null) {
            return new Result(false, "用户已经注册");
        }
        return new Result(true, "可以注册");
    }

    @GetMapping("findUserAndAddresss")
    public Result findUserAndAddresss() {
        Map<String, Object> user = userService.findUserAndAddresss(LoginUtil.getId());
        Date registerTime = (Date) user.get("registerTime");
        Date loginTime = (Date) user.get("loginTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(registerTime);
        String loginT = sdf.format(loginTime);
        HashMap<String, Object> hashMap = new HashMap<>(user);
        hashMap.put("registerTime", format);
        hashMap.put("loginTime", loginT);
        return new Result(true, "可以注册", hashMap);
    }

    @PutMapping("save")
    public Result save(@RequestBody User user) {
        userService.update(user);
        return new Result(true, "修改成功");
    }

    @PutMapping("updatepassword/{password}")
    public Result updatepasswordupdatepassword(@PathVariable String password) {
        User loginUser = LoginUtil.getLoginUser();
        loginUser.setPassword(password);
        userService.update(loginUser);
        return new Result(true, "修改成功");
    }
}
