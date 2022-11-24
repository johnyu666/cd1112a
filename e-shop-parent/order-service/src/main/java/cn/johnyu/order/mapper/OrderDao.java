package cn.johnyu.order.mapper;

import cn.johnyu.order.po.OrderPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao {
    @Select("select id,product_id as pid ,user_id as aid,pay_amount as amount from orders where id= #{oid}")
    OrderPo loadOrder(@Param("oid") int oid);
    @Insert("insert into orders (user_id,product_id,pay_amount) values (#{aid},#{pid},#{amount})")
    int createOrder(@Param("aid") int aid,@Param("pid") int pid,@Param("amount")int amount);
    @Select("select id,product_id as pid ,user_id as aid,pay_amount as amount from orders")
    List<OrderPo> findAllOrders();
}
