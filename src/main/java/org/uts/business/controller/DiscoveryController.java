package org.uts.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 注册发现 接口类
 * @Author codBoy
 * @Date 2024/7/10 21:01
 */
@RestController
public class DiscoveryController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/instance")
    public Map<String, List<ServiceInstance>> getInstance() {
        Map<String,List<ServiceInstance>> instances = new HashMap<>();

        List<String> services = discoveryClient.getServices();
        if(!CollectionUtils.isEmpty(services)){
            for(String service : services){
                List<ServiceInstance> instanceList = discoveryClient.getInstances(service);
                if(!CollectionUtils.isEmpty(instanceList)){
                    instances.put(service,instanceList);
                }
            }
        }
        return instances;
    }
}
