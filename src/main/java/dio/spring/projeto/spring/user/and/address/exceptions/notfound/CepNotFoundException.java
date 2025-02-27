package dio.spring.projeto.spring.user.and.address.exceptions.notfound;

public class CepNotFoundException extends RuntimeException{
    public CepNotFoundException(){
        super("Cep not found!");
    }
}
