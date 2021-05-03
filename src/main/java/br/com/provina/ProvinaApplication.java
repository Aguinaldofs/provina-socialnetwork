package br.com.provina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ProvinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvinaApplication.class, args);
	}

}
