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

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;

// 20201113 继承ClassWriter, 实现于ClassVisitor, 其中ClassWriter是ASM中访问链上最后一名访问者, 主要作用是产生Class字节码,
// 20201113 其中DebuggingClassWriter最终是委托ClassWriter来生成字节码的, DebuggingClassWriter主要工作是将字节码持久化到硬盘上
public class DebuggingClassWriter extends ClassWriter {
    
    public static final String DEBUG_LOCATION_PROPERTY = "cglib.debugLocation";
    
    private static String debugLocation;
    private static boolean traceEnabled;
    
    private String className;
    private String superName;
    
    static {
        // 20201113 获取字节码是否持久化配置
        debugLocation = System.getProperty(DEBUG_LOCATION_PROPERTY);
        if (debugLocation != null) {
            System.err.println("CGLIB debugging enabled, writing to '" + debugLocation + "'");
            try {
                // TraceClassVisitor是ASM中一个工具类, 作用是将字节码转换成易读的文本, 也就是反编译, 跟javap差不多
                Class.forName("org.objectweb.asm.util.TraceClassVisitor");
                traceEnabled = true;
            } catch (Throwable ignore) {
            }
        }
    }

    // 20201113 有参构造器
    public DebuggingClassWriter(int flags) {
        super(flags);
    }

    public void visit(int version,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces) {
        className = name.replace('/', '.');
        this.superName = superName.replace('/', '.');
        super.visit(version, access, name, signature, superName, interfaces);
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getSuperName() {
        return superName;
    }

    // 20201113 持久化字节码关键方法
    public byte[] toByteArray() {
        
      return (byte[]) java.security.AccessController.doPrivileged(
        new java.security.PrivilegedAction() {
            public Object run() {

                // 20201113 委托父类ClassWriter生成字节码
                byte[] b = DebuggingClassWriter.super.toByteArray();
                if (debugLocation != null) {
                    // 20201113 如果开启持久化, 则先设置类名
                    String dirs = className.replace('.', File.separatorChar);
                    try {
                        // 20201113 创建文件目录
                        new File(debugLocation + File.separatorChar + dirs).getParentFile().mkdirs();

                        // 20201113 创建Class文件
                        File file = new File(new File(debugLocation), dirs + ".class");

                        // 20201113 开启输出流
                        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                        try {
                            // 20201113 输出流写出文件
                            out.write(b);
                        } finally {
                            out.close();
                        }

                        // 20201113 如果开启ASM反编译
                        if (traceEnabled) {
                            // 20201113 创建.asm反编译我呢间
                            file = new File(new File(debugLocation), dirs + ".asm");

                            // 20201113 开启输出流写出文件
                            out = new BufferedOutputStream(new FileOutputStream(file));
                            try {
                                // 20201113 使用TraceClassVisitor输出ASM文件
                                ClassReader cr = new ClassReader(b);
                                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
                                TraceClassVisitor tcv = new TraceClassVisitor(null, pw);
                                cr.accept(tcv, 0);
                                pw.flush();
                            } finally {
                                out.close();
                            }
                        }
                    } catch (IOException e) {
                        throw new CodeGenerationException(e);
                    }
                }
                return b;
             }  
            });
            
        }
    }
