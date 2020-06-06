package com.github.xiaoxixi.proxy.staticproxy;

public class CNUserManagerImpl implements UserManager {

    @Override
    public void addUser(User user) {
        System.out.println("CN-UserManagerImpl.addUser" + user.toString());
    }

    @Override
    public void delUser(Long userId) {
        System.out.println("CN-UserManagerImpl.delUser" + userId);
    }
}
