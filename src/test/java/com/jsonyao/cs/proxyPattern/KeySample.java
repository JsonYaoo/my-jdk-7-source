package com.jsonyao.cs.proxyPattern;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.KeyFactory;

/**
 * KeyFactory测试类
 */
public class KeySample {

    private interface MyFactory {
        public Object newInstance(int a, char[] b, String d);
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".\\cglib\\classes");

        MyFactory myFactory = (MyFactory) KeyFactory.create(MyFactory.class);

        // 构造multi-values key对象
        Object key1 = myFactory.newInstance(20, new char[]{'a', 'b'}, "hello");
        Object key2 =  myFactory.newInstance(20, new char[]{'a', 'b'}, "hello");
        Object key3 = myFactory.newInstance(20, new char[]{'a', '_'}, "hello");

        System.out.println(key1.toString());// 20, {a, b}, hello
        System.out.println(key2.toString());// 20, {a, b}, hello
        System.out.println(key3.toString());// 0, {a, _}, hello

        System.out.println(key1.equals(key2));// true
        System.out.println(key1.equals(key3));// false
        System.out.println(key3.equals(key2));// false
    }

}
