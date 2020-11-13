/*
 * Copyright 2003,2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.cglib.core;

import java.io.*;
import java.util.*;
import java.lang.ref.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

/**
 * Abstract class for all code-generating CGLIB utilities.
 * In addition to caching generated classes for performance, it provides hooks for
 * customizing the <code>ClassLoader</code>, name of the generated class, and transformations
 * applied before generation.
 */
// 20201113 AbstractClassGenerator采用模板方法设计模式, create(Object key)就是模板方法, 定义了类生成的策略, 没有实现ClassGenerator的generateClass()方法, 所以为抽象类
abstract public class AbstractClassGenerator implements ClassGenerator
{
    private static final Object NAME_KEY = new Object();
    private static final ThreadLocal CURRENT = new ThreadLocal();

    private GeneratorStrategy strategy = DefaultGeneratorStrategy.INSTANCE;// 20201113 DefaultGeneratorStrategy是CGLIB提供的一个默认的生成策略
    private NamingPolicy namingPolicy = DefaultNamingPolicy.INSTANCE;// 20201113 默认命名策略
    private Source source;
    private ClassLoader classLoader;
    private String namePrefix;
    private Object key;
    private boolean useCache = true;
    private String className;
    private boolean attemptLoad;

    // 20201113 Source是AbstractClassGenerator的一个静态内部类
    protected static class Source {
        String name;
        Map cache = new WeakHashMap();// 20201113 Cache是缓存, WeakHashMap -> <ClassLoader, <Object, class>>, 类加载器缓存, 同JDK动态代理
        public Source(String name) {
            this.name = name;// 20201113 name用来记录Class Generator
        }
    }

    // 20201113 AbstractClassGenerator唯一一个构造函数
    protected AbstractClassGenerator(Source source) {
        this.source = source;
    }

    protected void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    // 20201113 Enhancer.generateClass()调用AbstractClassGenerator.getClassName()方法生成className
    final protected String getClassName() {
        if (className == null)
            className = getClassName(getClassLoader());// 20201113 如果成员变量name为空, 根据类加载器获取ClassName
        return className;
    }

    // 20201113 根据类加载器获取ClassName
    private String getClassName(final ClassLoader loader) {
        final Set nameCache = getClassNameCache(loader);// 20201113 根据类加载器从缓存中获取ClassName
        return namingPolicy.getClassName(namePrefix, source.name, key, new Predicate() {// 调用默认命名策略获取ClassName
            public boolean evaluate(Object arg) {
                return nameCache.contains(arg);//20201113 根据参数判断名称缓存中是否存在该ClassName
            }
        });
    }

    private Set getClassNameCache(ClassLoader loader) {
        return (Set)((Map)source.cache.get(loader)).get(NAME_KEY);
    }

    /**
     * Set the <code>ClassLoader</code> in which the class will be generated.
     * Concrete subclasses of <code>AbstractClassGenerator</code> (such as <code>Enhancer</code>)
     * will try to choose an appropriate default if this is unset.
     * <p>
     * Classes are cached per-<code>ClassLoader</code> using a <code>WeakHashMap</code>, to allow
     * the generated classes to be removed when the associated loader is garbage collected.
     * @param classLoader the loader to generate the new class with, or null to use the default
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Override the default naming policy.
     * @see DefaultNamingPolicy
     * @param namingPolicy the custom policy, or null to use the default
     */
    public void setNamingPolicy(NamingPolicy namingPolicy) {
        if (namingPolicy == null)
            namingPolicy = DefaultNamingPolicy.INSTANCE;
        this.namingPolicy = namingPolicy;
    }

    /**
     * @see #setNamingPolicy
     */
    public NamingPolicy getNamingPolicy() {
        return namingPolicy;
    }

    /**
     * Whether use and update the static cache of generated classes
     * for a class with the same properties. Default is <code>true</code>.
     */
    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    /**
     * @see #setUseCache
     */
    public boolean getUseCache() {
        return useCache;
    }

    /**
     * If set, CGLIB will attempt to load classes from the specified
     * <code>ClassLoader</code> before generating them. Because generated
     * class names are not guaranteed to be unique, the default is <code>false</code>.
     */
    public void setAttemptLoad(boolean attemptLoad) {
        this.attemptLoad = attemptLoad;
    }

    public boolean getAttemptLoad() {
        return attemptLoad;
    }
    
    /**
     * Set the strategy to use to create the bytecode from this generator.
     * By default an instance of {@see DefaultGeneratorStrategy} is used.
     */
    public void setStrategy(GeneratorStrategy strategy) {
        if (strategy == null)
            strategy = DefaultGeneratorStrategy.INSTANCE;
        this.strategy = strategy;
    }

    /**
     * @see #setStrategy
     */
    public GeneratorStrategy getStrategy() {
        return strategy;
    }

    /**
     * Used internally by CGLIB. Returns the <code>AbstractClassGenerator</code>
     * that is being used to generate a class in the current thread.
     */
    public static AbstractClassGenerator getCurrent() {
        return (AbstractClassGenerator)CURRENT.get();
    }

    public ClassLoader getClassLoader() {
        ClassLoader t = classLoader;
        if (t == null) {
            t = getDefaultClassLoader();
        }
        if (t == null) {
            t = getClass().getClassLoader();
        }
        if (t == null) {
            t = Thread.currentThread().getContextClassLoader();
        }
        if (t == null) {
            throw new IllegalStateException("Cannot determine classloader");
        }
        return t;
    }

    abstract protected ClassLoader getDefaultClassLoader();

    // 20201113 缓存的使用: 生成的缓存是按照ClassLoader来划分的, 不同的类被不同的ClassLoader加载也是不同的
    protected Object create(Object key) {
        try {
            // 20201112 代理对象Class, 每个生成类在缓存中都会有一个multi-valus key对象与之对应, 对于简单的类可以用类名作为key, 而动态代理不仅与委托类有关, 还与使用的拦截器、回调函数过滤器有关, 因此使用multi-values来标识这个类
        	Class gen = null;

            synchronized (source) {
                // 20201112 获取类加载器
                ClassLoader loader = getClassLoader();

                // 20201112 根据类加载器获取缓存
                Map cache2 = null;
                cache2 = (Map)source.cache.get(loader);

                // 20201112 缓存为空时, 创建类加载器缓存
                if (cache2 == null) {
                    cache2 = new HashMap();
                    cache2.put(NAME_KEY, new HashSet());// 20201113 每个ClassLoader的缓存中都会有一个NAME_KEY, 用来对Class Name进行去重 -> 生成类命名策略
                    source.cache.put(loader, cache2);
                } else if (useCache) {
                    // 20201112 如果开启了从缓存中获取, 则根据特殊键从缓存获取实例
                    Reference ref = (Reference)cache2.get(key);
                    gen = (Class) (( ref == null ) ? null : ref.get()); 
                }

                // 20201112 如果从缓存获取不到
                if (gen == null) {
                    // 20201112 则先获取本地线程save
                    Object save = CURRENT.get();

                    // 20201112 本地线程先保存当前生成器对象
                    CURRENT.set(this);
                    try {
                        // 20201112 设置特殊键为成员变量
                        this.key = key;

                        // 20201112 如果开启尝试类加载
                        if (attemptLoad) {
                            try {
                                // 20201112 从加载器中加载
                                gen = loader.loadClass(getClassName());
                            } catch (ClassNotFoundException e) {
                                // ignore
                            }
                        }

                        // 20201112 如果尝试类加载失败
                        if (gen == null) {
                            // 20201112 则根据默认策略生成代理对象的Class文件字节流
                            byte[] b = strategy.generate(this);

                            // 20201112 根据字节流获取Class文件名
                            String className = ClassNameReader.getClassName(new ClassReader(b));

                            // 20201112 添加Class文件名到类加载器中
                            getClassNameCache(loader).add(className);

                            // 20201112 根据Class文件名 & Class文件字节流 & 类加载器生成代理对象的Class
                            gen = ReflectUtils.defineClass(className, b, loader);
                        }

                        // 20201112 添加代理类Class对象到类加载器缓存中
                        if (useCache) {
                            cache2.put(key, new WeakReference(gen));
                        }
                        return firstInstance(gen);
                    } finally {
                        // 20201112 当前线程保存生成器实例
                        CURRENT.set(save);
                    }
                }
            }
            // 20201112 调用Enhancer的firstInstance()结合参数类型返回一个代理类实例
            return firstInstance(gen);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw new CodeGenerationException(e);
        }
    }

    abstract protected Object firstInstance(Class type) throws Exception;
    abstract protected Object nextInstance(Object instance) throws Exception;
}
