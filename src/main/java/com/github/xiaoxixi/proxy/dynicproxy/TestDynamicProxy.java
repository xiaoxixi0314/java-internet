package com.github.xiaoxixi.proxy.dynicproxy;

import com.github.xiaoxixi.proxy.staticproxy.CNUserManagerImpl;
import com.github.xiaoxixi.proxy.staticproxy.UserManager;

import java.lang.reflect.Proxy;

public class TestDynamicProxy {

    public static void main(String[] args) {

        UserManager userManager = (UserManager) Proxy.newProxyInstance(
                UserManager.class.getClassLoader(),
                new Class[]{UserManager.class},
                new DynamicProxy(new CNUserManagerImpl())
        );
        userManager.delUser(1L);

    }
}
