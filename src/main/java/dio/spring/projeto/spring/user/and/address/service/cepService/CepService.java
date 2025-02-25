package dio.spring.projeto.spring.user.and.address.service.cepService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidNumberCepException;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.CepNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
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

    public Address buscarEnderecoPorCep(String cep){
        if (!checagemDeNumerosDoCep(cep)){
            throw new InvalidNumberCepException();
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity(url, String.class);

        try {
            if (jsonResponse.getStatusCode() == HttpStatus.OK && !jsonResponse.getBody().contains("\"erro\": true")) {
                return objectMapper.readValue(jsonResponse.getBody(), Address.class);
            } else {
                throw new CepNotFoundException();
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new CepNotFoundException(); // Erro 4xx ou 5xx da API
        } catch (CepNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RestClientException ex){
            throw new CepNotFoundException();
        } catch (JsonProcessingException ex){
            throw new RuntimeException("Erro ao processar resposta ao ViaCep", ex);
        }
    }

    public boolean checagemDeNumerosDoCep(String cep) throws InvalidNumberCepException {
        return cep != null && cep.matches("^\\d{8}$");
    }
}
