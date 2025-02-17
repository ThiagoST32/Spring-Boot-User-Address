package dio.spring.projeto.spring.user.and.address.domain;

import dio.spring.projeto.spring.user.and.address.dto.AddressDTO;
import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import jakarta.persistence.*;

@Entity(name = "user")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "id_address", nullable = false)
    @ManyToOne
    private Address address;

    public User() {
    }

    public User(UserDTO userDTO){
        this.nome = userDTO.nome();
        this.sobrenome = userDTO.sobrenome();
        this.email = userDTO.email();
        this.telefone = userDTO.telefone();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
