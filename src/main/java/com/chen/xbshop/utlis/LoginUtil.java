package com.chen.xbshop.utlis;

import com.chen.xbshop.pojo.User;
import com.chen.xbshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Component
public class LoginUtil {
    private static RedisTemplate redisTemplate;
    private static HttpSession session;
    private static HttpServletRequest request;
    private static UserService userService;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        LoginUtil.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setSession(HttpSession session) {
        LoginUtil.session = session;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        LoginUtil.request = request;
    }

    @Autowired
    public void setUserService(UserService userService) {
        LoginUtil.userService = userService;
    }

    /*** 获取当前登录的用户 ** @return */
    public static User getLoginUser() {
        Integer userId = null; /*** 从Cookie里面获取 */
        Cookie cookie = getCookie("userId");
        if (cookie != null && cookie.getValue() != "") {
            userId = Integer.parseInt(cookie.getValue());
            User user = (User) redisTemplate.opsForValue().get("user:loginUser:" + userId);
            if (user == null) {
                user = userService.findById(userId);
                redisTemplate.opsForValue().set("user:loginUser:" + user.getId(), user, 7, TimeUnit.DAYS);
            }
            return user;
        }
        return null;
    }

    /*** 根据cookieName获取Cookie
     六、用户模块
     6.1 用户列表分页
     一个完整的用户列表页面包含有如下数据：
     1）总页数（Integer)
     2）当前页数据（ List<User> ） 3）我（当前登录的用户）关注过哪些人（ List<id> ） ** @param cookieName * @return */
    public static Cookie getCookie(String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /*** 获取当前登录用户的id ** @return */
    public static Integer getId() {
        if (getLoginUser() != null) {
            return getLoginUser().getId();
        }
        return null;
    }
}