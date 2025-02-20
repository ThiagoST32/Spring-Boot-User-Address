package dio.spring.projeto.spring.user.and.address.service.cepService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface CepService {

    @GetMapping("{cep}/json")
    String consultCep(@PathVariable("cep") String cep);

}
