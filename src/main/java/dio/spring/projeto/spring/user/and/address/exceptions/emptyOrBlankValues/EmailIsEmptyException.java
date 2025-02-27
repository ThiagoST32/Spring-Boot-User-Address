package dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues;

public class EmailIsEmptyException extends RuntimeException{
    public EmailIsEmptyException(){
        super("The email cannot be empty!!");
    }
}
