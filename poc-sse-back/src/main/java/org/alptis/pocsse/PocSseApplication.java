package org.alptis.pocsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PocSseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocSseApplication.class, args);
	}

}
