package cn.johnyu.product.controller;

import cn.johnyu.commons.dto.ProductDto;
import cn.johnyu.product.mapper.ProductDao;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@RestController
public class ProductController {
    @Autowired private ProductDao productDao;

    @Transactional
    @PostMapping(value = "/reduceStock")
    public int reduceStock(int pid,int stock){
        ProductDto product = productDao.loadProduct(pid);
        if(product.getStock()<stock){
            throw new RuntimeException("库存不足");
        }
        return productDao.reduceStock(pid,stock);
    }

    @SentinelResource(value = "loadProductById",blockHandler = "handleBlock")
    @GetMapping(value = "/products/{pid}")
    public ProductDto loadProduct(@PathVariable("pid") int pid){
        if(pid==0){
            throw new RuntimeException("自定义异常");
        }
        if(pid==1){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        ProductDto productDto = productDao.loadProduct(pid);
        productDto.setExtra(bean.getName()+"-----"+Thread.currentThread().getName());
        return productDto;
    }

    public ProductDto  handleBlock(int pid , BlockException e){
        return new ProductDto().setExtra("商品查询被流控！");
    }

}
