#!/bin/bash
if [ $2 ] && [ $2 = 1 ]; then
    mvn clean package
fi

scp e-shop-parent/$1-service/target/$1-service-1.0-SNAPSHOT.jar root@johnyu.cn:~/eshopx