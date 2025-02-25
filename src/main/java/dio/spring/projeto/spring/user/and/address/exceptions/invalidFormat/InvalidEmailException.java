package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.invalidFormat;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(){
        super("Formato de email invalido!");
    }
}
