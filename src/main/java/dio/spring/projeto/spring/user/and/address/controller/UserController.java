package dio.spring.projeto.spring.user.and.address.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.dto.updateDTO.UpdateUserDTO;
import dio.spring.projeto.spring.user.and.address.service.UserService;
import dio.spring.projeto.spring.user.and.address.service.cepService.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CepService cepService;

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody @Validated UserDTO userDTO) throws JsonProcessingException {
        this.checkCepUser(userDTO.cep());
        User saveUser = this.userService.saveUser(userDTO);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = this.userService.getUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/checkCep/{cep}")
    public ResponseEntity<String>checkCepUser(@PathVariable("cep") String cep) throws JsonProcessingException {
        String address = String.valueOf((this.cepService.buscarEnderecoPorCep(cep)));
        return new ResponseEntity<>(address, HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody UpdateUserDTO updateUserDTO) throws JsonProcessingException {
        Optional<User> updatedUser = this.userService.updateUserInfo(id, updateUserDTO);
        System.err.println(updateUserDTO);
        return new ResponseEntity(updatedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id){
        this.userService.deleteUserById(id);
    }
}
