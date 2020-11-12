package com.jsonyao.cs.proxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 用户服务实现JDK动态代理类
 */
public class UserServiceImplJdkProxy implements InvocationHandler {

    private UserService userService;

    public UserServiceImplJdkProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理前...");

        Object result = method.invoke(userService, args);

        System.out.println("JDK动态代理后...");
        return result;
    }
}
