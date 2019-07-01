package com.hblolj.dubbo.consumer.action;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hblolj.dubbo.provider.DemoService;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2019/6/28 16:11
 * @Description:
 * @Version:
 **/
@Component("annotationAction")
public class AnnotationAction {

    // 设置 check 有效
//    @Reference(check = false)
    @Reference(version = "1.0.1", protocol = "dubbo", check = false)
    private DemoService demoService;

    public String doSayHello(String name){

        System.out.println(null == demoService);

//        return demoService.sayHello(name);
        return "default values";
    }
}
