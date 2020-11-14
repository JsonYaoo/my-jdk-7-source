package com.jsonyao.cs.proxyPattern;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理方法拦截类
 */
public class MyMethodInterceptor implements MethodInterceptor {

    public static final HelloCglib helloCglib = new HelloCglib();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB动态代理前...");

        // 20201114 之所以调用invoke方法失败, 是因为实际调度的是原委托类的方法, 需要传入原委托类对象, 但这里的Object对象指的是原委托类对象, 因此调用异常
//        Object object = methodProxy.invoke(helloCglib, objects);// 20201114 手动调用原委托类的FastClass方法, 可能会带来访问限定符号不为public和非final的异常

        // 20201113 实际是调用了FastClass的invoke方法, 去调用父类的原方法, 对比JDK动态代理提高了性能
        Object object = methodProxy.invokeSuper(o, objects);// 20201114 调用代理类的FastClass方法则避免了限定符异常的问题

        System.out.println("CGLIB动态代理后...");

        return object;
    }
}
