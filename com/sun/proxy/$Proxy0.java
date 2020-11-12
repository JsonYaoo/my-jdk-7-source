// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sun.proxy;

import com.jsonyao.cs.proxyPattern.UserService;
import java.lang.reflect.*;

// 20201112 动态代理生成类$Proxy0
public final class $Proxy0 extends Proxy implements UserService
{
    private static Method m3;// com.jsonyao.cs.proxyPattern.UserServic => save()
    private static Method m1;// java.lang.Object => equals()
    private static Method m0;// java.lang.Object => hashCode()
    private static Method m2;// java.lang.Object => toString()

    static
    {
        try
        {
            // 反射获取UserService接口的save()方法
            m3 = Class.forName("com.jsonyao.cs.proxyPattern.UserService").getMethod("save", new Class[0]);

            // 反射获取java.lang.Object的equals()方法
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] {
                    Class.forName("java.lang.Object")
            });

            // 反射获取java.lang.Object的hashCode()方法
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);

            // 反射获取java.lang.Object的toString()方法
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);

        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new NoSuchMethodError(nosuchmethodexception.getMessage());
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    // 20201112 构造方法
    public $Proxy0(InvocationHandler invocationhandler)
    {
        // 20201112 调用父类构造器, 赋值自定义InvocationHandler
        super(invocationhandler);
    }

    // 20201112 实现了UserService的save()方法
    public final void save()
    {
        try
        {
            // 20201112 调用父类注入的invocationHandler实例实现的invoke()方法
            super.h.invoke(this, m3, null);// 20201112 参数分别为Object proxy, Method method, Object[] args
            return;
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    // 20201112 重写java.lang.object的equals()方法
    public final boolean equals(Object obj)
    {
        try
        {
            // 20201112 调用父类注入的invocationHandler实例实现的invoke()方法
            return ((Boolean)super.h.invoke(this, m1, new Object[] {// 20201112 参数分别为Object proxy, Method method, Object[] args
                obj// 20201112 args为传入的obj参数
            })).booleanValue();
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    // 20201112  重写java.lang.object的hashCode()方法
    public final int hashCode()
    {
        try
        {
            // 20201112 调用父类注入的invocationHandler实例实现的invoke()方法
            return ((Integer)super.h.invoke(this, m0, null)).intValue();// 20201112 参数分别为Object proxy, Method method, Object[] args
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    // 20201112  重写java.lang.object的toString()方法
    public final String toString()
    {
        try
        {
            // 20201112 调用父类注入的invocationHandler实例实现的invoke()方法
            return (String)super.h.invoke(this, m2, null);// 20201112 参数分别为Object proxy, Method method, Object[] args
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
    }

}
