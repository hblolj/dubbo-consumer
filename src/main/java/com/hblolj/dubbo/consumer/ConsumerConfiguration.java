package com.hblolj.dubbo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: hblolj
 * @Date: 2019/6/28 16:35
 * @Description:
 * @Version:
 **/
@Configuration
@EnableDubbo(scanBasePackages = "com.hblolj.dubbo.consumer.action")
@PropertySource("classpath:/consumer.properties")
@ComponentScan(value = "com.hblolj.dubbo.consumer")
public class ConsumerConfiguration {
}
