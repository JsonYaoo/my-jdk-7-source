package com.jsonyao.cs.proxyPattern;

import java.lang.reflect.Method;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

// 20201113 CGLIB代理HelloCglib委托类生成的代理对象的反编译文件, 继承了委托类, 实现了Factory接口
public class HelloCglib$$EnhancerByCGLIB$$6bf7bfad extends HelloCglib implements Factory
{
  private boolean CGLIB$BOUND;
  private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
  private static final Callback[] CGLIB$STATIC_CALLBACKS;
  private MethodInterceptor CGLIB$CALLBACK_0;// 20201113 持有MethodInterceptor接口实现类实例
  private static final Method CGLIB$finalize$0$Method;// 20201113 原委托类finalize()
  private static final MethodProxy CGLIB$finalize$0$Proxy;// 20201113 代理类finalize()
  private static final Object[] CGLIB$emptyArgs;
  private static final Method CGLIB$equals$1$Method;// 20201113 原委托类equals()
  private static final MethodProxy CGLIB$equals$1$Proxy;// 20201113 代理类equals()
  private static final Method CGLIB$toString$2$Method;// 20201113 原委托类toString()
  private static final MethodProxy CGLIB$toString$2$Proxy;// 20201113 代理类toString()
  private static final Method CGLIB$hashCode$3$Method;// 20201113 原委托类hashCode()
  private static final MethodProxy CGLIB$hashCode$3$Proxy;// 20201113 代理类hashCode()
  private static final Method CGLIB$clone$4$Method;// 20201113 原委托类clone()
  private static final MethodProxy CGLIB$clone$4$Proxy;// 20201113 代理类clone()
  private static final Method CGLIB$helloAagin$5$Method;// 20201113 原委托类helloAagin()
  private static final MethodProxy CGLIB$helloAagin$5$Proxy;// 20201113 代理类helloAagin()

  static void CGLIB$STATICHOOK1()
  {
    CGLIB$THREAD_CALLBACKS = new ThreadLocal();
    CGLIB$emptyArgs = new Object[0];// 20201113 方法调用入参
    Class localClass1 = Class.forName("com.jsonyao.cs.proxyPattern.HelloCglib$$EnhancerByCGLIB$$6bf7bfad");// 20201113 代理对象
    Class localClass2;

    Method[] tmp107_104 = ReflectUtils.findMethods(new String[] { "finalize", "()V", "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;", "helloAagin", "()V" }, (localClass2 = Class.forName("com.jsonyao.cs.proxyPattern.HelloCglib")).getDeclaredMethods());
    CGLIB$finalize$0$Method = tmp107_104[0];
    CGLIB$finalize$0$Proxy = MethodProxy.create(localClass2, localClass1, "()V", "finalize", "CGLIB$finalize$0");

    // 20201113 MethodProxy根据loadClass2和loadClass1进行分析并生成FastClass, 然后再使用getIndex()来获取equals()方法和代理equals()方法的索引
    Method[] tmp127_107 = tmp107_104;
    CGLIB$equals$1$Method = tmp127_107[1];
    CGLIB$equals$1$Proxy = MethodProxy.create(localClass2, localClass1, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");

    Method[] tmp147_127 = tmp127_107;
    CGLIB$toString$2$Method = tmp147_127[2];
    CGLIB$toString$2$Proxy = MethodProxy.create(localClass2, localClass1, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");

    Method[] tmp167_147 = tmp147_127;
    CGLIB$hashCode$3$Method = tmp167_147[3];
    CGLIB$hashCode$3$Proxy = MethodProxy.create(localClass2, localClass1, "()I", "hashCode", "CGLIB$hashCode$3");

    Method[] tmp187_167 = tmp167_147;
    CGLIB$clone$4$Method = tmp187_167[4];
    CGLIB$clone$4$Proxy = MethodProxy.create(localClass2, localClass1, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");

    Method[] tmp207_187 = tmp187_167;
    CGLIB$helloAagin$5$Method = tmp207_187[5];
    CGLIB$helloAagin$5$Proxy = MethodProxy.create(localClass2, localClass1, "()V", "helloAagin", "CGLIB$helloAagin$5");

    tmp207_187;
    return;
  }

  final void CGLIB$finalize$0()
    throws Throwable
  {
    super.finalize();
  }

  protected final void finalize()
    throws Throwable
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    if (this.CGLIB$CALLBACK_0 != null)
      return;
    super.finalize();
  }

  final boolean CGLIB$equals$1(Object paramObject)
  {
    return super.equals(paramObject);
  }

  public final boolean equals(Object paramObject)
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
    {
      Object tmp41_36 = tmp17_14.intercept(this, CGLIB$equals$1$Method, new Object[] { paramObject }, CGLIB$equals$1$Proxy);
      tmp41_36;
      return tmp41_36 == null ? false : ((Boolean)tmp41_36).booleanValue();
    }
    return super.equals(paramObject);
  }

  final String CGLIB$toString$2()
  {
    return super.toString();
  }

  public final String toString()
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
      return (String)tmp17_14.intercept(this, CGLIB$toString$2$Method, CGLIB$emptyArgs, CGLIB$toString$2$Proxy);
    return super.toString();
  }

  final int CGLIB$hashCode$3()
  {
    return super.hashCode();
  }

  public final int hashCode()// 20201113 public在代理对象中有被代理实现到 -> intercept
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
    {
      Object tmp36_31 = tmp17_14.intercept(
              this, // 202011113 代理对象本身
              CGLIB$hashCode$3$Method,// 20201113 原委托类的hashCode()
              CGLIB$emptyArgs,// 20201113 方法调用入参
              CGLIB$hashCode$3$Proxy// 20201113 代理类的hashCode()
      );
      tmp36_31;
      return tmp36_31 == null ? 0 : ((Number)tmp36_31).intValue();
    }
    return super.hashCode();
  }

  final Object CGLIB$clone$4()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  protected final Object clone()
    throws CloneNotSupportedException
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
      return tmp17_14.intercept(this, CGLIB$clone$4$Method, CGLIB$emptyArgs, CGLIB$clone$4$Proxy);
    return super.clone();
  }

  final void CGLIB$helloAagin$5()
  {
    super.helloAagin();
  }

  protected final void helloAagin()// 20201113 protected在代理对象中没有被代理实现到
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;// 20201113 持有MethodInterceptor接口实现类实例
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    if (this.CGLIB$CALLBACK_0 != null)
      return;
    super.helloAagin();
  }

  public static MethodProxy CGLIB$findMethodProxy(Signature paramSignature)
  {
    String tmp4_1 = paramSignature.toString();
    switch (tmp4_1.hashCode())
    {
    case -1574182249:
      if (tmp4_1.equals("finalize()V"))
        return CGLIB$finalize$0$Proxy;
      break;
    case -508378822:
    case 949643067:
    case 1826985398:
    case 1913648695:
    case 1984935277:
    }
  }

  public HelloCglib$$EnhancerByCGLIB$$6bf7bfad()
  {
    CGLIB$BIND_CALLBACKS(this);
  }

  public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] paramArrayOfCallback)
  {
    CGLIB$THREAD_CALLBACKS.set(paramArrayOfCallback);
  }

  public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] paramArrayOfCallback)
  {
    CGLIB$STATIC_CALLBACKS = paramArrayOfCallback;
  }

  private static final void CGLIB$BIND_CALLBACKS(Object paramObject)
  {
      Target$$EnhancerByCGLIB$$788444a0 temp_1 = (Target$$EnhancerByCGLIB$$788444a0)o;
      Object temp_2;
      Callback[] temp_3
      if(temp_1.CGLIB$BOUND == true){
        return;
      }
      temp_1.CGLIB$BOUND = true;
      temp_2 = CGLIB$THREAD_CALLBACKS.get();// Enhancer: firstInstance() -> createUsingReflection -> setThreadCallbacks -> setCallbacksHelper(type, callbacks, SET_THREAD_CALLBACKS_NAME);
      if(temp_2!=null){
        temp_3 = (Callback[])temp_2;
      }
      else if(CGLIB$STATIC_CALLBACKS!=null){
        temp_3 = CGLIB$STATIC_CALLBACKS;
      }
      else{
        return;
      }
      temp_1.CGLIB$CALLBACK_0 = (MethodInterceptor)temp_3[0];// 20201113 持有MethodInterceptor接口实现类实例
      return;
  }

  public Object newInstance(Callback[] paramArrayOfCallback)
  {
    CGLIB$SET_THREAD_CALLBACKS(paramArrayOfCallback);
    CGLIB$SET_THREAD_CALLBACKS(null);
    return new 6bf7bfad();
  }

  public Object newInstance(Callback paramCallback)
  {
    CGLIB$SET_THREAD_CALLBACKS(new Callback[] { paramCallback });
    CGLIB$SET_THREAD_CALLBACKS(null);
    return new 6bf7bfad();
  }

  public Object newInstance(Class[] paramArrayOfClass, Object[] paramArrayOfObject, Callback[] paramArrayOfCallback)
  {
    CGLIB$SET_THREAD_CALLBACKS(paramArrayOfCallback);
    Class[] tmp9_8 = paramArrayOfClass;
    switch (tmp9_8.length)
    {
    case 0:
      tmp9_8;
      break;
    default:
      new 6bf7bfad();
      throw new IllegalArgumentException("Constructor not found");
    }
    CGLIB$SET_THREAD_CALLBACKS(null);
  }

  public Callback getCallback(int paramInt)
  {
    CGLIB$BIND_CALLBACKS(this);
    switch (paramInt)
    {
    case 0:
      break;
    }
    return null;
  }

  public void setCallback(int paramInt, Callback paramCallback)
  {
    switch (paramInt)
    {
    case 0:
      this.CGLIB$CALLBACK_0 = ((MethodInterceptor)paramCallback);
      break;
    }
  }

  public Callback[] getCallbacks()
  {
    CGLIB$BIND_CALLBACKS(this);
    return new Callback[] { this.CGLIB$CALLBACK_0 };
  }

  public void setCallbacks(Callback[] paramArrayOfCallback)
  {
    this.CGLIB$CALLBACK_0 = ((MethodInterceptor)paramArrayOfCallback[0]);
  }

  static
  {
    CGLIB$STATICHOOK1();
  }
}

/* Location:           C:\Users\14840\Desktop\
 * Qualified Name:     com.jsonyao.cs.proxyPattern.HelloCglib..EnhancerByCGLIB..6bf7bfad
 * JD-Core Version:    0.6.2
 */