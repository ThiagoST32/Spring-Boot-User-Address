package dio.spring.projeto.spring.user.and.address.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.dto.updateDTO.UpdateUserDTO;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.UserNotFoundException;
import dio.spring.projeto.spring.user.and.address.repository.AddressRepository;
import dio.spring.projeto.spring.user.and.address.repository.UserRepository;
import dio.spring.projeto.spring.user.and.address.service.cepService.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private UserValidator userValidator;


    public User saveUser(UserDTO userDTO) throws JsonProcessingException {
        this.userValidator.validadorUsuarioInfo(userDTO);
        User newUser = new User(userDTO, getAddressByCep(userDTO));
        this.userRepository.save(newUser);
        return newUser;
    }

    public Address getAddressByCep(UserDTO userDTO) throws JsonProcessingException {
        Address address = this.cepService.buscarEnderecoPorCep(userDTO.cep());
        address.setNumero(userDTO.number());
        return address;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(int id){
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public Optional<User> updateUserInfo(int id, UpdateUserDTO updateUserDTO) throws JsonProcessingException {
        User updatedUser = this.getUserById(id);
        this.userValidator(updateUserDTO);
        if (id == updatedUser.getId()) {
            updatedUser.setFirstName(updateUserDTO.firstName());
            updatedUser.setLastName(updateUserDTO.lastName());
            updatedUser.setEmail(updateUserDTO.email());
            updatedUser.setPhone(updateUserDTO.phone());
            this.updateAddressUser(updatedUser, id, updateUserDTO.cep(), updateUserDTO.number());
            this.userRepository.save(updatedUser);
        }
        return Optional.of(updatedUser);
    }

    @Transactional
    public Address updateAddressUser(User user,int id, String cep, int numero) throws JsonProcessingException {
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

}
