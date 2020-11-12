package com.jsonyao.cs.proxyPattern;

import net.sf.cglib.proxy.Enhancer;

/**
 * 代理模式
 * A. 定义:
 *      a. 为其他对象提供一种代理, 以控制对这个对象的访问. 在某种情况下, 一个对象不适合或者不能直接引用另一个对象, 而代理对象可以在客户端和目标对象之间起到中介的作用
 * B. 组成:
 *      a. 抽象角色: 通过接口或者抽象类声明真实角色实现的业务方法
 *      b. 代理角色: 实现抽象角色, 是真实角色的代理, 通过真实角色的业务逻辑方法来实现抽象方法(持有真实角色对象的引用), 并可以附加自己的操作
 *      c. 真实角色: 实现抽象角色, 定义真实角色所要实现的业务逻辑, 供代理角色调用
 * C. 分类:
 *      a. 静态代理: 静态代理是由程序员创建或者工具生成代理类的源码, 在编译代理类, 即所谓静态也就是程序运行前就已经存在代理类的字节码文件, 这时代理类和委托了类的关系在运行前就确定了
 *          => 注意这里说的是, 代理类和委托类之间的关系, 而不是代理类的创建
 *      b. 动态代理: 动态代理在实现阶段不用关心代理类, 而在运行阶段才指定哪一个对象
 * D. JDK动态代理 & CGLIB动态代理:
 *      a. JDK动态代理: 实现InvocationHandler的处理类, 利用反射机制生成一个实现了委托类接口(可以调用被代理方法) 和 继承了Proxy类(为了
 *         持有已经实现InvocationHandler实例的引用) 的代理类, 使得在调用代理类具体方法时去调用实现InvocationHandler接口的处理类里的方法,
 *         但是只能对实现了接口的类生成代理
 *      b. CGLIB动态代理: 利用ASM开源包, 通过修改委托类的Class文件的字节码生成子类来处理, 其中主要是生成的子类去覆盖原本委托类中的方法,
 *         并在覆盖方法中实现增强, 但是因为采用的是继承, 所以对于final类或者方法是无法继承和代理的
 *      c. Spring中的选择实现:
 *          c.1. 如果目标对象实现了接口, 默认情况下会采用JDK动态代理实现AOP
 *          c.2. 如果目标对象实现了接口, 还可以强制使用CGLIB实现AOP
 *          c.3. 如果目标对象没有实现接口, 则必须采用CGLIB进行动态代理, 实现AOP
 *              => 可以看到Spring会自动在JDK动态代理和CGLIB动态代理之间转换
 * E. 优点:
 *      a. 职责清晰: 真实角色就是实现实际业务逻辑的, 不用去关心其他非本职的业务, 而通过后期的代理去完成那些非真实角色本职的业务, 编程简洁清晰
 *      b. 保护目标对象: 在客户端和目标对象中间存在代理对象, 起到中介以及保护目标对象的作用
 *      c. 高扩展性: 符合开闭原则, 代理类的实现不是通过修改原有的代码, 而是通过扩展的方式织入新的业务代码, 符合对修改关闭, 对扩展开放的原则
 * E. Relation:
 *      a. https://baike.baidu.com/item/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F/8374046?fr=aladdin
 *      b. https://www.cnblogs.com/cenyu/p/6289209.html
 *      c. http://hg.openjdk.java.net
 *      d. https://blog.csdn.net/wiseyl/article/details/14046975
 *      e. https://blog.csdn.net/xuerong_zhu/article/details/103896138/
 */
public class Client {

    public static void main(String[] args) throws Throwable {
        /**
         * 1、静态代理
         *      A. 概念:
         *          a. 静态代理在使用时, 需要定义接口或者父类, 被代理对象与代理对象一起实现相同的接口或者是继承相同的父类, 关键的是, 被代理对象一定要在接口或者父类中
         *             声明自己需要被代理方法, 以供代理对象调用再封装
         *          b. 其中, 代理角色需要持有真实角色的引用, 以便在实现真实角色的真实方法前后, 进行代理织入新的业务逻辑
         *      B. 优点:
         *          a. 可以做到在不修改目标对象功能的前提下, 对目标功能扩展
         *      C. 缺点:
         *          a. 每一个代理类都必须实现一遍委托类用于声明实现方法的接口, 如果接口增加方法, 则代理类也必须跟着修改
         *          b. 其次, 代理类每一个接口对象对应一个委托类, 如果委托对象非常多, 则静态代理类就会非常臃肿, 难以胜任
         */
//        UserService userService = new UserServiceImplStaticProxy(new UserServiceImpl());
//        userService.save();

        /**
         * 2、JDK动态代理
         *      A. 概念:
         *          a. 为了解决静态代理中代理类接口过多的问题, 可以通过JDK自带的java.lang.reflect.Proxy类, 通过反射实现代理
         *      B. 使用:
         *          a. 编写一个委托类的接口, 即静态代理的接口UserService, 把实现方法save()声明出去
         *          b. 实现一个真正的委托类, 即静态代理的UserServiceImpl, 同时接口save()方法
         *          c. 创建一个动态代理类, 实现InvocationHandler接口, 并重写invoke方法织入需要代理的业务逻辑, 其中动态代理类需要持有委托类的引用,
         *             用于反射调用委托类的save()实现方法
         *          d. 在客户端中生成动态代理类对象, 调用声明的save()方法
         *      C. newProxyInstance()方法参数:
         *          a. ClassLoader loader: 指定当前目标对象的使用类加载器
         *          b. Class<?>[] interfaces: 目标对象实现的接口类型, 使用泛型方式确认类型
         *          c. InvocationHandler h: 事件处理, 代理对象方法调用个实际处理者. 在执行目标对象的方法时, 会触发事件处理器, 把当前执行目标对象的方法作为参数传入, 供代理对象使用
         *      D. 原理:
         *          a. newProxyInstance()通过反射生成含有接口方法的Proxy Class, 其中Proxy Class又继承了Proxy类, 父类持有实现代理接口的引用,
         *             最后该代理对象的所有方法调用都会转发到InvocationHandler.invoke()方法, 实现动态代理
         */
//        UserServiceImplJdkProxy userServiceImplJdkProxy = new UserServiceImplJdkProxy(new UserServiceImpl());
//        UserService userService = (UserService) Proxy.newProxyInstance(
//                UserServiceImpl.class.getClassLoader(), new Class[]{UserService.class}, userServiceImplJdkProxy);
//        userService.save();

        /**
         * 3、JDK动态代理优化-构建动态代理工厂
         *      A. 由例子可见, 在B.c.步中, 实现InvocationHandler接口可以通过内部类实现, 而持有委托类的引用可以通过参数传入,
         *         因此, 可以抽取公共的方法, 使用简单工厂模式构建一个动态代理类工厂
         *      B. 通过传入委托类的引用创建动态代理类工厂, 调用动态代理类工厂的getInstance()方法获取代理类对象的实例
         *      C. 优点:
         *          a. 通过内部类实现InvocationHandler接口, 通过工厂类newProxyInstance()可以不用再编写代理类即可获得代理对象, 简化编程
         */
        // 反编译命令 jad -s java $proxy0.class
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        UserService userService = (UserService) new JdkProxyFactory(new UserServiceImpl()).getInstance();
//        userService.save();

        /**
         * 4、CGLIB动态代理
         *      A. 背景:
         *          a. 解决没有实现任何接口的委托类的代理
         *      B. 使用:
         *          a. 首先实现一个MethodInterceptor, 方法调用会被转发到该类的intercept()方法
         *          b. 然后在需要使用HelloCglib的时候, 通过CGLIB动态代理获取代理对象
         *          c. 通过CGLIB的Enhancer来指定要代理的目标对象, 即实际处理逻辑的对象, 最终通过调用create()方法得到代理对象
         *          d. 对这个代理对象所有非final方法的调用都会转发给MethodInterceptor.intercept()方法, 在intercept()方法里可以加入任何逻辑,
         *             比如修改方法参数、安全检查功能等
         *          e. 最后通过调用MethodProxy.invokeSuper()方法, 将调用转发给原始对象, 即HelloCglib的具体方法
         *      C. 原理:
         *          a. CGLIB是一个强大的高性能代码生成包, 底层是通过使用一个小而快的字节码处理框架ASM进行动态代理, 可以在运行期扩展Java类与实现Java接口
         *          b. 其中Enhancer是CGLIB的字节码增强器, 可以很方便的对类进行拓展, 主要原理是把拦截器设置在了Enhancer的成员变量中
         *          c. 创建代理对象的几个步骤:
         *              c.1. 生成代理类的二进制字节码文件
         *              c.2. 加载二进制字节码, 生成Class对象
         *              c.3. 通过反射机制获得实例构造, 并创建代理类对象
         */
        // 初始化Enhancer实例
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloCglib.class);
        enhancer.setCallback(new MyMethodInterceptor());

        HelloCglib helloCglib = (HelloCglib) enhancer.create();// 调用构造函数
        helloCglib.hello();// final方法不会被代理

        System.out.println();
        helloCglib.helloAagin();// protect方法依然会被代理

        System.out.println();
        HelloCglib testConstructor = new HelloCglib();

        System.out.println();
        helloCglib.hashCode();

        System.out.println();
        helloCglib.equals(testConstructor);

//        System.out.println();
//        Object clone = helloCglib.clone();// clone本地方法代理后抛出CloneNotSupportedException异常

        System.out.println();
        helloCglib.toString();// 连父类的toString()方法也会代理

        System.out.println();
        helloCglib.finalize();
    }

}
