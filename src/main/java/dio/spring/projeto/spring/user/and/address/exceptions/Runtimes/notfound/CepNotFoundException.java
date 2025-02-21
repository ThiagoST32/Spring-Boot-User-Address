package dio.spring.projeto.spring.user.and.address.exceptions.Runtimes.notfound;

public class CepNotFoundException extends RuntimeException{
    public CepNotFoundException(){
        super("Cep não encontrado!");
    }
}
