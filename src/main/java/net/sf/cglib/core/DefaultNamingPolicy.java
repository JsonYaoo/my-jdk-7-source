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

import java.util.Set;

/**
 * The default policy used by {@link AbstractClassGenerator}.
 * Generates names such as
 * <p><code>net.sf.cglib.Foo$$EnhancerByCGLIB$$38272841</code><p>
 * This is composed of a prefix based on the name of the superclass, a fixed
 * string incorporating the CGLIB class responsible for generation, and a
 * hashcode derived from the parameters used to create the object. If the same
 * name has been previously been used in the same <code>ClassLoader</code>, a
 * suffix is added to ensure uniqueness.
 */
public class DefaultNamingPolicy implements NamingPolicy {
    public static final DefaultNamingPolicy INSTANCE = new DefaultNamingPolicy();

    // 20201113 根据参数组装ClassName -> 默认名称格式: 被代理class name + "$$" + class generator name + "ByCGLIB" + "$$" + key的hashcode
    public String getClassName(String prefix, String source, Object key, Predicate names) {
        if (prefix == null) {
            prefix = "net.sf.cglib.empty.Object";
        } else if (prefix.startsWith("java")) {
            prefix = "$" + prefix;// 20201113 设置$前缀
        }

        String base =
            prefix + "$$" + // 设置被代理ClassName$$
            source.substring(source.lastIndexOf('.') + 1) +// 20201113 设置Class Generator的name, CGLIB动态代理时, name => net.sf.cglib.proxy.Enhancer
            getTag() + "$$" +// 设置ByCGLIB&&
            Integer.toHexString(key.hashCode());// 20201113 设置multi-values生成的key的hashCode作为版本号
        String attempt = base;
        int index = 2;
        while (names.evaluate(attempt))
            attempt = base + "_" + index++;// 20201113 文件重名, 则文件名自动添加上"_2"
        return attempt;
    }

    /**
     * Returns a string which is incorporated into every generated class name.
     * By default returns "ByCGLIB"
     */
    protected String getTag() {
        return "ByCGLIB";
    }

  public int hashCode() {
    return getTag().hashCode();
  }

  public boolean equals(Object o) {
    return (o instanceof DefaultNamingPolicy) && ((DefaultNamingPolicy) o).getTag().equals(getTag());
  }
}
