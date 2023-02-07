package com.sazkuyo.ultrawallpaper2.Mapper;

import com.sazkuyo.ultrawallpaper2.Entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    List<Account> queryAll();//查询所有用户

    Account queryAccountByUid(int uid);//通过id查找用户

    int register(Account account);//用户注册

    int update(Account account);//更新用户信息

    Account loginByUidAndPwd(Account account);//用户通过账号和密码登录

    Account loginByEmailAndPwd(Account account);//用户通过邮箱和密码登录

    Account queryAccountByEmail(Account account);//通过email查找

    int queryAccountCount();//查询用户总数
}
