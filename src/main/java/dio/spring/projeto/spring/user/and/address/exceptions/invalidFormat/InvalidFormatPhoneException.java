package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.invalidFormat;

public class InvalidFormatPhoneException extends RuntimeException{
    public InvalidFormatPhoneException(){
        super("Telefone invalido");
    }
}
