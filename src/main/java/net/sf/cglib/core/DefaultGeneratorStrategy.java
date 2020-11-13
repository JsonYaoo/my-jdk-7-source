/*
 * Copyright 2003 The Apache Software Foundation
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

// 20201113 DefaultGeneratorStrategy是CGLIB提供的一个默认的生成策略
public class DefaultGeneratorStrategy implements GeneratorStrategy {
    public static final DefaultGeneratorStrategy INSTANCE = new DefaultGeneratorStrategy();// 20201113 单例

    // 20201113 生成代理类的字节码, 传入具体的ClassGenerator引用, 即Enhancer实例
    public byte[] generate(ClassGenerator cg) throws Exception {
        ClassWriter cw = getClassWriter();
        transform(cg).generateClass(cw);// 20201113 通过调用具体ClassGenerator实现的generateClass来生成代理类的字节码
        return transform(cw.toByteArray());
    }

    protected ClassWriter getClassWriter() throws Exception {
      return new DebuggingClassWriter(ClassWriter.COMPUTE_MAXS);
    }

    protected byte[] transform(byte[] b) throws Exception {
        return b;
    }

    protected ClassGenerator transform(ClassGenerator cg) throws Exception {
        return cg;
    }
}
