package dio.spring.projeto.spring.user.and.address.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dio.spring.projeto.spring.user.and.address.domain.Address;
import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.AddressDTO;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.dto.updateDTO.UpdateUserDTO;
import dio.spring.projeto.spring.user.and.address.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserDTO userDTO) throws JsonProcessingException {
        Address address = Address.fromJsonCepReceiver(String.valueOf(userDTO.address()));
        User newUser = new User(userDTO);
        this.userRepository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getOneUser(int id){
        return this.userRepository.findById(id).orElseThrow( () -> new RuntimeException("User not Found!!"));
    }

    public boolean checkUserExist (int id){
       User checkUser = this.userRepository.findById(id).orElseThrow();
       if (id == checkUser.getId()){
            return true;
       }
       return false;
    }

    public User updateUser(int id, UpdateUserDTO updateUserDTO){
        User updatedUser = new User();
        if (this.checkUserExist(id)) {
            updatedUser.setNome(updateUserDTO.nome());
            updatedUser.setSobrenome(updateUserDTO.sobrenome());
            updatedUser.setEmail(updateUserDTO.email());
            updatedUser.setTelefone(updateUserDTO.telefone());
            AddressDTO addressDTO = updateUserDTO.address();
            Address teste = new Address(
                    addressDTO.rua(),
                    addressDTO.numero(),
                    addressDTO.bairro(),
                    addressDTO.cep(),
                    addressDTO.cidade(),
                    addressDTO.estado()
            );
            this.userRepository.save(updatedUser);
        }
        return updatedUser;
    }
}
