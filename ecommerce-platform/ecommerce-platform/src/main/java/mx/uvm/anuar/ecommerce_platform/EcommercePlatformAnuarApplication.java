package mx.uvm.anuar.ecommerce_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EcommercePlatformAnuarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePlatformAnuarApplication.class, args);
	}

}
