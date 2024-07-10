# uts-discover

## 1  项目简介

本项目为注册发现项目。

## 2 实现细节

### 2.1  SpringBoot集成Nacos

（1）下载并启动nacos

![image-20240710230708112](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20240710230708112.png)

（1）pom文件中引入nacos依赖

```xml
<!-- Spring Cloud Alibaba Nacos Discovery -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
<!-- Spring Cloud Alibaba Nacos Configuration -->
<dependency>
   <groupId>com.alibaba.cloud</groupId>
   <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
   <version>2.2.5.RELEASE</version>
</dependency>
```

  （2）application.properties中添加nacos配置

- 配置包括注册管理相关配置和配置管理相关配置；
- 提前在nacos的web页面创建了一个namespace，这里的namespace配置值为页面对应的ID；
- group是自己定义的；

```properties
# nacos discover(注册中心配置)
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
spring.cloud.nacos.discovery.namespace=3064957d-b949-4a3b-a014-46b850c3bf0c
spring.cloud.nacos.discovery.group=uts-discover
# nacos config(配置中心配置)
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
spring.cloud.nacos.config.namespace=3064957d-b949-4a3b-a014-46b850c3bf0c
spring.cloud.nacos.config.group=uts-discover
spring.cloud.nacos.config.file-extension=yaml
```

（3）启动SpringBoot项目并访问http://localhost:8848/nacos/index.html，看到服务已经注册到nacos

![image-20240710230619840](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20240710230619840.png)

### 2.2  uts-discover服务主要功能

（1）提供查询接口，用于查询当前注册到uts-discover注册发现的服务实例。接口详情如下：

```json
请求URL：http://ip:8002/uts-discover/instance
请求方式：GET
请求参数：无
返回示例：
{
    "uts-discover": [
        {
            "serviceId": "uts-discover",
            "host": "127.0.0.1",
            "port": 8002,
            "secure": false,
            "metadata": {
                "nacos.instanceId": "127.0.0.1.5#8002#DEFAULT#uts-discover@@uts-discover",
                "nacos.weight": "1.0",
                "nacos.cluster": "DEFAULT",
                "nacos.ephemeral": "true",
                "nacos.healthy": "true",
                "preserved.register.source": "SPRING_CLOUD"
            },
            "uri": "http://127.0.0.1:8002",
            "scheme": null,
            "instanceId": null
        }
    ]
}
```

## 3 nacos相关知识

### 3.1 nacos功能

- 一个更易于构建云原生应用的 **动态服务发现**、**服务配置**、**服务管理** 平台；
- 注册中心（如：服务地址注册进去根据名称调用）、配置中心（如：每个服务yaml中的配置）、服务管理（可视化管理平台）；

### 3.2 关于namespace和group

作用：用于进行租户粒度的配置隔离，namespace可理解为不同环境的配置的区分隔；

如何设置：以namespace来区分不同环境，如dev测试环境、prod生产环境。以group来区分不同项目或者说项目组。