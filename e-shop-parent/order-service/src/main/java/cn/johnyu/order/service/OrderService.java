package cn.johnyu.order.service;

import cn.johnyu.order.entity.Order;
import cn.johnyu.order.po.OrderPo;

import java.util.List;

public interface OrderService {
    Order loadOrder(int oid);
    int createOrder(int aid,int pid,int count);

    List<OrderPo> findAllOrders();
}
