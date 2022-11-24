package cn.johnyu.order.service.feign;

import cn.johnyu.commons.dto.AccountDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account-service")
public interface AccountService {
    @GetMapping("/accounts/{aid}")
    AccountDto loadAccount(@PathVariable("aid") int aid);
    @PostMapping(value = "/reduceBalance")
    int reduceBalance(@RequestParam("aid") int aid, @RequestParam("balance") int balance);
}
