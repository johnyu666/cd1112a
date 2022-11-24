package cn.johnyu.order.controller;

import cn.johnyu.commons.dto.CommonResult;
import cn.johnyu.order.entity.Order;
import cn.johnyu.order.po.OrderPo;
import cn.johnyu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/orders/{oid}")
    public CommonResult<Order> loadOrder(@PathVariable("oid") int oid){
        Order order = orderService.loadOrder(oid);
        return new CommonResult<Order>()
                .setCode(200)
                .setInfo("success")
                .setContent(order);
    }

    @PostMapping(value = "/createOrder")
    public String createOrder(int aid,int pid,int count){
        int rs = orderService.createOrder(aid, pid, count);
        if(rs>0) return aid+" 用户购买了 "+pid+ " 商品";
        else  return "错误！";
    }

    @GetMapping("/orders")
    public CommonResult<List<OrderPo>> findAllOrders(){
        List<OrderPo> allOrders = orderService.findAllOrders();
        return new CommonResult<List<OrderPo>>().setCode(200).setContent(allOrders);
    }
}
