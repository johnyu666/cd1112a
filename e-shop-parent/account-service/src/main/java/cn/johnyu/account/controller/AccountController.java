package cn.johnyu.account.controller;

import cn.johnyu.account.mapper.AccountMapper;
import cn.johnyu.commons.dto.AccountDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@RestController
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/accounts/{aid}")
    public AccountDto loadAccount(@PathVariable("aid") int aid){
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();

        AccountDto accountDto = accountMapper.loadAccount(aid);
        accountDto.setExtra(bean.getName()+"...."+Thread.currentThread().getName());
        return accountDto;
    }

    @Transactional
    @PostMapping(value = "/reduceBalance")
    public int reduceBalance(int aid,int balance){
        AccountDto account = accountMapper.loadAccount(aid);
        if(account.getBalance()<balance){
            throw new RuntimeException("用户帐户的余额不足");
        }

        return accountMapper.reduceBalance(aid,balance);
    }
}
