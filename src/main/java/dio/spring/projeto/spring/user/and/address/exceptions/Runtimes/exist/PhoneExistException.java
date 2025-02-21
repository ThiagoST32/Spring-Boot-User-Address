package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.exist;

public class PhoneExistException extends RuntimeException {
    public PhoneExistException(){
        super("Este telefone já existe!");
    }
}
