package dio.spring.projeto.spring.user.and.address.repository;

import dio.spring.projeto.spring.user.and.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
