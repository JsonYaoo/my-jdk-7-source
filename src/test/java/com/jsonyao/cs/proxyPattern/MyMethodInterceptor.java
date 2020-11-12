package com.jsonyao.cs.proxyPattern;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理方法拦截类
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB动态代理前...");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("CGLIB动态代理后...");
        return object;
    }
}
