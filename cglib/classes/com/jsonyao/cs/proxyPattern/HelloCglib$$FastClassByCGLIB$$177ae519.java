// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   <generated>

package com.jsonyao.cs.proxyPattern;

import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.core.Signature;
import net.sf.cglib.reflect.FastClass;

// Referenced classes of package com.jsonyao.cs.proxyPattern:
//            HelloCglib
// 20201113 HelloCglib代理后的FastClass反编译文件
public class HelloCglib$$FastClassByCGLIB$$177ae519 extends FastClass
{

    public int getIndex(Signature signature)
    {
        String s = signature.toString();
        s;
        s.hashCode();
        JVM INSTR lookupswitch 10: default 204
    //                   -1725733088: 100
    //                   -1026001249: 111
    //                   -792725149: 121
    //                   243996900: 131
    //                   946854621: 141
    //                   1116248544: 152
    //                   1826985398: 163
    //                   1902039948: 173
    //                   1913648695: 184
    //                   1984935277: 194;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L2:
        "getClass()Ljava/lang/Class;";
        equals();
        JVM INSTR ifeq 205;
           goto _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_205;
_L12:
        return 7;
_L3:
        "wait(JI)V";
        equals();
        JVM INSTR ifeq 205;
           goto _L14 _L15
_L15:
        break MISSING_BLOCK_LABEL_205;
_L14:
        return 4;
_L4:
        "hello()V";
        equals();
        JVM INSTR ifeq 205;
           goto _L16 _L17
_L17:
        break MISSING_BLOCK_LABEL_205;
_L16:
        return 3;
_L5:
        "wait(J)V";
        equals();
        JVM INSTR ifeq 205;
           goto _L18 _L19
_L19:
        break MISSING_BLOCK_LABEL_205;
_L18:
        return 5;
_L6:
        "notifyAll()V";
        equals();
        JVM INSTR ifeq 205;
           goto _L20 _L21
_L21:
        break MISSING_BLOCK_LABEL_205;
_L20:
        return 9;
_L7:
        "wait()V";
        equals();
        JVM INSTR ifeq 205;
           goto _L22 _L23
_L23:
        break MISSING_BLOCK_LABEL_205;
_L22:
        return 6;
_L8:
        "equals(Ljava/lang/Object;)Z";
        equals();
        JVM INSTR ifeq 205;
           goto _L24 _L25
_L25:
        break MISSING_BLOCK_LABEL_205;
_L24:
        return 0;
_L9:
        "notify()V";
        equals();
        JVM INSTR ifeq 205;
           goto _L26 _L27
_L27:
        break MISSING_BLOCK_LABEL_205;
_L26:
        return 8;
_L10:
        "toString()Ljava/lang/String;";
        equals();
        JVM INSTR ifeq 205;
           goto _L28 _L29
_L29:
        break MISSING_BLOCK_LABEL_205;
_L28:
        return 1;
_L11:
        "hashCode()I";
        equals();
        JVM INSTR ifeq 205;
           goto _L30 _L31
_L31:
        break MISSING_BLOCK_LABEL_205;
_L30:
        return 2;
_L1:
        JVM INSTR pop ;
        return -1;
    }

    public int getIndex(String s, Class aclass[])
    {
        s;
        aclass;
        JVM INSTR swap ;
        JVM INSTR dup ;
        hashCode();
        JVM INSTR lookupswitch 8: default 435
    //                   -1776922004: 80
    //                   -1295482945: 114
    //                   -1039689911: 164
    //                   3641717: 199
    //                   99162322: 291
    //                   147696667: 326
    //                   1902066072: 362
    //                   1950568386: 399;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L2:
        "toString";
        equals();
        JVM INSTR ifeq 436;
           goto _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_439;
_L10:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 0: default 111
    //                   0 108;
           goto _L12 _L13
_L12:
        break MISSING_BLOCK_LABEL_439;
_L13:
        JVM INSTR pop ;
        return 1;
_L3:
        "equals";
        equals();
        JVM INSTR ifeq 436;
           goto _L14 _L15
_L15:
        break MISSING_BLOCK_LABEL_439;
_L14:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 1 1: default 161
    //                   1 144;
           goto _L16 _L17
_L16:
        break MISSING_BLOCK_LABEL_439;
_L17:
        JVM INSTR dup ;
        0;
        JVM INSTR aaload ;
        getName();
        "java.lang.Object";
        equals();
        JVM INSTR ifeq 439;
           goto _L18 _L19
_L19:
        break MISSING_BLOCK_LABEL_439;
_L18:
        JVM INSTR pop ;
        return 0;
_L4:
        "notify";
        equals();
        JVM INSTR ifeq 436;
           goto _L20 _L21
_L21:
        break MISSING_BLOCK_LABEL_439;
_L20:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 0: default 196
    //                   0 192;
           goto _L22 _L23
_L22:
        break MISSING_BLOCK_LABEL_439;
_L23:
        JVM INSTR pop ;
        return 8;
_L5:
        "wait";
        equals();
        JVM INSTR ifeq 436;
           goto _L24 _L25
_L25:
        break MISSING_BLOCK_LABEL_439;
_L24:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 2: default 288
    //                   0 236
    //                   1 240
    //                   2 257;
           goto _L26 _L27 _L28 _L29
_L26:
        break MISSING_BLOCK_LABEL_439;
_L27:
        JVM INSTR pop ;
        return 6;
_L28:
        JVM INSTR dup ;
        0;
        JVM INSTR aaload ;
        getName();
        "long";
        equals();
        JVM INSTR ifeq 439;
           goto _L30 _L31
_L31:
        break MISSING_BLOCK_LABEL_439;
_L30:
        JVM INSTR pop ;
        return 5;
_L29:
        JVM INSTR dup ;
        0;
        JVM INSTR aaload ;
        getName();
        "long";
        equals();
        JVM INSTR ifeq 439;
           goto _L32 _L33
_L33:
        break MISSING_BLOCK_LABEL_439;
_L32:
        JVM INSTR dup ;
        1;
        JVM INSTR aaload ;
        getName();
        "int";
        equals();
        JVM INSTR ifeq 439;
           goto _L34 _L35
_L35:
        break MISSING_BLOCK_LABEL_439;
_L34:
        JVM INSTR pop ;
        return 4;
_L6:
        "hello";
        equals();
        JVM INSTR ifeq 436;
           goto _L36 _L37
_L37:
        break MISSING_BLOCK_LABEL_439;
_L36:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 0: default 323
    //                   0 320;
           goto _L38 _L39
_L38:
        break MISSING_BLOCK_LABEL_439;
_L39:
        JVM INSTR pop ;
        return 3;
_L7:
        "hashCode";
        equals();
        JVM INSTR ifeq 436;
           goto _L40 _L41
_L41:
        break MISSING_BLOCK_LABEL_439;
_L40:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 0: default 359
    //                   0 356;
           goto _L42 _L43
_L42:
        break MISSING_BLOCK_LABEL_439;
_L43:
        JVM INSTR pop ;
        return 2;
_L8:
        "notifyAll";
        equals();
        JVM INSTR ifeq 436;
           goto _L44 _L45
_L45:
        break MISSING_BLOCK_LABEL_439;
_L44:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 0: default 396
    //                   0 392;
           goto _L46 _L47
_L46:
        break MISSING_BLOCK_LABEL_439;
_L47:
        JVM INSTR pop ;
        return 9;
_L9:
        "getClass";
        equals();
        JVM INSTR ifeq 436;
           goto _L48 _L49
_L49:
        break MISSING_BLOCK_LABEL_439;
_L48:
        JVM INSTR dup ;
        JVM INSTR arraylength .length;
        JVM INSTR tableswitch 0 0: default 432
    //                   0 428;
           goto _L50 _L51
_L50:
        break MISSING_BLOCK_LABEL_439;
_L51:
        JVM INSTR pop ;
        return 7;
_L1:
        JVM INSTR pop ;
        JVM INSTR pop ;
        return -1;
    }

    public int getIndex(Class aclass[])
    {
        aclass;
        aclass.length;
        JVM INSTR tableswitch 0 0: default 23
    //                   0 20;
           goto _L1 _L2
_L2:
        JVM INSTR pop ;
        return 0;
_L1:
        JVM INSTR pop ;
        return -1;
    }

    public Object invoke(int i, Object obj, Object aobj[])
        throws InvocationTargetException
    {
        (HelloCglib)obj;
        i;
        JVM INSTR tableswitch 0 9: default 152
    //                   0 60
    //                   1 75
    //                   2 79
    //                   3 91
    //                   4 96
    //                   5 119
    //                   6 133
    //                   7 138
    //                   8 142
    //                   9 147;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L2:
        aobj[0];
        equals();
        JVM INSTR new #80  <Class Boolean>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        Boolean();
        return;
_L3:
        toString();
        return;
_L4:
        hashCode();
        JVM INSTR new #87  <Class Integer>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        Integer();
        return;
_L5:
        hello();
        return null;
_L6:
        ((Number)aobj[0]).longValue();
        ((Number)aobj[1]).intValue();
        wait();
        return null;
_L7:
        ((Number)aobj[0]).longValue();
        wait();
        return null;
_L8:
        wait();
        return null;
_L9:
        getClass();
        return;
_L10:
        notify();
        return null;
_L11:
        notifyAll();
        return null;
        JVM INSTR new #75  <Class InvocationTargetException>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        InvocationTargetException();
        throw ;
_L1:
        throw new IllegalArgumentException("Cannot find matching method/constructor");
    }

    public Object newInstance(int i, Object aobj[])
        throws InvocationTargetException
    {
        JVM INSTR new #77  <Class HelloCglib>;
        JVM INSTR dup ;
        i;
        JVM INSTR tableswitch 0 0: default 28
    //                   0 24;
           goto _L1 _L2
_L2:
        HelloCglib();
        return;
        JVM INSTR new #75  <Class InvocationTargetException>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        InvocationTargetException();
        throw ;
_L1:
        throw new IllegalArgumentException("Cannot find matching method/constructor");
    }

    public int getMaxIndex()
    {
        return 9;
    }

    public HelloCglib$$FastClassByCGLIB$$177ae519(Class class1)
    {
        super(class1);
    }
}
