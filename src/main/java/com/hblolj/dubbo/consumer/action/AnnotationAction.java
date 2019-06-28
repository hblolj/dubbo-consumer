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

    @Reference
    private DemoService demoService;

    public String doSayHello(String name){
        return demoService.sayHello(name);
//        return "default values";
    }
}
