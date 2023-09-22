package com.example.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.user.User;

public interface UserService extends IService<User> {

    Result placeAnOrder() throws Exception;

    boolean checkUser(String userName, String password);

    User validate(String userName, String password);
}
