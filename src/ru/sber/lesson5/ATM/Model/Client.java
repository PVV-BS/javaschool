package ru.sber.lesson5.ATM.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class Client {
    @Getter
    @Setter
    private Integer number;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String secondName;
    @Getter
    @Setter
    private LocalDate birthday;
    private final Map<String, Account> accounts;
    private final Map<String, Card> cards;

    public Client(){
        accounts = new HashMap<>();
        cards = new HashMap<>();
    }

    public Optional<Account> GetAccount(String account)
    {
        return Optional.ofNullable(accounts.get(account));
    }

    public void setAccount(Account account)
    {
        accounts.put(account.getAccount(), account);
    }

    public Optional<Card> getCard(String card)
    {
        return Optional.ofNullable(cards.get(card));
    }

    public void setCard(Card card)
    {
        cards.put(card.getNumber(), card);
    }
}
