package dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues;

public class NameIsEmptyException extends RuntimeException{
    public NameIsEmptyException(){
        super("O nome não pode estar vazio!!");
    }
}
