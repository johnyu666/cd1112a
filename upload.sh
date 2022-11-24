#!/bin/bash
mvn clean package
scp e-shop-parent/product-service/target/product-service-1.0-SNAPSHOT.jar root@johnyu.cn:~/eshopx