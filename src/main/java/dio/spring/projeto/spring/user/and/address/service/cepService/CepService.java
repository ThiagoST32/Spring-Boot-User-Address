package dio.spring.projeto.spring.user.and.address.service.cepService;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.notfound.CepNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final ObjectMapper objectMapper;

    public CepService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Address buscarEnderecoPorCep(String cep) throws JsonProcessingException {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        if (jsonResponse.contains("400 Bad Request on GET request")) {
            throw new CepNotFoundException();
        }
        return objectMapper.readValue(jsonResponse, Address.class);
    }
}
