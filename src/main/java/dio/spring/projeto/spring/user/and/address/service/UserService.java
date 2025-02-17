package dio.spring.projeto.spring.user.and.address.service;

import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User saveUser(UserDTO userDTO){
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
}
