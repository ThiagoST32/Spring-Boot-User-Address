package dio.spring.projeto.spring.user.and.address.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dio.spring.projeto.spring.user.and.address.domain.User;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import dio.spring.projeto.spring.user.and.address.dto.updateDTO.UpdateUserDTO;
import dio.spring.projeto.spring.user.and.address.service.AddressService;
import dio.spring.projeto.spring.user.and.address.service.UserService;
import dio.spring.projeto.spring.user.and.address.service.cepService.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CepService cepService;

    @Autowired
    private AddressService addressService;

    @PostMapping
    @Validated
    public ResponseEntity<User>createUser(@RequestBody UserDTO userDTO) throws JsonProcessingException {
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
        User user = this.userService.getOneUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/checkCep/{cep}")
    public ResponseEntity<String>checkCepUser(@PathVariable("cep") String cep){
        String address = this.cepService.consultCep(cep);
        this.addressService.getAddressByCep(address);
        return new ResponseEntity<>(address, HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody UpdateUserDTO updateUserDTO){
        System.err.println(updateUserDTO.address());
        User updatedUser = this.userService.updateUser(id, updateUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }
}
