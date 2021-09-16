package com.tingyu.antimicrobial.monitor.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author essionshy
 * @Create 2021/5/25 22:12
 * @Version kjyjcxt
 */
//@RefreshScope
@MapperScan(basePackages = {"com.tingyu.antimicrobial.monitor.dao"})
@ComponentScan(basePackages = {"com.tingyu.antimicrobial.monitor.config"})
@Configuration
public class AppConfig {
}
