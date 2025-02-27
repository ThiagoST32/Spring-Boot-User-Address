package dio.spring.projeto.spring.user.and.address.exceptions.exist;

import javax.naming.Name;

public class NameExistException extends RuntimeException{
    public NameExistException(){
        super("This name already exists!");
    }
}
