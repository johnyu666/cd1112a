package cn.johnyu.order.service.feign;

import cn.johnyu.commons.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-service")
public interface ProductService {
    @GetMapping(value = "/products/{pid}")
    ProductDto loadProduct(@PathVariable("pid") int pid);

    @PostMapping(value = "/reduceStock")
    int reduceStock(@RequestParam("pid") int pid, @RequestParam("stock") int stock);
}
