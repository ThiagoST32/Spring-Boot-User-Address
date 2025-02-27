package dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues;

public class EmailIsEmptyException extends RuntimeException{
    public EmailIsEmptyException(){
        super("Email não pode estar vazio!");
    }
}
