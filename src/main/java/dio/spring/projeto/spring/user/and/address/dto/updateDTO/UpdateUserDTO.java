package dio.spring.projeto.spring.user.and.address.dto.updateDTO;

import dio.spring.projeto.spring.user.and.address.dto.AddressDTO;

public record UpdateUserDTO(String nome, String sobrenome, String email, String telefone, AddressDTO address) {
}
