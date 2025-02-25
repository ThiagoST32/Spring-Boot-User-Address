package dio.spring.projeto.spring.user.and.address.repository;

import dio.spring.projeto.spring.user.and.address.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<Boolean> findByEmail(String email);
    Optional<Boolean> findByTelefone(String telefone);
    Optional<Boolean> findByNome(String name);
}
