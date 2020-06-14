package com.github.xiaoxixi.rpc.service;

import com.github.xiaoxixi.rpc.service.pojo.User;
import com.github.xiaoxixi.rpc.service.pojo.UserQuery;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> queryUser(UserQuery query) {
        User user = new User();
        user.setUserId(query.getUserId());
        user.setUserName(query.getUserName());
        user.setAge(18);
        user.setPwd("a1234566");
        return Arrays.asList(user);
    }

    @Override
    public boolean createUser(User user) {
        System.out.println("收到的创建用户的参数:");
        System.out.println(user.toString());
        return true;
    }
}
