package dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat;

public class InvalidAddressCepException extends RuntimeException{
    public InvalidAddressCepException(){
        super("Endereço Invalido!");
    }
}
