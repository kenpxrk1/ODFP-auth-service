package fast.delivery.auth.service.service.account;

import fast.delivery.auth.service.model.AccountEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    boolean existsByPhoneNumber(String phoneNumber);
    AccountEntity save(AccountEntity account);
}
