package cn.johnyu.product.mapper;

import cn.johnyu.commons.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductDao {

    @Update("update products set stock=stock -  #{stock} where id=#{pid}")
    int reduceStock(@Param("pid") int pid, @Param("stock") int stock);
    @Select("select * from products where id = #{pid}")
    ProductDto loadProduct(@Param("pid") int pid);
}
