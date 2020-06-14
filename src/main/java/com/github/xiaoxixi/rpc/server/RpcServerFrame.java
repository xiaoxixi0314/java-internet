package com.github.xiaoxixi.rpc.server;

import com.github.xiaoxixi.rpc.CommonConstants;
import com.github.xiaoxixi.rpc.service.UserService;
import com.github.xiaoxixi.rpc.service.UserServiceImpl;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServerFrame {

    /**
     * 服务注册容器
     */
    public static Map<String, Class> REGISTER_CONTAINER = new HashMap<>();

    /**
     * 工作线程池
     */
    private static final ExecutorService SERVER_WORKERS =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args){
        registerServices(UserService.class.getName(), UserServiceImpl.class);
        serverStart();
    }

    /**
     * 服务注册
     * @param serviceName
     * @param clazz
     */
    private static void registerServices(String serviceName, Class clazz){
        System.out.println("start register service..." + serviceName);
        REGISTER_CONTAINER.put(serviceName, clazz);
    }

    /**
     * 服务端启动
     */
    private static void serverStart(){
        System.out.println("start to start server...");
        try {
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress(CommonConstants.PORT));
            System.out.println(">>>[server] server started...");
            while (true) {
                SERVER_WORKERS.execute(new ServerWorkTask(ss.accept()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
