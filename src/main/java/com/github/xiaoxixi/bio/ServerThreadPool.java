package com.github.xiaoxixi.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThreadPool {

    private static ExecutorService service =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(12345));
        while (true) {
            service.execute(new ServerTask(serverSocket.accept()));
        }
    }

    private static class ServerTask implements Runnable {

        private Socket socket = null;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                // 服务器的输入
                input = new ObjectInputStream(socket.getInputStream());
                String clientMsg = input.readUTF();
                System.out.println("accept client message:" + clientMsg);

                // 发送给客户端
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeUTF("hello," + clientMsg);
                // 强制从缓存区通过网络发送出去
                output.flush();

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
