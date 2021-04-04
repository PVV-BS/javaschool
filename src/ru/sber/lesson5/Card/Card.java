package ru.sber.lesson5.Card;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Card {
    String GetNumber();
    String GetAccount();
    String GetFistName();
    String GetSecondName();
    int GetCSV();
    BigDecimal GetAvailableCash();
    LocalDate GetEndService();
    int GetPinCode();
    boolean IsCredit();
    BigDecimal GetCreditLimit();
    String GetCurrencyIso();
    int GetCurrencyCode();
    /* Percent on remainder or debt */
    float GetPercent();
}
