package sop.ce.gov.controlefinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ControleFinanceiroApplication {

	public static void main(String[] args) {

		SpringApplication.run(ControleFinanceiroApplication.class, args);
	}

}
