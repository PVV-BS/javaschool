package ru.sber.lesson5.Card;

import java.math.BigDecimal;
import java.util.Date;

public interface Card {
    String Number();
    String Account();
    String FistName();
    String SecondName();
    int CSV();
    BigDecimal AvailableCash();
    Date EndService();
    int PinCode();
    boolean IsCredit();
    BigDecimal CreditLimit();
    /* Percent on remainder or debt */
    float Percent();
}
