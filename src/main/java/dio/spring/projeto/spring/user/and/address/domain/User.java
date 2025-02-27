package dio.spring.projeto.spring.user.and.address.domain;

import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tab_user")
@Table(name = "tab_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName", nullable = false, unique = true)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, length = 11, unique = true)
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public User() {}

    public User(UserDTO userDTO, Address address){
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.email = userDTO.email();
        this.phone = userDTO.phone();
        this.address = new Address(
          address.getLogradouro(),
          address.getNumero(),
          address.getBairro(),
          address.getCep(),
          address.getLocalidade(),
          address.getUf()
        );

    }
}
