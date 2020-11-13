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
        Object object = methodProxy.invokeSuper(o, objects);// 20201113 实际是调用了FastClass的invoke方法, 去调用父类的原方法, 对比JDK动态代理提高了性能
        System.out.println("CGLIB动态代理后...");
        return object;
    }
}
