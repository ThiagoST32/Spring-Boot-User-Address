package dio.spring.projeto.spring.user.and.address.exceptions.exist;

public class EmailExistException extends RuntimeException{
    public EmailExistException(){
        super("This email already exists!");
    }
}
