package ru.sber.lesson5.ATM.Model;

import lombok.Data;
import ru.sber.lesson5.Card.Card;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

@Data
public class Client {
    private Integer Number;
    private String FirstName;
    private String SecondName;
    private LocalDate Birthday;
    private Hashtable<String, Account> Accounts;
    private Hashtable<String, Card> Cards;
}
