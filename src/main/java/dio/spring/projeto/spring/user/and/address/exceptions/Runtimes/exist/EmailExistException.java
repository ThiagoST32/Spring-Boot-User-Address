package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.exist;

public class EmailExistException extends RuntimeException{
    public EmailExistException(){
        super("Este email já existe!");
    }
}
