package NegocioDeAcoes.NegocioDeAcoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@Configuration
@EnableAsync
public class NegocioDeAcoesApplication {

	private static final Logger logger = LoggerFactory.getLogger(NegocioDeAcoesApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(NegocioDeAcoesApplication.class, args);

	}

}
