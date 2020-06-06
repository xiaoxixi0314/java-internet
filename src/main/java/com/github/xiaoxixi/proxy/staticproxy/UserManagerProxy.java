package com.github.xiaoxixi.proxy.staticproxy;

public class UserManagerProxy implements UserManager {

    private UserManager userManager;

    public UserManagerProxy(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void addUser(User user) {
        System.out.println("proxy add user start");
        userManager.addUser(user);
        System.out.println("proxy add user finish");
    }

    @Override
    public void delUser(Long userId) {
        System.out.println("proxy del user start");
        userManager.delUser(userId);
        System.out.println("proxy del user finish");
    }
}
