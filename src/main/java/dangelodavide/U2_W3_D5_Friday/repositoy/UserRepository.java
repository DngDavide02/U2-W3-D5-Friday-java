package dangelodavide.U2_W3_D5_Friday.repositoy;

import dangelodavide.U2_W3_D5_Friday.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail (String email);
    boolean existsByEmail (String email);
}
