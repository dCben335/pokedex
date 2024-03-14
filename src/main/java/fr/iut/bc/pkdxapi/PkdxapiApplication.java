package fr.iut.bc.pkdxapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.iut.bc.pkdxapi")
public class PkdxapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PkdxapiApplication.class, args);
	}

}
