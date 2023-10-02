package com.example.userservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.user.User;
import com.example.userservice.service.UserService;
import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class UserController implements ApplicationContextAware {
    @Autowired
    @Qualifier("userServiceV3")
    private UserService userService;

    private ApplicationContext applicationContext;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("list")
    public Result list() {
        List<User> list = userService.list();
        log.info("list:{}", list);

        return Result.build(200, "操作成功", list);
    }

    @RequestMapping("add")
    public Result add(@RequestBody User user) {
        userService.save(user);
        System.out.println("id;" + user.getId());
        return Result.build(200);
    }

    @RequestMapping("del")
    public Result del(String id) {
        userService.removeById(id);
        return Result.build(200);
    }

    @RequestMapping("edit")
    public Result edit(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return Result.build(200);
    }

    @RequestMapping("page")
    public Result page() {
        Object mybatisPlusInterceptor = applicationContext.getBean("mybatisPlusInterceptor");
        if (mybatisPlusInterceptor == null) {
            System.out.println("1");
        } else {
            System.out.println("2");
        }
        Page<User> page = userService.page(new Page<>(1, 1));
        return Result.build(200, "操作成功", page);
    }

        @RequestMapping("placeAnOrder")
    public Result placeAnOrder() {
            try {
                userService.placeAnOrder();
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.build(500,"服务器错误");
            }
            return Result.build(200);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
