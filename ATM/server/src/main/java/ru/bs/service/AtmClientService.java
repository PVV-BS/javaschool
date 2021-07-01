package ru.bs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bs.entity.Account;
import ru.bs.entity.Client;
import ru.bs.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AtmClientService implements ClientService {
    private AccountRepository accountRepository;

    @Override
    public Client getClientByPinCodeAndAccount(Integer pinCode, String account){
        Account clientAccount = accountRepository.findByAccountAndPin(account, pinCode);
        return (clientAccount != null) ? clientAccount.getClient() : null;
    }

    @Override
    public Account getClientAccount(Client client, String account){
        return accountRepository.findByAccountAndClientId(account, client.getId());
    }

    @Override
    public List<String> getClientServices(Client client) {
        List<String> services = new ArrayList<>();
        services.add("Check balance");
        services.add("Get cash");
        services.add("Make a transfer");
        services.add("Put cash");
        services.add("Exit");
        return services;
    }
}
