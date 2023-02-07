package com.sazkuyo.ultrawallpaper2.Service;


import com.sazkuyo.ultrawallpaper2.Entity.Account;
import com.sazkuyo.ultrawallpaper2.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;

    //查询所有用户
    public List<Account> queryAll() {
        return accountMapper.queryAll();
    }

    //通过id查找用户
    public Account queryAccountByUid(int uid) {
        return accountMapper.queryAccountByUid(uid);
    }

    //用户注册
    public int register(Account account) {
        return accountMapper.register(account);
    }

    //更新用户信息
    public int update(Account account) {
        return accountMapper.update(account);
    }

    //用户通过账号和密码登录
    public Account loginByUidAndPwd(Account account) {
        return accountMapper.loginByUidAndPwd(account);
    }

    //用户通过邮箱和密码登录
    public Account loginByEmailAndPwd(Account account) {
        return accountMapper.loginByEmailAndPwd(account);
    }

    public Account queryAccountByEmail(Account account) {
        return accountMapper.queryAccountByEmail(account);
    }

    //查找用户总数
    public int queryAccountCount() {
        return accountMapper.queryAccountCount();
    }
}
