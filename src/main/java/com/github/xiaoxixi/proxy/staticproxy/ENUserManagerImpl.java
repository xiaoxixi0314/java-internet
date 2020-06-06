package com.github.xiaoxixi.proxy.staticproxy;

public class ENUserManagerImpl implements UserManager {

    @Override
    public void addUser(User user) {
        System.out.println("EN-UserManagerImpl.addUser" + user.toString());
    }

    @Override
    public void delUser(Long userId) {
        System.out.println("EN-UserManagerImpl.delUser" + userId);
    }
}
