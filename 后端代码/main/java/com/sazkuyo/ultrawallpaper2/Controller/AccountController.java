package com.sazkuyo.ultrawallpaper2.Controller;

import com.sazkuyo.ultrawallpaper2.Entity.Account;
import com.sazkuyo.ultrawallpaper2.Service.AccountService;
import com.sazkuyo.ultrawallpaper2.Util.getCheckCodeByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("accountController")
public class AccountController {
    @Autowired
    AccountService accountService;
    Map<String, String> maps = new HashMap<>();
    Queue<String> li = new LinkedList<>();

    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAll")
    public Object queryAll(HttpServletRequest req, HttpServletResponse res) {
        System.out.println(req.getRemoteAddr());
        return accountService.queryAll();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAccountByUid")
    public Object queryAccountByUid(@RequestBody int uid) {
        return accountService.queryAccountByUid(uid);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Object register(@RequestBody Account account) {
        if (accountService.queryAccountByEmail(account) != null) {
            return "该邮箱已被注册";
        }
        return accountService.register(account) > 0 ? "注册成功" : "注册失败";
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("getCheckCode")
    public String getCheckCode(@RequestParam("email") String email) {
        try {
            String checkCode = getCheckCodeByEmail.sendCheckCode(email);
            li.offer(email);
            maps.put(email, checkCode);
            if (li.size() > 300) {
                for (int i = 0; i < 100; i++) {
                    maps.remove(li.poll());
                }
            }
            return "验证码发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "验证码发送失败";
        }

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("CheckCode")
    public String CheckCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        if (maps.get(email) == null || email == "") {
            return "请重新获取验证码";
        }
        return maps.get(email).equals(code) ? "验证成功" : "验证失败";
    }

    //    @CrossOrigin
//    @ResponseBody
//    @RequestMapping(value = "loginByUidAndPwd",method = RequestMethod.POST)
//    public Object queryAccountByUid(@RequestBody Account account){
//        return accountService.loginByUidAndPwd(account);
//    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "loginByEmailAndPwd", method = RequestMethod.POST)
    public Object loginByEmailAndPwd(@RequestBody Account account) {
        return accountService.loginByEmailAndPwd(account);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "loginByUidAndPwd", method = RequestMethod.POST)
    public Object loginByUidAndPwd(@RequestBody Account account) {
        return accountService.loginByUidAndPwd(account);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "queryAccountCount")
    public int queryAccountCount() {
        return accountService.queryAccountCount();
    }
}
