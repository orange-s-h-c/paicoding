package com.github.paicoding.forum.test.user;

import com.github.paicoding.forum.api.model.vo.user.UserInfoSaveReq;
import com.github.paicoding.forum.api.model.vo.user.UserSaveReq;
import com.github.paicoding.forum.test.BasicTest;
import com.github.paicoding.forum.service.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author YiHui
 * @date 2022/8/6
 */
public class UserServiceTest extends BasicTest {

    @Autowired
    private UserService userService;

    /**
     * 注册一个用户
     */
    @Test
    public void testRegister() {
        UserSaveReq req = new UserSaveReq();
        req.setThirdAccountId(UUID.randomUUID().toString());
        req.setLoginType(0);
        userService.registerOrGetUserInfo(req);
        long userId = req.getUserId();

        UserInfoSaveReq save = new UserInfoSaveReq();
        save.setUserId(userId);
        save.setUserName("一灰灰");
        save.setPhoto("https://spring.hhui.top/spring-blog/css/images/avatar.jpg");
        save.setCompany("xm");
        save.setPosition("java");
        save.setProfile("码农");
        userService.saveUserInfo(save);
    }

}
