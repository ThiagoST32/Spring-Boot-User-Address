package dio.spring.projeto.spring.user.and.address.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.dto.updateDTO.UpdateUserDTO;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.EmailExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.NameExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.PhoneExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.CepNotFoundException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidEmailException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidFormatPhoneException;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.UserNotFoundException;
import dio.spring.projeto.spring.user.and.address.repository.AddressRepository;
import dio.spring.projeto.spring.user.and.address.repository.UserRepository;
import dio.spring.projeto.spring.user.and.address.service.cepService.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CepService cepService;


    public User saveUser(UserDTO userDTO){
        User newUser = null;
        this.validadorUsuarioInfo(userDTO);
        newUser = new User(userDTO, getAddressByCep(userDTO));
        this.userRepository.save(newUser);
        return newUser;
    }

    public Address getAddressByCep(UserDTO userDTO){
        Address address = this.cepService.buscarEnderecoPorCep(userDTO.cep());
        address.setNumero(userDTO.numero());
        return address;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(int id){
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public Optional<User> updateUserInfo(int id, UpdateUserDTO updateUserDTO) throws JsonProcessingException, InvalidEmailException, InvalidFormatPhoneException {
        User updatedUser = this.getUserById(id);
        if (id == updatedUser.getId()) {
            System.err.println(updateUserDTO);
            updatedUser.setNome(updateUserDTO.nome());
            updatedUser.setSobrenome(updateUserDTO.sobrenome());
            updatedUser.setEmail(updateUserDTO.email());
            updatedUser.setTelefone(updateUserDTO.telefone());
            this.updateAddressUser(updatedUser, id, updateUserDTO.cep(), updateUserDTO.numero());
            this.userRepository.save(updatedUser);
        }
        return Optional.of(updatedUser);
    }

    @Transactional
    public Address updateAddressUser(User user,int id, String cep, int numero) throws JsonProcessingException, CepNotFoundException {
        Address updatedAddress = user.getAddress();
        Address newAddressInfo = this.cepService.buscarEnderecoPorCep(cep);
        if (id == user.getId()){
            updatedAddress.setNumero(numero);
            updatedAddress.setBairro(newAddressInfo.getBairro());
            updatedAddress.setCep(newAddressInfo.getCep());
            updatedAddress.setLocalidade(newAddressInfo.getLocalidade());
            updatedAddress.setLogradouro(newAddressInfo.getLogradouro());
            updatedAddress.setUf(newAddressInfo.getUf());
        }
        return updatedAddress;
    }

    @Transactional
    public void deleteUserById(int id) throws UserNotFoundException{
        this.userRepository.deleteById(id);
    }

    public boolean emailInvalido(UserDTO userDTO){
        String validador = "^(.+)@(\\S+)$";
        return Pattern.compile(validador).matcher(userDTO.email()).matches();
    }

    public boolean telefoneInvalido(UserDTO userDTO){
        String validador = "^(?=[8-9]{1})(?=[0-9]{8}).*";
        return Pattern.compile(validador).matcher(userDTO.telefone()).matches();
    }

    public Boolean emailExistente(UserDTO userDTO){
        return this.userRepository.findByEmail(userDTO.email()).orElseThrow(EmailExistException::new);
    }

    public boolean phoneExist(UserDTO userDTO){
        return this.userRepository.findByTelefone(userDTO.telefone()).orElseThrow(PhoneExistException::new);
    }

    public boolean nameExist(UserDTO userDTO) {
        return this.userRepository.findByNome(userDTO.nome()).orElseThrow(NameExistException::new);
    }

    public void validadorUsuarioInfo(UserDTO userDTO){
        if (!this.emailInvalido(userDTO)) throw new InvalidEmailException();
        //if (!this.emailExistente(userDTO));
        if (!this.telefoneInvalido(userDTO)) throw new InvalidFormatPhoneException();
        //if (!this.phoneExist(userDTO));
        //if (!this.nameExist(userDTO));
    }

}
