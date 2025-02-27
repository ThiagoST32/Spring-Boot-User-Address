package dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat;

public class InvalidNumberCepException extends RuntimeException{
    public InvalidNumberCepException(){
        super("Invalid cep, enter a valid cep code!");
    }
}
