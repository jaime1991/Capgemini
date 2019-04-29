package com.capgemini.mibank.transactional.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jaime Cadena
 */
@EnableSwagger2
@SpringBootApplication
public class TransactionalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionalApiApplication.class, args);
	}

}
