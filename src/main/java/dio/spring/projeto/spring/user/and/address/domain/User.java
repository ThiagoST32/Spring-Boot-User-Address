package dio.spring.projeto.spring.user.and.address.domain;

import dio.spring.projeto.spring.user.and.address.dto.UserDTO;
import jakarta.persistence.*;

@Entity(name = "tab_user")
@Table(name = "tab_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone", nullable = false, length = 11, unique = true)
    private String telefone;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public User() {
    }

    public User(UserDTO userDTO, Address address){
        this.nome = userDTO.nome();
        this.sobrenome = userDTO.sobrenome();
        this.email = userDTO.email();
        this.telefone = userDTO.telefone();
        this.address = new Address(
          address.getLogradouro(),
          address.getNumero(),
          address.getBairro(),
          address.getCep(),
          address.getLocalidade(),
          address.getUf()
        );

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
