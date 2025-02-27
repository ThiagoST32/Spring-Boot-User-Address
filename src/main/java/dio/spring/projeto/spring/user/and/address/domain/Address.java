package dio.spring.projeto.spring.user.and.address.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "address")
@Table(name = "address")
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rua", nullable = false)
    private String logradouro;

    @Column(name = "number", nullable = false)
    private int numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "cidade", nullable = false)
    private String localidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String uf;

    @OneToMany(mappedBy = "address")
    private List<User> users;


    public Address() {
    }

    public Address(String rua, int numero, String bairro, String cep, String cidade, String estado) {
        this.logradouro = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.localidade = cidade;
        this.uf = estado;
    }

    public Address(String cep) {
    }
}
