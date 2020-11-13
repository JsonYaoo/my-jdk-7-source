// class version 46.0 (46)
// access flags 1
public class com/jsonyao/cs/proxyPattern/HelloCglib$$FastClassByCGLIB$$177ae519 extends net/sf/cglib/reflect/FastClass  {

  // compiled from: <generated>

  // access flags 1
  public <init>(Ljava/lang/Class;)V
    ALOAD 0
    ALOAD 1
    INVOKESPECIAL net/sf/cglib/reflect/FastClass.<init> (Ljava/lang/Class;)V
    RETURN
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 1
  public getIndex(Lnet/sf/cglib/core/Signature;)I
    ALOAD 1
    INVOKEVIRTUAL java/lang/Object.toString ()Ljava/lang/String;
    DUP
    INVOKEVIRTUAL java/lang/Object.hashCode ()I
    LOOKUPSWITCH
      -1725733088: L0
      -1026001249: L1
      -792725149: L2
      243996900: L3
      946854621: L4
      1116248544: L5
      1826985398: L6
      1902039948: L7
      1913648695: L8
      1984935277: L9
      default: L10
   L0
    LDC "getClass()Ljava/lang/Class;"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    BIPUSH 7
    IRETURN
   L1
    LDC "wait(JI)V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    ICONST_4
    IRETURN
   L2
    LDC "hello()V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    ICONST_3
    IRETURN
   L3
    LDC "wait(J)V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    ICONST_5
    IRETURN
   L4
    LDC "notifyAll()V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    BIPUSH 9
    IRETURN
   L5
    LDC "wait()V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    BIPUSH 6
    IRETURN
   L6
    LDC "equals(Ljava/lang/Object;)Z"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    ICONST_0
    IRETURN
   L7
    LDC "notify()V"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    BIPUSH 8
    IRETURN
   L8
    LDC "toString()Ljava/lang/String;"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    ICONST_1
    IRETURN
   L9
    LDC "hashCode()I"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L11
    ICONST_2
    IRETURN
   L10
    POP
   L11
    ICONST_M1
    IRETURN
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 1
  public getIndex(Ljava/lang/String;[Ljava/lang/Class;)I
    ALOAD 1
    ALOAD 2
    SWAP
    DUP
    INVOKEVIRTUAL java/lang/Object.hashCode ()I
    LOOKUPSWITCH
      -1776922004: L0
      -1295482945: L1
      -1039689911: L2
      3641717: L3
      99162322: L4
      147696667: L5
      1902066072: L6
      1950568386: L7
      default: L8
   L0
    LDC "toString"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L10
      default: L11
   L10
    POP
    ICONST_1
    IRETURN
   L11
    GOTO L12
   L1
    LDC "equals"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      1: L13
      default: L14
   L13
    DUP
    ICONST_0
    AALOAD
    INVOKEVIRTUAL java/lang/Class.getName ()Ljava/lang/String;
    LDC "java.lang.Object"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L12
    POP
    ICONST_0
    IRETURN
   L14
    GOTO L12
   L2
    LDC "notify"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L15
      default: L16
   L15
    POP
    BIPUSH 8
    IRETURN
   L16
    GOTO L12
   L3
    LDC "wait"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L17
      1: L18
      2: L19
      default: L20
   L17
    POP
    BIPUSH 6
    IRETURN
   L18
    DUP
    ICONST_0
    AALOAD
    INVOKEVIRTUAL java/lang/Class.getName ()Ljava/lang/String;
    LDC "long"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L12
    POP
    ICONST_5
    IRETURN
   L19
    DUP
    ICONST_0
    AALOAD
    INVOKEVIRTUAL java/lang/Class.getName ()Ljava/lang/String;
    LDC "long"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L12
    DUP
    ICONST_1
    AALOAD
    INVOKEVIRTUAL java/lang/Class.getName ()Ljava/lang/String;
    LDC "int"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L12
    POP
    ICONST_4
    IRETURN
   L20
    GOTO L12
   L4
    LDC "hello"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L21
      default: L22
   L21
    POP
    ICONST_3
    IRETURN
   L22
    GOTO L12
   L5
    LDC "hashCode"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L23
      default: L24
   L23
    POP
    ICONST_2
    IRETURN
   L24
    GOTO L12
   L6
    LDC "notifyAll"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L25
      default: L26
   L25
    POP
    BIPUSH 9
    IRETURN
   L26
    GOTO L12
   L7
    LDC "getClass"
    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IFEQ L9
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L27
      default: L28
   L27
    POP
    BIPUSH 7
    IRETURN
   L28
    GOTO L12
   L8
    POP
   L9
    GOTO L12
   L12
    POP
    ICONST_M1
    IRETURN
    MAXSTACK = 3
    MAXLOCALS = 3

  // access flags 1
  public getIndex([Ljava/lang/Class;)I
    ALOAD 1
    DUP
    ARRAYLENGTH
    TABLESWITCH
      0: L0
      default: L1
   L0
    POP
    ICONST_0
    IRETURN
   L1
    GOTO L2
   L2
    POP
    ICONST_M1
    IRETURN
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 1
  public invoke(ILjava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; throws java/lang/reflect/InvocationTargetException 
    TRYCATCHBLOCK L0 L1 L1 java/lang/Throwable
    ALOAD 2
    CHECKCAST com/jsonyao/cs/proxyPattern/HelloCglib
    ILOAD 1
   L0
    TABLESWITCH
      0: L2
      1: L3
      2: L4
      3: L5
      4: L6
      5: L7
      6: L8
      7: L9
      8: L10
      9: L11
      default: L12
   L2
    ALOAD 3
    ICONST_0
    AALOAD
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.equals (Ljava/lang/Object;)Z
    NEW java/lang/Boolean
    DUP_X1
    SWAP
    INVOKESPECIAL java/lang/Boolean.<init> (Z)V
    ARETURN
   L3
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.toString ()Ljava/lang/String;
    ARETURN
   L4
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.hashCode ()I
    NEW java/lang/Integer
    DUP_X1
    SWAP
    INVOKESPECIAL java/lang/Integer.<init> (I)V
    ARETURN
   L5
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.hello ()V
    ACONST_NULL
    ARETURN
   L6
    ALOAD 3
    ICONST_0
    AALOAD
    CHECKCAST java/lang/Number
    INVOKEVIRTUAL java/lang/Number.longValue ()J
    ALOAD 3
    ICONST_1
    AALOAD
    CHECKCAST java/lang/Number
    INVOKEVIRTUAL java/lang/Number.intValue ()I
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.wait (JI)V
    ACONST_NULL
    ARETURN
   L7
    ALOAD 3
    ICONST_0
    AALOAD
    CHECKCAST java/lang/Number
    INVOKEVIRTUAL java/lang/Number.longValue ()J
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.wait (J)V
    ACONST_NULL
    ARETURN
   L8
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.wait ()V
    ACONST_NULL
    ARETURN
   L9
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.getClass ()Ljava/lang/Class;
    ARETURN
   L10
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.notify ()V
    ACONST_NULL
    ARETURN
   L11
    INVOKEVIRTUAL com/jsonyao/cs/proxyPattern/HelloCglib.notifyAll ()V
    ACONST_NULL
    ARETURN
   L12
    GOTO L13
   L1
    NEW java/lang/reflect/InvocationTargetException
    DUP_X1
    SWAP
    INVOKESPECIAL java/lang/reflect/InvocationTargetException.<init> (Ljava/lang/Throwable;)V
    ATHROW
   L13
    NEW java/lang/IllegalArgumentException
    DUP
    LDC "Cannot find matching method/constructor"
    INVOKESPECIAL java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
    ATHROW
    MAXSTACK = 5
    MAXLOCALS = 4

  // access flags 1
  public newInstance(I[Ljava/lang/Object;)Ljava/lang/Object; throws java/lang/reflect/InvocationTargetException 
    TRYCATCHBLOCK L0 L1 L1 java/lang/Throwable
    NEW com/jsonyao/cs/proxyPattern/HelloCglib
    DUP
    ILOAD 1
   L0
    TABLESWITCH
      0: L2
      default: L3
   L2
    INVOKESPECIAL com/jsonyao/cs/proxyPattern/HelloCglib.<init> ()V
    ARETURN
   L3
    GOTO L4
   L1
    NEW java/lang/reflect/InvocationTargetException
    DUP_X1
    SWAP
    INVOKESPECIAL java/lang/reflect/InvocationTargetException.<init> (Ljava/lang/Throwable;)V
    ATHROW
   L4
    NEW java/lang/IllegalArgumentException
    DUP
    LDC "Cannot find matching method/constructor"
    INVOKESPECIAL java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
    ATHROW
    MAXSTACK = 5
    MAXLOCALS = 3

  // access flags 1
  public getMaxIndex()I
    BIPUSH 9
    IRETURN
    MAXSTACK = 1
    MAXLOCALS = 1
}
