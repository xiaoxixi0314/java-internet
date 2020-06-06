package com.github.xiaoxixi.serializable;

import java.io.*;

public class SerializableTest {

    private static final String SERIALIZABLE_FILE_PATH = "/user.txt";

    public static void main(String[] args) {
        SerializableSimpleVO serializableSimpleVO = new SerializableSimpleVO();
        serializableSimpleVO.setUserId(20L);
        serializableSimpleVO.setUserName("hello, world");
        serializableSimpleVO.setPwd("123456789");
        //serializable(user);
        deSerializable();
    }

    public static void serializable(SerializableSimpleVO serializableSimpleVO) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(SERIALIZABLE_FILE_PATH));
            output.writeObject(serializableSimpleVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("序列化对象成功");
    }

    public static void deSerializable() {
        ObjectInputStream input = null;
        try{
            File file = new File(SERIALIZABLE_FILE_PATH);
            input = new ObjectInputStream(new FileInputStream(file));
            SerializableSimpleVO serializableSimpleVO = (SerializableSimpleVO)input.readObject();
            System.out.println(serializableSimpleVO.toString());
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
