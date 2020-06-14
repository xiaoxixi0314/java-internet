package com.github.xiaoxixi.rpc;

import com.alibaba.fastjson.JSON;
import com.github.xiaoxixi.rpc.client.RpcClientFrame;
import com.github.xiaoxixi.rpc.service.UserService;
import com.github.xiaoxixi.rpc.service.pojo.User;
import com.github.xiaoxixi.rpc.service.pojo.UserQuery;

import java.util.List;


public class Test {


    public static void main(String[] args) {

        UserService remote = RpcClientFrame.getRemoteService(UserService.class);
        UserQuery query = new UserQuery();
        query.setUserId(2L);
        query.setUserName("hello");
        List<User> users = remote.queryUser(query);
        System.out.println(JSON.toJSONString(users));

    }
}
