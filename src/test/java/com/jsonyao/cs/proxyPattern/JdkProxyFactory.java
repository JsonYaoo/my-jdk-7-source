package com.jsonyao.cs.proxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理类工厂
 */
public class JdkProxyFactory {

    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    public Object getInstance(){
        Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK动态代理前...");

                        Object result = method.invoke(target, args);

                        System.out.println("JDK动态代理后...");
                        return result;
                    }
                });
        return object;
    }
}
