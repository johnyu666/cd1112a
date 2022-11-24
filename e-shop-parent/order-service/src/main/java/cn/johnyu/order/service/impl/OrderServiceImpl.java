package cn.johnyu.order.service.impl;

import cn.johnyu.commons.dto.AccountDto;
import cn.johnyu.commons.dto.ProductDto;
import cn.johnyu.order.entity.Order;
import cn.johnyu.order.mapper.OrderDao;
import cn.johnyu.order.po.OrderPo;
import cn.johnyu.order.service.OrderService;
import cn.johnyu.order.service.feign.AccountService;
import cn.johnyu.order.service.feign.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;
    @Override
    public Order loadOrder(int oid) {
        OrderPo orderPo=orderDao.loadOrder(oid);
        ProductDto productDto = productService.loadProduct(orderPo.getPid());
        AccountDto accountDto=accountService.loadAccount(orderPo.getAid());
        Order order=new Order();
        order.setId(oid).setAmount(orderPo.getAmount())
                .setProduct(productDto)
                .setAccount(accountDto);
        return order;
    }

    @GlobalTransactional
    @Override
    public int createOrder(int aid, int pid, int count) {
        //todo: 报价微服务：获取价格
        int price=100;
        int amount=count*price;
        productService.reduceStock(pid,count);
        accountService.reduceBalance(aid,amount);
        return orderDao.createOrder(aid,pid,amount);
    }

    @Override
    public List<OrderPo> findAllOrders() {
        return orderDao.findAllOrders();
    }
}
