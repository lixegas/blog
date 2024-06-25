package com.lixega.blog;

import com.lixega.blog.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RSAKeyProperties.class)
@SpringBootApplication
public class BlogApplication {
	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);

	}
}