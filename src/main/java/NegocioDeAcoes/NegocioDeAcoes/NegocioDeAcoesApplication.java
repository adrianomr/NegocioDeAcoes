package NegocioDeAcoes.NegocioDeAcoes;

import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NegocioDeAcoesApplication {

	public static void main(String[] args) {

		SpringApplication.run(NegocioDeAcoesApplication.class, args);

	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ResponseEntity<Monitoramento[]> responseEntity = restTemplate.getForEntity(
					"http://localhost:8080/contas/1101/monitoramentos", Monitoramento[].class);
			List<Monitoramento> monitoramentos = Arrays.asList(responseEntity.getBody());
			for(Monitoramento monitoramento : monitoramentos){
				System.out.println(monitoramento.toString());
			}
//			System.out.println(monitoramentos);
		};
	}
}
