package com.github.xiaoxixi.rpc.service;

import com.github.xiaoxixi.rpc.service.pojo.User;
import com.github.xiaoxixi.rpc.service.pojo.UserQuery;
import java.util.List;

public interface UserService {

    List<User> queryUser(UserQuery query);

    boolean createUser(User user);

}
