package dio.spring.projeto.spring.user.and.address.exceptions.exist;

public class UserExistException extends RuntimeException {
    public UserExistException(){
        super("Usuário já existe!");
    }
}
