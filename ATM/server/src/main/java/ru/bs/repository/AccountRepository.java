package ru.bs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bs.entity.Account;

@Repository
public interface AccountRepository  extends CrudRepository<Account, Long> {

    Account findByAccountAndClientId(String account, int clientId);
    Account findByAccountAndPin(String account, int pin);
}
