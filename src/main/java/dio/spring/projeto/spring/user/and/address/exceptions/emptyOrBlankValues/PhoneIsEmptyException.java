package dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues;

public class PhoneIsEmptyException extends RuntimeException{
    public PhoneIsEmptyException(){
        super("O telfone não pode estar vazio!!");
    }
}
