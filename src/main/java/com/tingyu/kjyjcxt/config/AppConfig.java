package com.tingyu.kjyjcxt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author essionshy
 * @Create 2021/5/25 22:12
 * @Version kjyjcxt
 */
@MapperScan(basePackages = {"com.tingyu.kjyjcxt.dao"})
@ComponentScan(basePackages = {"com.tingyu.kjyjcxt.config"})
@Configuration
public class AppConfig {
}
