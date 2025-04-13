package fast.delivery.auth.service.repository;

import fast.delivery.auth.service.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByPhoneNumber(String phoneNumber);
    boolean existsAccountEntityByPhoneNumber(String phoneNumber);
}
