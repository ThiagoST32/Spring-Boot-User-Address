package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.exist;

public class UserExistException extends RuntimeException {
    public UserExistException(){
        super("Usuário já existe!");
    }
}
