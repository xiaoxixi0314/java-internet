package com.github.xiaoxixi.rpc.client;

import com.github.xiaoxixi.rpc.CommonConstants;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClientFrame {

    private static Socket socket = null;
    /**
     * connect to server
     */
    static {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(CommonConstants.SERVER, CommonConstants.PORT);
            socket = new Socket();
            System.out.println("start to connect to server...");
            socket.connect(socketAddress);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static <T> T getRemoteService(Class interfaceClazz) {
        return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(),
                new Class<?>[]{interfaceClazz}, new DynProxy(interfaceClazz, socket));
    }

    private static class DynProxy implements InvocationHandler {


        private Socket innerSocket;

        private Class interfaceClazz;

        public DynProxy(Class interfaceClazz, Socket innerSocket) {
            this.innerSocket = innerSocket;
            this.interfaceClazz = interfaceClazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
               outputStream = new ObjectOutputStream(innerSocket.getOutputStream());
               outputStream.writeUTF(interfaceClazz.getName());
               outputStream.writeUTF(method.getName());
               outputStream.writeObject(method.getParameterTypes());
               outputStream.writeObject(args);
               outputStream.flush();

               inputStream = new ObjectInputStream(innerSocket.getInputStream());
               // 返回结果
               Object result = inputStream.readObject();
               return result;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (innerSocket != null) {
                    innerSocket.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            return null;
        }
    }
}
