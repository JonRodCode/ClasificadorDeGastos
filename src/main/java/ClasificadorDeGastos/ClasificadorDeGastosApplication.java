package ClasificadorDeGastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"controller", "service"})
@SpringBootApplication
public class ClasificadorDeGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClasificadorDeGastosApplication.class, args);
	}

}
