package dio.spring.projeto.spring.user.and.address.service;

import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.EmailIsEmptyException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.EmailExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.NameExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.PhoneExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidEmailException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidFormatPhoneException;
import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.NameIsEmptyException;
import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.PhoneIsEmptyException;
import dio.spring.projeto.spring.user.and.address.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;


    private boolean emailInvalido(UserDTO userDTO){
        String validador = "^(.+)@(\\S+)$";
        return Pattern.compile(validador).matcher(userDTO.email()).matches();
    }

    private boolean telefoneInvalido(UserDTO userDTO){
        String validador = "^(?=[8-9])(?=[0-9]{8}).*";
        return Pattern.compile(validador).matcher(userDTO.telefone()).matches();
    }

    private boolean phoneExist(UserDTO userDTO){
        return this.userRepository.existByTelefone(userDTO.telefone());
    }

    private boolean nameExist(UserDTO userDTO) {
        return this.userRepository.existByNome(userDTO.nome());
    }

    private boolean emailExistente(UserDTO userDTO){
        return this.userRepository.existByEmail(userDTO.email());
    }

    private boolean isEmptyName(UserDTO userDTO) { return userDTO.nome().isEmpty();}

    private boolean isEmptyEmail(UserDTO userDTO) { return userDTO.email().isEmpty();}

    private boolean isEmptyPhone(UserDTO userDTO) { return userDTO.telefone().isEmpty();}

    private boolean isNameBlank(UserDTO userDTO) { return  userDTO.nome().isBlank();}

    private boolean isEmailBlank(UserDTO userDTO) { return  userDTO.email().isBlank();}

    private boolean isPhoneBlank(UserDTO userDTO) { return userDTO.telefone().isBlank();}

    private boolean numberPhoneMoreThan11(UserDTO userDTO) { return userDTO.telefone().length() > 11;}

    public void validadorUsuarioInfo(UserDTO userDTO) {

        if (this.isEmptyName(userDTO)) throw new NameIsEmptyException();
        if (this.isEmptyEmail(userDTO)) throw new EmailIsEmptyException();
        if (this.isEmptyPhone(userDTO)) throw new PhoneIsEmptyException();

        if (this.isNameBlank(userDTO)) throw new NameIsEmptyException();
        if (this.isEmailBlank(userDTO)) throw new EmailIsEmptyException();
        if (this.isPhoneBlank(userDTO)) throw new PhoneIsEmptyException();

        if (this.nameExist(userDTO)) throw new NameExistException();
        if (this.emailExistente(userDTO)) throw new EmailExistException();
        if (this.phoneExist(userDTO)) throw new PhoneExistException();

        if (this.numberPhoneMoreThan11(userDTO)) throw new InvalidFormatPhoneException();

        if (!this.emailInvalido(userDTO)) throw new InvalidEmailException();
        if (!this.telefoneInvalido(userDTO)) throw new InvalidFormatPhoneException();

    }
}
