package ru.bs.service;

import ru.bs.entity.Account;
import ru.bs.entity.Client;

import java.util.List;

public interface ClientService {
    Client getClientByPinCodeAndAccount(Integer pinCode, String account);

    Account getClientAccount(Client client, String account);

    List<String> getClientServices(Client client);
}
