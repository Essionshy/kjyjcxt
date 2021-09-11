package com.tingyu.antimicrobial.monitor;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableEurekaClient
@EnableApolloConfig
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AntimicrobialMonitoringSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntimicrobialMonitoringSystemApplication.class, args);
    }

}
