package dio.spring.projeto.spring.user.and.address.exceptions.notfound;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found!");
    }
}
