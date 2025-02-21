package dio.spring.projeto.spring.user.and.address.service.cepService;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.spring.projeto.spring.user.and.address.domain.Address;
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
        return objectMapper.readValue(jsonResponse, Address.class);
    }
}
