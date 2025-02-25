package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.notfound;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Usuario não encontrado!");
    }
}
