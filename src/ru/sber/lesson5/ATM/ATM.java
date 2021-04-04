package ru.sber.lesson5.ATM;

import ru.sber.lesson5.Card.Card;
import ru.sber.lesson5.Card.Operation;

import java.math.BigDecimal;

// TODO: exceptions

public interface ATM {
    Card GetCurrentCard();
    Operation PushCash();
    Operation PullCash();
    String GetBalance();
}
