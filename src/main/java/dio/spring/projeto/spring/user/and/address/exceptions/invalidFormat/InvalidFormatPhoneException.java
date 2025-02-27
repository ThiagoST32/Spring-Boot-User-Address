package dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat;

public class InvalidFormatPhoneException extends RuntimeException{
    public InvalidFormatPhoneException(){
        super("Invalid phone number!");
    }
}
