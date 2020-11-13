// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   <generated>

package com.jsonyao.cs.proxyPattern;

import java.lang.reflect.Method;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.*;

// Referenced classes of package com.jsonyao.cs.proxyPattern:
//            HelloCglib
// 20201113 CGLIB代理HelloCglib委托类生成的代理对象的反编译文件, 继承了委托类, 实现了Factory接口
public class HelloCglib$$EnhancerByCGLIB$$6bf7bfad extends HelloCglib implements Factory
{

    static void CGLIB$STATICHOOK1()
    {
        Method amethod[];// 20201113 Method数组 -> 含动态代理生成的方法 & 原委托类所有非Final的方法
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class class1 = Class.forName("com.jsonyao.cs.proxyPattern.HelloCglib$$EnhancerByCGLIB$$6bf7bfad");// 20201113 代理对象Class对象
        Class class2;// 20201113 委托类代理Class对象
        amethod = ReflectUtils.findMethods(new String[] {
            "finalize", "()V", "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;", 
            "helloAagin", "()V"
        }, (class2 = Class.forName("com.jsonyao.cs.proxyPattern.HelloCglib")).getDeclaredMethods());
        Method[] _tmp = amethod;
        CGLIB$finalize$0$Method = amethod[0];// 20201113 finalize()
        CGLIB$finalize$0$Proxy = MethodProxy.create(class2, class1, "()V", "finalize", "CGLIB$finalize$0");
        CGLIB$equals$1$Method = amethod[1];// 20201113 equals()
        CGLIB$equals$1$Proxy = MethodProxy.create(class2, class1, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
        CGLIB$toString$2$Method = amethod[2];// 20201113 toString()
        CGLIB$toString$2$Proxy = MethodProxy.create(class2, class1, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
        CGLIB$hashCode$3$Method = amethod[3];// 20201113 hashCode()
        CGLIB$hashCode$3$Proxy = MethodProxy.create(class2, class1, "()I", "hashCode", "CGLIB$hashCode$3");
        CGLIB$clone$4$Method = amethod[4];// 20201113 clone()
        CGLIB$clone$4$Proxy = MethodProxy.create(class2, class1, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
        CGLIB$helloAagin$5$Method = amethod[5];// 20201113 helloAagin()
        CGLIB$helloAagin$5$Proxy = MethodProxy.create(class2, class1, "()V", "helloAagin", "CGLIB$helloAagin$5");
    }

    // 20201113 委托类原finalize()
    final void CGLIB$finalize$0()
        throws Throwable
    {
        super.finalize();
    }

    // 20201113 代理finalize()
    protected final void finalize()
        throws Throwable
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 37;
           goto _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_21;
_L4:
        break MISSING_BLOCK_LABEL_37;
        this;
        CGLIB$finalize$0$Method;
        CGLIB$emptyArgs;
        CGLIB$finalize$0$Proxy;
        intercept();
        return;
        super.finalize();
        return;
    }

    // 20201113 委托类原equals()
    final boolean CGLIB$equals$1(Object obj)
    {
        return super.equals(obj);
    }

    // 20201113 代理equals()
    public final boolean equals(Object obj)
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 57;
           goto _L3 _L4
_L3:
        this;
        CGLIB$equals$1$Method;
        new Object[] {
            obj
        };
        CGLIB$equals$1$Proxy;
        intercept();
        JVM INSTR dup ;
        JVM INSTR ifnonnull 50;
           goto _L5 _L6
_L5:
        JVM INSTR pop ;
        false;
          goto _L7
_L6:
        (Boolean);
        booleanValue();
_L7:
        return;
_L4:
        return super.equals(obj);
    }

    // 20201113 委托类原toString()
    final String CGLIB$toString$2()
    {
        return super.toString();
    }

    // 20201113 代理toString()
    public final String toString()
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 40;
           goto _L3 _L4
_L3:
        this;
        CGLIB$toString$2$Method;
        CGLIB$emptyArgs;
        CGLIB$toString$2$Proxy;
        intercept();
        (String);
        return;
_L4:
        return super.toString();
    }

    // 20201113 委托类原hashCode()
    final int CGLIB$hashCode$3()
    {
        return super.hashCode();
    }

    // 20201113 代理hashCode()
    public final int hashCode()
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 52;
           goto _L3 _L4
_L3:
        this;
        CGLIB$hashCode$3$Method;
        CGLIB$emptyArgs;
        CGLIB$hashCode$3$Proxy;
        intercept();
        JVM INSTR dup ;
        JVM INSTR ifnonnull 45;
           goto _L5 _L6
_L5:
        JVM INSTR pop ;
        0;
          goto _L7
_L6:
        (Number);
        intValue();
_L7:
        return;
_L4:
        return super.hashCode();
    }

    // 20201113 委托类原clone()
    final Object CGLIB$clone$4()
        throws CloneNotSupportedException
    {
        return super.clone();
    }

    // 20201113 代理clone()
    protected final Object clone()
        throws CloneNotSupportedException
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 37;
           goto _L3 _L4
_L3:
        this;
        CGLIB$clone$4$Method;
        CGLIB$emptyArgs;
        CGLIB$clone$4$Proxy;
        intercept();
        return;
_L4:
        return super.clone();
    }

    // 20201113 委托类原helloAagin()
    final void CGLIB$helloAagin$5()
    {
        super.helloAagin();
    }

    // 20201113 代理helloAagin()
    protected final void helloAagin()
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 37;
           goto _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_21;
_L4:
        break MISSING_BLOCK_LABEL_37;
        this;
        CGLIB$helloAagin$5$Method;
        CGLIB$emptyArgs;
        CGLIB$helloAagin$5$Proxy;
        intercept();
        return;
        super.helloAagin();
        return;
    }

    public static MethodProxy CGLIB$findMethodProxy(Signature signature)
    {
        String s = signature.toString();
        s;
        s.hashCode();
        JVM INSTR lookupswitch 6: default 140
    //                   -1574182249: 68
    //                   -508378822: 80
    //                   949643067: 92
    //                   1826985398: 104
    //                   1913648695: 116
    //                   1984935277: 128;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L2:
        "finalize()V";
        equals();
        JVM INSTR ifeq 141;
           goto _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_141;
_L8:
        return CGLIB$finalize$0$Proxy;
_L3:
        "clone()Ljava/lang/Object;";
        equals();
        JVM INSTR ifeq 141;
           goto _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_141;
_L10:
        return CGLIB$clone$4$Proxy;
_L4:
        "helloAagin()V";
        equals();
        JVM INSTR ifeq 141;
           goto _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_141;
_L12:
        return CGLIB$helloAagin$5$Proxy;
_L5:
        "equals(Ljava/lang/Object;)Z";
        equals();
        JVM INSTR ifeq 141;
           goto _L14 _L15
_L15:
        break MISSING_BLOCK_LABEL_141;
_L14:
        return CGLIB$equals$1$Proxy;
_L6:
        "toString()Ljava/lang/String;";
        equals();
        JVM INSTR ifeq 141;
           goto _L16 _L17
_L17:
        break MISSING_BLOCK_LABEL_141;
_L16:
        return CGLIB$toString$2$Proxy;
_L7:
        "hashCode()I";
        equals();
        JVM INSTR ifeq 141;
           goto _L18 _L19
_L19:
        break MISSING_BLOCK_LABEL_141;
_L18:
        return CGLIB$hashCode$3$Proxy;
_L1:
        JVM INSTR pop ;
        return null;
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback acallback[])
    {
        CGLIB$THREAD_CALLBACKS.set(acallback);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback acallback[])
    {
        CGLIB$STATIC_CALLBACKS = acallback;
    }

    // 20201113 获取实现的拦截器实例
    private static final void CGLIB$BIND_CALLBACKS(Object obj)
    {
        HelloCglib$$EnhancerByCGLIB$$6bf7bfad hellocglib$$enhancerbycglib$$6bf7bfad = (HelloCglib$$EnhancerByCGLIB$$6bf7bfad)obj;
        if(hellocglib$$enhancerbycglib$$6bf7bfad.CGLIB$BOUND) goto _L2; else goto _L1
_L1:
        Object obj1;
        hellocglib$$enhancerbycglib$$6bf7bfad.CGLIB$BOUND = true;
        obj1 = CGLIB$THREAD_CALLBACKS.get();
        obj1;
        if(obj1 != null) goto _L4; else goto _L3
_L3:
        JVM INSTR pop ;
        CGLIB$STATIC_CALLBACKS;
        if(CGLIB$STATIC_CALLBACKS != null) goto _L4; else goto _L5
_L5:
        JVM INSTR pop ;
          goto _L2
_L4:
        (Callback[]);
        hellocglib$$enhancerbycglib$$6bf7bfad;
        JVM INSTR swap ;
        0;
        JVM INSTR aaload ;
        (MethodInterceptor);
        CGLIB$CALLBACK_0;
_L2:
    }

    public Object newInstance(Callback acallback[])
    {
        CGLIB$SET_THREAD_CALLBACKS(acallback);
        CGLIB$SET_THREAD_CALLBACKS(null);
        return new HelloCglib$$EnhancerByCGLIB$$6bf7bfad();
    }

    public Object newInstance(Callback callback)
    {
        CGLIB$SET_THREAD_CALLBACKS(new Callback[] {
            callback
        });
        CGLIB$SET_THREAD_CALLBACKS(null);
        return new HelloCglib$$EnhancerByCGLIB$$6bf7bfad();
    }

    public Object newInstance(Class aclass[], Object aobj[], Callback acallback[])
    {
        CGLIB$SET_THREAD_CALLBACKS(acallback);
        JVM INSTR new #2   <Class HelloCglib$$EnhancerByCGLIB$$6bf7bfad>;
        JVM INSTR dup ;
        aclass;
        aclass.length;
        JVM INSTR tableswitch 0 0: default 35
    //                   0 28;
           goto _L1 _L2
_L2:
        JVM INSTR pop ;
        HelloCglib$$EnhancerByCGLIB$$6bf7bfad();
          goto _L3
_L1:
        JVM INSTR pop ;
        throw new IllegalArgumentException("Constructor not found");
_L3:
        CGLIB$SET_THREAD_CALLBACKS(null);
        return;
    }

    public Callback getCallback(int i)
    {
        CGLIB$BIND_CALLBACKS(this);
        this;
        i;
        JVM INSTR tableswitch 0 0: default 30
    //                   0 24;
           goto _L1 _L2
_L2:
        CGLIB$CALLBACK_0;
          goto _L3
_L1:
        JVM INSTR pop ;
        null;
_L3:
        return;
    }

    public void setCallback(int i, Callback callback)
    {
        switch(i)
        {
        case 0: // '\0'
            CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
            break;
        }
    }

    public Callback[] getCallbacks()
    {
        CGLIB$BIND_CALLBACKS(this);
        this;
        return (new Callback[] {
            CGLIB$CALLBACK_0
        });
    }

    public void setCallbacks(Callback acallback[])
    {
        this;
        acallback;
        JVM INSTR dup2 ;
        0;
        JVM INSTR aaload ;
        (MethodInterceptor);
        CGLIB$CALLBACK_0;
    }

    private boolean CGLIB$BOUND;
    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static final Callback CGLIB$STATIC_CALLBACKS[];
    private MethodInterceptor CGLIB$CALLBACK_0;// 20201113 持有实现的MethodInterceptor的引用
    private static final Method CGLIB$finalize$0$Method;
    private static final MethodProxy CGLIB$finalize$0$Proxy;
    private static final Object CGLIB$emptyArgs[];
    private static final Method CGLIB$equals$1$Method;
    private static final MethodProxy CGLIB$equals$1$Proxy;
    private static final Method CGLIB$toString$2$Method;
    private static final MethodProxy CGLIB$toString$2$Proxy;
    private static final Method CGLIB$hashCode$3$Method;
    private static final MethodProxy CGLIB$hashCode$3$Proxy;
    private static final Method CGLIB$clone$4$Method;
    private static final MethodProxy CGLIB$clone$4$Proxy;
    private static final Method CGLIB$helloAagin$5$Method;
    private static final MethodProxy CGLIB$helloAagin$5$Proxy;

    static 
    {
        CGLIB$STATICHOOK1();
    }

    public HelloCglib$$EnhancerByCGLIB$$6bf7bfad()
    {
        CGLIB$BIND_CALLBACKS(this);
    }
}
