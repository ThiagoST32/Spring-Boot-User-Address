package dio.spring.projeto.spring.user.and.address;

import dio.spring.projeto.spring.user.and.address.service.AddressService;
import dio.spring.projeto.spring.user.and.address.service.cepService.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


	}
	@Autowired
	private CepService cepService;

	@Autowired
	private AddressService addressService;

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Starting project");
		String answer =this.cepService.consultCep("79092330");
		System.err.println(answer);

	}
}
