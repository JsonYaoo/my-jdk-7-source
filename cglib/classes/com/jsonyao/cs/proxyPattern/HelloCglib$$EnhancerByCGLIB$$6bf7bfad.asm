// class version 46.0 (46)
// access flags 1
public class com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad extends com/jsonyao/cs/proxyPattern/HelloCglib  implements net/sf/cglib/proxy/Factory  {

  // compiled from: <generated>

  // access flags 2
  private Z CGLIB$BOUND

  // access flags 26
  private final static Ljava/lang/ThreadLocal; CGLIB$THREAD_CALLBACKS

  // access flags 26
  private final static [Lnet/sf/cglib/proxy/Callback; CGLIB$STATIC_CALLBACKS

  // access flags 2
  private Lnet/sf/cglib/proxy/MethodInterceptor; CGLIB$CALLBACK_0

  // access flags 26
  private final static Ljava/lang/reflect/Method; CGLIB$finalize$0$Method

  // access flags 26
  private final static Lnet/sf/cglib/proxy/MethodProxy; CGLIB$finalize$0$Proxy

  // access flags 26
  private final static [Ljava/lang/Object; CGLIB$emptyArgs

  // access flags 26
  private final static Ljava/lang/reflect/Method; CGLIB$equals$1$Method

  // access flags 26
  private final static Lnet/sf/cglib/proxy/MethodProxy; CGLIB$equals$1$Proxy

  // access flags 26
  private final static Ljava/lang/reflect/Method; CGLIB$toString$2$Method

  // access flags 26
  private final static Lnet/sf/cglib/proxy/MethodProxy; CGLIB$toString$2$Proxy

  // access flags 26
  private final static Ljava/lang/reflect/Method; CGLIB$hashCode$3$Method

  // access flags 26
  private final static Lnet/sf/cglib/proxy/MethodProxy; CGLIB$hashCode$3$Proxy

  // access flags 26
  private final static Ljava/lang/reflect/Method; CGLIB$clone$4$Method

  // access flags 26
  private final static Lnet/sf/cglib/proxy/MethodProxy; CGLIB$clone$4$Proxy

  // access flags 26
  private final static Ljava/lang/reflect/Method; CGLIB$helloAagin$5$Method

  // access flags 26
  private final static Lnet/sf/cglib/proxy/MethodProxy; CGLIB$helloAagin$5$Proxy

  // access flags 8
  static CGLIB$STATICHOOK1()V
    NEW java/lang/ThreadLocal
    DUP
    INVOKESPECIAL java/lang/ThreadLocal.<init> ()V
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$THREAD_CALLBACKS : Ljava/lang/ThreadLocal;
    ICONST_0
    ANEWARRAY java/lang/Object
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$emptyArgs : [Ljava/lang/Object;
    LDC "com.jsonyao.cs.proxyPattern.HelloCglib$$EnhancerByCGLIB$$6bf7bfad"
    INVOKESTATIC java/lang/Class.forName (Ljava/lang/String;)Ljava/lang/Class;
    ASTORE 0
    BIPUSH 12
    ANEWARRAY java/lang/String
    DUP
    ICONST_0
    LDC "finalize"
    AASTORE
    DUP
    ICONST_1
    LDC "()V"
    AASTORE
    DUP
    ICONST_2
    LDC "equals"
    AASTORE
    DUP
    ICONST_3
    LDC "(Ljava/lang/Object;)Z"
    AASTORE
    DUP
    ICONST_4
    LDC "toString"
    AASTORE
    DUP
    ICONST_5
    LDC "()Ljava/lang/String;"
    AASTORE
    DUP
    BIPUSH 6
    LDC "hashCode"
    AASTORE
    DUP
    BIPUSH 7
    LDC "()I"
    AASTORE
    DUP
    BIPUSH 8
    LDC "clone"
    AASTORE
    DUP
    BIPUSH 9
    LDC "()Ljava/lang/Object;"
    AASTORE
    DUP
    BIPUSH 10
    LDC "helloAagin"
    AASTORE
    DUP
    BIPUSH 11
    LDC "()V"
    AASTORE
    LDC "com.jsonyao.cs.proxyPattern.HelloCglib"
    INVOKESTATIC java/lang/Class.forName (Ljava/lang/String;)Ljava/lang/Class;
    DUP
    ASTORE 1
    INVOKEVIRTUAL java/lang/Class.getDeclaredMethods ()[Ljava/lang/reflect/Method;
    INVOKESTATIC net/sf/cglib/core/ReflectUtils.findMethods ([Ljava/lang/String;[Ljava/lang/reflect/Method;)[Ljava/lang/reflect/Method;
    DUP
    ICONST_0
    AALOAD
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$finalize$0$Method : Ljava/lang/reflect/Method;
    ALOAD 1
    ALOAD 0
    LDC "()V"
    LDC "finalize"
    LDC "CGLIB$finalize$0"
    INVOKESTATIC net/sf/cglib/proxy/MethodProxy.create (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/sf/cglib/proxy/MethodProxy;
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$finalize$0$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    DUP
    ICONST_1
    AALOAD
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$equals$1$Method : Ljava/lang/reflect/Method;
    ALOAD 1
    ALOAD 0
    LDC "(Ljava/lang/Object;)Z"
    LDC "equals"
    LDC "CGLIB$equals$1"
    INVOKESTATIC net/sf/cglib/proxy/MethodProxy.create (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/sf/cglib/proxy/MethodProxy;
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$equals$1$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    DUP
    ICONST_2
    AALOAD
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$toString$2$Method : Ljava/lang/reflect/Method;
    ALOAD 1
    ALOAD 0
    LDC "()Ljava/lang/String;"
    LDC "toString"
    LDC "CGLIB$toString$2"
    INVOKESTATIC net/sf/cglib/proxy/MethodProxy.create (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/sf/cglib/proxy/MethodProxy;
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$toString$2$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    DUP
    ICONST_3
    AALOAD
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$hashCode$3$Method : Ljava/lang/reflect/Method;
    ALOAD 1
    ALOAD 0
    LDC "()I"
    LDC "hashCode"
    LDC "CGLIB$hashCode$3"
    INVOKESTATIC net/sf/cglib/proxy/MethodProxy.create (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/sf/cglib/proxy/MethodProxy;
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$hashCode$3$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    DUP
    ICONST_4
    AALOAD
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$clone$4$Method : Ljava/lang/reflect/Method;
    ALOAD 1
    ALOAD 0
    LDC "()Ljava/lang/Object;"
    LDC "clone"
    LDC "CGLIB$clone$4"
    INVOKESTATIC net/sf/cglib/proxy/MethodProxy.create (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/sf/cglib/proxy/MethodProxy;
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$clone$4$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    DUP
    ICONST_5
    AALOAD
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$helloAagin$5$Method : Ljava/lang/reflect/Method;
    ALOAD 1
    ALOAD 0
    LDC "()V"
    LDC "helloAagin"
    LDC "CGLIB$helloAagin$5"
    INVOKESTATIC net/sf/cglib/proxy/MethodProxy.create (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/sf/cglib/proxy/MethodProxy;
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$helloAagin$5$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    POP
    RETURN
    RETURN
    MAXSTACK = 6
    MAXLOCALS = 2

  // access flags 16
  final CGLIB$finalize$0()V throws java/lang/Throwable 
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.finalize ()V
    RETURN
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 20
  protected final finalize()V throws java/lang/Throwable 
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    DUP
    IFNONNULL L0
    POP
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    DUP
    IFNULL L1
    ALOAD 0
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$finalize$0$Method : Ljava/lang/reflect/Method;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$emptyArgs : [Ljava/lang/Object;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$finalize$0$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    INVOKEINTERFACE net/sf/cglib/proxy/MethodInterceptor.intercept (Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lnet/sf/cglib/proxy/MethodProxy;)Ljava/lang/Object;
    RETURN
   L1
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.finalize ()V
    RETURN
    MAXSTACK = 5
    MAXLOCALS = 1

  // access flags 16
  final CGLIB$equals$1(Ljava/lang/Object;)Z
    ALOAD 0
    ALOAD 1
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.equals (Ljava/lang/Object;)Z
    IRETURN
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 17
  public final equals(Ljava/lang/Object;)Z
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    DUP
    IFNONNULL L0
    POP
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    DUP
    IFNULL L1
    ALOAD 0
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$equals$1$Method : Ljava/lang/reflect/Method;
    ICONST_1
    ANEWARRAY java/lang/Object
    DUP
    ICONST_0
    ALOAD 1
    AASTORE
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$equals$1$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    INVOKEINTERFACE net/sf/cglib/proxy/MethodInterceptor.intercept (Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lnet/sf/cglib/proxy/MethodProxy;)Ljava/lang/Object;
    DUP
    IFNONNULL L2
    POP
    ICONST_0
    GOTO L3
   L2
    CHECKCAST java/lang/Boolean
    INVOKEVIRTUAL java/lang/Boolean.booleanValue ()Z
   L3
    IRETURN
   L1
    ALOAD 0
    ALOAD 1
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.equals (Ljava/lang/Object;)Z
    IRETURN
    MAXSTACK = 7
    MAXLOCALS = 2

  // access flags 16
  final CGLIB$toString$2()Ljava/lang/String;
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.toString ()Ljava/lang/String;
    ARETURN
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 17
  public final toString()Ljava/lang/String;
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    DUP
    IFNONNULL L0
    POP
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    DUP
    IFNULL L1
    ALOAD 0
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$toString$2$Method : Ljava/lang/reflect/Method;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$emptyArgs : [Ljava/lang/Object;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$toString$2$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    INVOKEINTERFACE net/sf/cglib/proxy/MethodInterceptor.intercept (Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lnet/sf/cglib/proxy/MethodProxy;)Ljava/lang/Object;
    CHECKCAST java/lang/String
    ARETURN
   L1
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.toString ()Ljava/lang/String;
    ARETURN
    MAXSTACK = 5
    MAXLOCALS = 1

  // access flags 16
  final CGLIB$hashCode$3()I
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.hashCode ()I
    IRETURN
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 17
  public final hashCode()I
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    DUP
    IFNONNULL L0
    POP
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    DUP
    IFNULL L1
    ALOAD 0
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$hashCode$3$Method : Ljava/lang/reflect/Method;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$emptyArgs : [Ljava/lang/Object;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$hashCode$3$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    INVOKEINTERFACE net/sf/cglib/proxy/MethodInterceptor.intercept (Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lnet/sf/cglib/proxy/MethodProxy;)Ljava/lang/Object;
    DUP
    IFNONNULL L2
    POP
    ICONST_0
    GOTO L3
   L2
    CHECKCAST java/lang/Number
    INVOKEVIRTUAL java/lang/Number.intValue ()I
   L3
    IRETURN
   L1
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.hashCode ()I
    IRETURN
    MAXSTACK = 5
    MAXLOCALS = 1

  // access flags 16
  final CGLIB$clone$4()Ljava/lang/Object; throws java/lang/CloneNotSupportedException 
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.clone ()Ljava/lang/Object;
    ARETURN
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 20
  protected final clone()Ljava/lang/Object; throws java/lang/CloneNotSupportedException 
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    DUP
    IFNONNULL L0
    POP
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    DUP
    IFNULL L1
    ALOAD 0
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$clone$4$Method : Ljava/lang/reflect/Method;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$emptyArgs : [Ljava/lang/Object;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$clone$4$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    INVOKEINTERFACE net/sf/cglib/proxy/MethodInterceptor.intercept (Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lnet/sf/cglib/proxy/MethodProxy;)Ljava/lang/Object;
    ARETURN
   L1
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.clone ()Ljava/lang/Object;
    ARETURN
    MAXSTACK = 5
    MAXLOCALS = 1

  // access flags 16
  final CGLIB$helloAagin$5()V
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.helloAagin ()V
    RETURN
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 20
  protected final helloAagin()V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    DUP
    IFNONNULL L0
    POP
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    DUP
    IFNULL L1
    ALOAD 0
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$helloAagin$5$Method : Ljava/lang/reflect/Method;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$emptyArgs : [Ljava/lang/Object;
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$helloAagin$5$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    INVOKEINTERFACE net/sf/cglib/proxy/MethodInterceptor.intercept (Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lnet/sf/cglib/proxy/MethodProxy;)Ljava/lang/Object;
    RETURN
   L1
    ALOAD 0
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.helloAagin ()V
    RETURN
    MAXSTACK = 5
    MAXLOCALS = 1

  // access flags 9
  public static CGLIB$findMethodProxy(Lnet/sf/cglib/core/Signature;)Lnet/sf/cglib/proxy/MethodProxy;
    ALOAD 0
    INVOKEVIRTUAL java/lang/Object.toString ()Ljava/lang/String;
    DUP
    INVOKEVIRTUAL java/lang/Object.hashCode ()I
    LOOKUPSWITCH
      -1574182249: L0
      -508378822: L1
      949643067: L2
      1826985398: L3
      1913648695: L4
      1984935277: L5
      default: L6
   L0
    LDC "finalize()V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L7
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$finalize$0$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    ARETURN
   L1
    LDC "clone()Ljava/lang/Object;"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L7
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$clone$4$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    ARETURN
   L2
    LDC "helloAagin()V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L7
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$helloAagin$5$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    ARETURN
   L3
    LDC "equals(Ljava/lang/Object;)Z"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L7
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$equals$1$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    ARETURN
   L4
    LDC "toString()Ljava/lang/String;"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L7
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$toString$2$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    ARETURN
   L5
    LDC "hashCode()I"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L7
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$hashCode$3$Proxy : Lnet/sf/cglib/proxy/MethodProxy;
    ARETURN
   L6
    POP
   L7
    ACONST_NULL
    ARETURN
    MAXSTACK = 2
    MAXLOCALS = 1

  // access flags 1
  public <init>()V
    ALOAD 0
    DUP
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.<init> ()V
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    RETURN
    MAXSTACK = 2
    MAXLOCALS = 1

  // access flags 9
  public static CGLIB$SET_THREAD_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$THREAD_CALLBACKS : Ljava/lang/ThreadLocal;
    ALOAD 0
    INVOKEVIRTUAL java/lang/ThreadLocal.set (Ljava/lang/Object;)V
    RETURN
    MAXSTACK = 2
    MAXLOCALS = 1

  // access flags 9
  public static CGLIB$SET_STATIC_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V
    ALOAD 0
    PUTSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$STATIC_CALLBACKS : [Lnet/sf/cglib/proxy/Callback;
    RETURN
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 26
  private final static CGLIB$BIND_CALLBACKS(Ljava/lang/Object;)V
    ALOAD 0
    CHECKCAST com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad
    ASTORE 1
    ALOAD 1
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BOUND : Z
    IFNE L0
    ALOAD 1
    ICONST_1
    PUTFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BOUND : Z
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$THREAD_CALLBACKS : Ljava/lang/ThreadLocal;
    INVOKEVIRTUAL java/lang/ThreadLocal.get ()Ljava/lang/Object;
    DUP
    IFNONNULL L1
    POP
    GETSTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$STATIC_CALLBACKS : [Lnet/sf/cglib/proxy/Callback;
    DUP
    IFNONNULL L1
    POP
    GOTO L0
   L1
    CHECKCAST [Lnet/sf/cglib/proxy/Callback;
    ALOAD 1
    SWAP
    ICONST_0
    AALOAD
    CHECKCAST net/sf/cglib/proxy/MethodInterceptor
    PUTFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
   L0
    RETURN
    MAXSTACK = 3
    MAXLOCALS = 2

  // access flags 1
  public newInstance([Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;
    ALOAD 1
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$SET_THREAD_CALLBACKS ([Lnet/sf/cglib/proxy/Callback;)V
    NEW com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad
    DUP
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.<init> ()V
    ACONST_NULL
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$SET_THREAD_CALLBACKS ([Lnet/sf/cglib/proxy/Callback;)V
    ARETURN
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 1
  public newInstance(Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;
    ICONST_1
    ANEWARRAY net/sf/cglib/proxy/Callback
    DUP
    ICONST_0
    ALOAD 1
    AASTORE
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$SET_THREAD_CALLBACKS ([Lnet/sf/cglib/proxy/Callback;)V
    NEW com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad
    DUP
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.<init> ()V
    ACONST_NULL
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$SET_THREAD_CALLBACKS ([Lnet/sf/cglib/proxy/Callback;)V
    ARETURN
    MAXSTACK = 4
    MAXLOCALS = 2

  // access flags 1
  public newInstance([Ljava/lang/Class;[Ljava/lang/Object;[Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;
    ALOAD 3
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$SET_THREAD_CALLBACKS ([Lnet/sf/cglib/proxy/Callback;)V
    NEW com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad
    DUP
    ALOAD 1
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L0
      default: L1
   L0
    POP
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.<init> ()V
    GOTO L2
   L1
    GOTO L3
   L3
    POP
    NEW java/lang/IllegalArgumentException
    DUP
    LDC "Constructor not found"
    INVOKESPECIAL java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
    ATHROW
   L2
    ACONST_NULL
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$SET_THREAD_CALLBACKS ([Lnet/sf/cglib/proxy/Callback;)V
    ARETURN
    MAXSTACK = 5
    MAXLOCALS = 4

  // access flags 1
  public getCallback(I)Lnet/sf/cglib/proxy/Callback;
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    ILOAD 1
    TABLESWITCH
      0: L0
      default: L1
   L0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    GOTO L2
   L1
    POP
    ACONST_NULL
   L2
    ARETURN
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 1
  public setCallback(ILnet/sf/cglib/proxy/Callback;)V
    ILOAD 1
    TABLESWITCH
      0: L0
      default: L1
   L0
    ALOAD 0
    ALOAD 2
    CHECKCAST net/sf/cglib/proxy/MethodInterceptor
    PUTFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    GOTO L1
   L1
    RETURN
    MAXSTACK = 2
    MAXLOCALS = 3

  // access flags 1
  public getCallbacks()[Lnet/sf/cglib/proxy/Callback;
    ALOAD 0
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$BIND_CALLBACKS (Ljava/lang/Object;)V
    ALOAD 0
    ICONST_1
    ANEWARRAY net/sf/cglib/proxy/Callback
    DUP
    ICONST_0
    ALOAD 0
    GETFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    AASTORE
    ARETURN
    MAXSTACK = 5
    MAXLOCALS = 1

  // access flags 1
  public setCallbacks([Lnet/sf/cglib/proxy/Callback;)V
    ALOAD 0
    ALOAD 1
    DUP2
    ICONST_0
    AALOAD
    CHECKCAST net/sf/cglib/proxy/MethodInterceptor
    PUTFIELD com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$CALLBACK_0 : Lnet/sf/cglib/proxy/MethodInterceptor;
    RETURN
    MAXSTACK = 5
    MAXLOCALS = 2

  // access flags 8
  static <clinit>()V
    INVOKESTATIC com/jsonyao/cs/proxyPattern/HelloCglib$$EnhancerByCGLIB$$6bf7bfad.CGLIB$STATICHOOK1 ()V
    RETURN
    MAXSTACK = 0
    MAXLOCALS = 0
}
