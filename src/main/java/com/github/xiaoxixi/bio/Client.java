package com.github.xiaoxixi.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        InetSocketAddress address =
                new InetSocketAddress("127.0.0.1", 12345);
        try {
            socket = new Socket();
            socket.connect(address);
            
            // 注意获取流的顺序要和服务端相反
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF("xiaoxixi");
            output.flush();

            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("accept message from server:" + input.readUTF());
        }  finally {
            if(socket != null)socket.close();
            if(output != null) output.close();
            if(input != null) input.close();

        }
    }
}
