package dio.spring.projeto.spring.user.and.address.repository;

import dio.spring.projeto.spring.user.and.address.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = """
    SELECT CASE WHEN COUNT(email) > 0 THEN TRUE ELSE FALSE END
    FROM "TAB_USER"
    WHERE EMAIL = :email
    """, nativeQuery = true)
    Boolean existByEmail(@Param("email") String email);

    @Query(value = """
    SELECT CASE WHEN COUNT(telefone) > 0 THEN TRUE ELSE FALSE END
    FROM "TAB_USER"
    WHERE TELEFONE = :telefone
    """ , nativeQuery = true)
    Boolean existByTelefone(@Param("telefone") String telefone);

    @Query(value = """
            SELECT CASE WHEN COUNT(nome) > 0 THEN TRUE ELSE FALSE END
            FROM "TAB_USER"
            WHERE NOME = :name
            """, nativeQuery = true)
    Boolean existByNome(String name);
}
