package dio.spring.projeto.spring.user.and.address.service;

import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.dto.updateDTO.UpdateUserDTO;
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


    private boolean invalidEmail(UserDTO userDTO){
        String validator = "^(.+)@(\\S+)$";
        return Pattern.compile(validator).matcher(userDTO.email()).matches();
    }

    private boolean invalidPhone(UserDTO userDTO){
        String validator = "^(?=[8-9])(?=[0-9]{8}).*";
        return Pattern.compile(validator).matcher(userDTO.phone()).matches();
    }

    private boolean invalidEmailUpdate(UpdateUserDTO updateUserDTO){
        String validator = "^(.+)@(\\S+)$";
        return Pattern.compile(validator).matcher(updateUserDTO.email()).matches();
    }

    private boolean invalidPhoneUpdate(UpdateUserDTO updateUserDTO){
        String validator = "^(?=[8-9])(?=[0-9]{8}).*";
        return Pattern.compile(validator).matcher(updateUserDTO.phone()).matches();
    }

    private boolean nameExist(UserDTO userDTO) {
        return this.userRepository.existByNome(userDTO.firstName());
    }

    private boolean existEmail(UserDTO userDTO){
        return this.userRepository.existByEmail(userDTO.email());
    }

    private boolean phoneExist(UserDTO userDTO){ return this.userRepository.existByTelefone(userDTO.phone());}

    private boolean isEmptyName(UserDTO userDTO) { return userDTO.firstName().isEmpty();}

    private boolean isEmptyEmail(UserDTO userDTO) { return userDTO.email().isEmpty();}

    private boolean isEmptyPhone(UserDTO userDTO) { return userDTO.phone().isEmpty();}

    private boolean isNameBlank(UserDTO userDTO) { return  userDTO.firstName().isBlank();}

    private boolean isEmailBlank(UserDTO userDTO) { return  userDTO.email().isBlank();}

    private boolean isPhoneBlank(UserDTO userDTO) { return userDTO.phone().isBlank();}

    private boolean numberPhoneMoreThan8(UserDTO userDTO) { return userDTO.phone().length() > 8;}

    public void validadorUsuarioInfo(UserDTO userDTO) {

        if (this.isEmptyName(userDTO)) throw new NameIsEmptyException();
        if (this.isEmptyEmail(userDTO)) throw new EmailIsEmptyException();
        if (this.isEmptyPhone(userDTO)) throw new PhoneIsEmptyException();

        if (this.isNameBlank(userDTO)) throw new NameIsEmptyException();
        if (this.isEmailBlank(userDTO)) throw new EmailIsEmptyException();
        if (this.isPhoneBlank(userDTO)) throw new PhoneIsEmptyException();

        if (this.nameExist(userDTO)) throw new NameExistException();
        if (this.existEmail(userDTO)) throw new EmailExistException();
        if (this.phoneExist(userDTO)) throw new PhoneExistException();

        if (this.numberPhoneMoreThan8(userDTO)) throw new InvalidFormatPhoneException();

        if (!this.invalidEmail(userDTO)) throw new InvalidEmailException();
        if (!this.invalidPhone(userDTO)) throw new InvalidFormatPhoneException();

    }

    private boolean isEmptyNameUpdate(UpdateUserDTO updateUserDTO) { return updateUserDTO.firstName().isEmpty();}

    private boolean isEmptyEmailUpdate(UpdateUserDTO updateUserDTO) { return updateUserDTO.email().isEmpty();}

    private boolean isEmptyPhoneUpdate(UpdateUserDTO updateUserDTO) { return updateUserDTO.phone().isEmpty();}

    private boolean isNameBlankUpdate(UpdateUserDTO updateUserDTO) { return  updateUserDTO.firstName().isBlank();}

    private boolean isEmailBlankUpdate(UpdateUserDTO updateUserDTO) { return  updateUserDTO.email().isBlank();}

    private boolean isPhoneBlankUpdate(UpdateUserDTO updateUserDTO) { return updateUserDTO.phone().isBlank();}

    private boolean numberPhoneMoreThan8Update(UpdateUserDTO updateUserDTO) { return updateUserDTO.phone().length() > 8;}

    public void validadorUsuarioInfoUpdate(UpdateUserDTO updateUserDTO) {

        if (this.isEmptyNameUpdate(updateUserDTO)) throw new NameIsEmptyException();
        if (this.isEmptyEmailUpdate(updateUserDTO)) throw new EmailIsEmptyException();
        if (this.isEmptyPhoneUpdate(updateUserDTO)) throw new PhoneIsEmptyException();

        if (this.isNameBlankUpdate(updateUserDTO)) throw new NameIsEmptyException();
        if (this.isEmailBlankUpdate(updateUserDTO)) throw new EmailIsEmptyException();
        if (this.isPhoneBlankUpdate(updateUserDTO)) throw new PhoneIsEmptyException();

        if (this.numberPhoneMoreThan8Update(updateUserDTO)) throw new InvalidFormatPhoneException();

        if (!this.invalidEmailUpdate(updateUserDTO)) throw new InvalidEmailException();
        if (!this.invalidPhoneUpdate(updateUserDTO)) throw new InvalidFormatPhoneException();

    }
}
