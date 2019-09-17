package net.xdclass.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**

配置中心服务器端
	1.配置中心服务端依赖spring-cloud-config-server，配置中心服务高可用导入eureka客户端spring-cloud-starter-netflix-eureka-client

	2.启动项@SpringBootApplication和@EnableConfigServer注解

	3.配置application.yml以远程仓库github作为作为配置中心

配置中心访问方式，多种访问路径，可以通过启动日志去查看
	例子 http://localhost:9100/product-service.yml

	/{name}-{profiles}.properties
	/{name}-{profiles}.yml
	/{name}-{profiles}.json
	/{label}/{name}-{profiles}.yml

	name 服务器名称
	profile 环境名称，开发、测试、生产
	lable 仓库分支、默认master分支




配置中心客户端(以product-service为例)
	1.配置中心客户端依赖spring-cloud-config-client等其他核心依赖，比如web

	2.修改application.yml 改为 bootstrap.yml

	3.bootstrap.yml中需要找到配置中心配置文件
		#服务的名称
		spring:
		  application:
			name: product-service
		  #指定从哪个配置中心读取
		  cloud:
			config:
			  discovery:
				service-id: config-service
				enabled: true
			  profile: dev
			  #建议用lable去区分环境，默认是lable是master分支
			  #label: dev

 	配置中心地址信息/${label}/${name}-${profile}.yml
 	http://localhost:9100/dev/product-service-dev.yml

 */
@SpringBootApplication
@EnableConfigServer//作为SpringCloudConfig的配置服务器
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
