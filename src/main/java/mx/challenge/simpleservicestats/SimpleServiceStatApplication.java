package mx.challenge.simpleservicestats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SimpleServiceStatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleServiceStatApplication.class, args);
	}

}
