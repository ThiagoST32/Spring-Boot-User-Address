package dio.spring.projeto.spring.user.and.address.service.cepService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidAddressCepException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidNumberCepException;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.CepNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CepService {
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private CepValidator cepValidator;

    public CepService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Address buscarEnderecoPorCep(String cep) throws JsonProcessingException {

        this.cepValidator.cepIsValid(cep);

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity(url, String.class);

        if (this.cepValidator.checagemEnderecoCep(jsonResponse)) throw new InvalidAddressCepException();

        if (jsonResponse.getStatusCode() == HttpStatus.OK && !jsonResponse.getBody().contains("\"erro\": true")) {
            return objectMapper.readValue(jsonResponse.getBody(), Address.class);
        } else {
            throw new CepNotFoundException();
        }
    }
}
