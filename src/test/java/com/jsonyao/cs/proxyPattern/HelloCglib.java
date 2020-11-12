package com.jsonyao.cs.proxyPattern;

/**
 * CGLIB委托测试类
 */
public class HelloCglib {

    public final void hello(){
        System.out.println("你好, CGLIB...");
    }

    protected void helloAagin(){
        System.out.println("你好你好, CGLIB...");
    }

    public HelloCglib() {
        super();
        System.out.println("测试 HelloCglib()");
    }

    @Override
    public int hashCode() {
        System.out.println("测试 hashCode()");
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("测试 equals()");
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("测试 clone()");
        return super.clone();
    }

    @Override
    public String toString() {
        System.out.println("测试 toString()");
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("测试 finalize()");
        super.finalize();
    }
}
