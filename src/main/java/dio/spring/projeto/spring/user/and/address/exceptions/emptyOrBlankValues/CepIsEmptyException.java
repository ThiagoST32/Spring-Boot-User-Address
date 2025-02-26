package dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues;

public class CepIsEmptyException extends RuntimeException{
    public CepIsEmptyException(){
        super("Cep está vazio!");
    }
}
