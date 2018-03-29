package com.qlone.deureka;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qlone.deureka.**.dao")
public class DeurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeurekaApplication.class, args);
	}

}
