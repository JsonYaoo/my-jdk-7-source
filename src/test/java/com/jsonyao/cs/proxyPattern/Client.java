package com.jsonyao.cs.proxyPattern;

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
 * D. 优点:
 *      a. 职责清晰: 真实角色就是实现实际业务逻辑的, 不用去关心其他非本职的业务, 而通过后期的代理去完成那些非真实角色本职的业务, 编程简洁清晰
 *      b. 保护目标对象: 在客户端和目标对象中间存在代理对象, 起到中介以及保护目标对象的作用
 *      c. 高扩展性: 符合开闭原则, 代理类的实现不是通过修改原有的代码, 而是通过扩展的方式织入新的业务代码, 符合对修改关闭, 对扩展开放的原则
 * E. Relation:
 *      a. https://baike.baidu.com/item/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F/8374046?fr=aladdin
 *      b. https://www.cnblogs.com/cenyu/p/6289209.html
 *      c. https://blog.csdn.net/qq_27605885/article/details/81782916?utm_medium=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase&depth_1-utm_source=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase
 *      d. https://blog.csdn.net/wiseyl/article/details/14046975
 *      e. https://blog.csdn.net/xuerong_zhu/article/details/103896138/
 */
public class Client {

    public static void main(String[] args) {
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
         *      D.
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
         */
        // 反编译命令 jad -s java $proxy0.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        UserService userService = (UserService) new JdkProxyFactory(new UserServiceImpl()).getInstance();
        userService.save();
    }

}
