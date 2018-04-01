package cz.ivosahlik.zomatoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "cz.ivosahlik.zomatoapi")
@PropertySource(value = {"classpath:/config/zomato-config.properties"}, ignoreResourceNotFound = true)
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class ZomatoApiApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ZomatoApiApplication.class, args);
	}
}
