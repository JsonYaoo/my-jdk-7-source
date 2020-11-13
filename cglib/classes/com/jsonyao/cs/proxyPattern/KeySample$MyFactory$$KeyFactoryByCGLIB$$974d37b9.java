// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   <generated>

package com.jsonyao.cs.proxyPattern;

import net.sf.cglib.core.KeyFactory;

// 20201113 KeyFactory测试类KeySample main生成的反编译文件 -> 继承KeyFactory, 实现MyFactory
public class KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9 extends KeyFactory implements KeySample.MyFactory
{

    // 20201113 实现了MyFactory接口的newInstance()方法
    public Object newInstance(int i, char ac[], String s)
    {
        // 20201113 调用本身的有参构造方法 -> 构造multi-values key对象
        return new KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9(i, ac, s);
    }

    // 20201113 hashCode()
    public int hashCode()
    {
        179;
        FIELD_0;
        JVM INSTR swap ;
        0x72077;
        JVM INSTR imul ;
        JVM INSTR swap ;
        JVM INSTR iadd ;
        FIELD_1;
        if(FIELD_1 == null)
            break MISSING_BLOCK_LABEL_48;
        char ac[];
        ac;
        int i = 0;
          goto _L1
_L3:
        ac[i];
        JVM INSTR swap ;
        0x72077;
        JVM INSTR imul ;
        JVM INSTR swap ;
        JVM INSTR iadd ;
        i++;
_L1:
        if(i < ac.length) goto _L3; else goto _L2
_L2:
        break MISSING_BLOCK_LABEL_49;
        JVM INSTR pop ;
        FIELD_2;
        JVM INSTR swap ;
        0x72077;
        JVM INSTR imul ;
        JVM INSTR swap ;
        JVM INSTR dup ;
        JVM INSTR ifnull 68;
           goto _L4 _L5
_L4:
        hashCode();
          goto _L6
_L5:
        JVM INSTR pop ;
        false;
_L6:
        JVM INSTR iadd ;
        return;
    }

    // 20201113 equals()
    public boolean equals(Object obj)
    {
        if(!(obj instanceof KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9) || FIELD_0 != ((KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9)obj).FIELD_0)
            break MISSING_BLOCK_LABEL_133;
        FIELD_1;
        ((KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9)obj).FIELD_1;
        JVM INSTR dup2 ;
        JVM INSTR ifnonnull 43;
           goto _L1 _L2
_L1:
        break MISSING_BLOCK_LABEL_36;
_L2:
        break MISSING_BLOCK_LABEL_43;
        JVM INSTR ifnonnull 49;
           goto _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_39;
_L4:
        break MISSING_BLOCK_LABEL_49;
        JVM INSTR pop2 ;
        break MISSING_BLOCK_LABEL_93;
        JVM INSTR ifnull 49;
           goto _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_49;
_L5:
        break MISSING_BLOCK_LABEL_53;
        JVM INSTR pop2 ;
        break MISSING_BLOCK_LABEL_133;
        JVM INSTR dup2 ;
        JVM INSTR arraylength .length;
        JVM INSTR swap ;
        JVM INSTR arraylength .length;
        JVM INSTR icmpeq 64;
           goto _L7 _L8
_L7:
        break MISSING_BLOCK_LABEL_60;
_L8:
        break MISSING_BLOCK_LABEL_64;
        JVM INSTR pop2 ;
        break MISSING_BLOCK_LABEL_133;
        char ac[];
        ac;
        char ac1[];
        ac1;
        for(int i = 0; i < ac.length; i++)
            if(ac[i] != ac1[i])
                break MISSING_BLOCK_LABEL_133;

        FIELD_2;
        ((KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9)obj).FIELD_2;
        JVM INSTR dup2 ;
        JVM INSTR ifnonnull 115;
           goto _L9 _L10
_L9:
        break MISSING_BLOCK_LABEL_108;
_L10:
        break MISSING_BLOCK_LABEL_115;
        JVM INSTR ifnonnull 121;
           goto _L11 _L12
_L11:
        break MISSING_BLOCK_LABEL_111;
_L12:
        break MISSING_BLOCK_LABEL_121;
        JVM INSTR pop2 ;
        break MISSING_BLOCK_LABEL_131;
        JVM INSTR ifnull 121;
           goto _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_121;
_L13:
        break MISSING_BLOCK_LABEL_125;
        JVM INSTR pop2 ;
        break MISSING_BLOCK_LABEL_133;
        equals();
        JVM INSTR ifeq 133;
           goto _L15 _L16
_L15:
        break MISSING_BLOCK_LABEL_131;
_L16:
        break MISSING_BLOCK_LABEL_133;
        return true;
        return false;
    }

    // 20201113 toString()
    public String toString()
    {
        (new StringBuffer()).append(FIELD_0).append(", ");
        FIELD_1;
        if(FIELD_1 == null) goto _L2; else goto _L1
_L1:
        JVM INSTR swap ;
        "{";
        append();
        JVM INSTR swap ;
        char ac[];
        ac;
        int i = 0;
          goto _L3
_L5:
        ac[i];
        append();
        ", ";
        append();
        i++;
_L3:
        if(i < ac.length) goto _L5; else goto _L4
_L4:
        JVM INSTR dup ;
        JVM INSTR dup ;
        length();
        2;
        JVM INSTR isub ;
        setLength();
        "}";
        append();
          goto _L6
_L2:
        JVM INSTR pop ;
        "null";
        append();
_L6:
        ", ";
        append();
        FIELD_2;
        if(FIELD_2 == null) goto _L8; else goto _L7
_L7:
        toString();
        append();
          goto _L9
_L8:
        JVM INSTR pop ;
        "null";
        append();
_L9:
        toString();
        return;
    }

    // 20201113 成员变量为muli-values结构
    private final int FIELD_0;
    private final char FIELD_1[];
    private final String FIELD_2;

    // 20201113 无参构造函数
    public KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9()
    {
    }

    // 20201113 有参构造方法
    public KeySample$MyFactory$$KeyFactoryByCGLIB$$974d37b9(int i, char ac[], String s)
    {
        this;
        FIELD_0 = i;
        FIELD_1 = ac;
        FIELD_2 = s;
        return;
    }
}
