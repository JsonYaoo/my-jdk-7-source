package com.jsonyao.cs.proxyPattern;

/**
 * 用户服务实现静态代理类
 */
public class UserServiceImplStaticProxy implements UserService{

    private UserService userService;

    public UserServiceImplStaticProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void save() {
        System.out.println("静态代理前...");
        userService.save();
        System.out.println("静态代理后...");
    }

}
