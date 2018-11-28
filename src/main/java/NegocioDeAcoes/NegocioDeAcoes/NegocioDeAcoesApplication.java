package NegocioDeAcoes.NegocioDeAcoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NegocioDeAcoesApplication {

	public static void main(String[] args) {

		SpringApplication.run(NegocioDeAcoesApplication.class, args);

	}

}
