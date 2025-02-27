package dio.spring.projeto.spring.user.and.address.service.cepService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.CepIsEmptyException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidNumberCepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CepValidator {

    @Autowired
    private final ObjectMapper objectMapper;

    public CepValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean checagemEnderecoCep(ResponseEntity<String> jsonResponse) throws JsonProcessingException {
        var cepResponse = objectMapper.readValue(jsonResponse.getBody(), Address.class);
        return cepResponse.getLogradouro() == null;
    }

    public void cepIsValid(String cep){
        if (this.cepIsEmpty(cep)) throw new CepIsEmptyException();
        if (this.cepIsBlank(cep)) throw new CepIsEmptyException();
        if (!this.checagemDeNumerosDoCep(cep)) throw new InvalidNumberCepException();
    }

    public boolean checagemDeNumerosDoCep(String cep){
        return cep.matches("^\\d{8}$");
    }

    public boolean cepIsEmpty(String cep){
        return cep.isEmpty();
    }

    public boolean cepIsBlank(String cep){
        return cep.isBlank();
    }
}
