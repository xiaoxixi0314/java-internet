package com.github.xiaoxixi.rpc.server;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 服务端任务
 */
public class ServerWorkTask implements Runnable {

    private Socket socket;

    public ServerWorkTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            // 读取数据
            String serviceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();
            Class<?>[] paramTypes = (Class<?>[])inputStream.readObject();
            Object[] args = (Object[])inputStream.readObject();

            Class service = RpcServerFrame.REGISTER_CONTAINER.get(serviceName);
            Object result = null;
            if (service == null) {
                throw new ClassNotFoundException(serviceName + "not found");
            }

            // 执行本地方法
            result = invoke(service, methodName, paramTypes, args);
            System.out.println(">> [server] invoke the method:[" + service +"."+ methodName + "], result:" + JSON.toJSONString(result));

            // 返回给客户端执行结果
            outputStream.writeObject(result);
            outputStream.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null && socket.isConnected()) {
                    socket.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private Object invoke(Class service, String methodName, Class<?>[] paramTypes, Object[] args){
        try {
            Object instance = service.newInstance();
            Method method = service.getMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
