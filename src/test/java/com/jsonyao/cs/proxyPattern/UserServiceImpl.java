package com.jsonyao.cs.proxyPattern;

/**
 * 用户服务接口实现类
 */
public class UserServiceImpl implements UserService {

    @Override
    public void save() {
        System.out.println("保存用户信息...");
    }

}
