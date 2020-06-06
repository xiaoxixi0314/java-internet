package com.github.xiaoxixi.proxy.dynicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object realInstance;

    public DynamicProxy(Object realInstance) {
        this.realInstance = realInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>>dynamic proxy start");
        Object result = method.invoke(realInstance, args);
        System.out.println("<<<<dynamic proxy finish");
        return result;
    }
}
