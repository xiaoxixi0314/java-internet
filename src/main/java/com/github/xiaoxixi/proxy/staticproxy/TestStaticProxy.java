package com.github.xiaoxixi.proxy.staticproxy;

public class TestStaticProxy {

    public static void main(String[] args) {

        User cnUSer = User.builder().userId(1L).userName("CN user").sex("male").build();
        UserManager cn = new CNUserManagerImpl();
        UserManagerProxy proxy = new UserManagerProxy(cn);
        proxy.addUser(cnUSer);
        proxy.delUser(cnUSer.getUserId());
        System.out.println("=======================================");

        User enUSer = User.builder().userId(1L).userName("EN user").sex("female").build();
        UserManager en = new ENUserManagerImpl();
        proxy = new UserManagerProxy(en);
        proxy.addUser(enUSer);
        proxy.delUser(enUSer.getUserId());
    }
}
