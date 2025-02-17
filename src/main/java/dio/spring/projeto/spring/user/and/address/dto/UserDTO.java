package dio.spring.projeto.spring.user.and.address.dto;

import dio.spring.projeto.spring.user.and.address.domain.Address;

public record UserDTO(String nome, String sobrenome, String email, String telefone, Address address) {
}
