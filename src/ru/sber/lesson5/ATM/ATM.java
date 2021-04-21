package ru.sber.lesson5.ATM;

import ru.sber.lesson5.ATM.Model.Operation;

import java.math.BigDecimal;

// TODO: exceptions

public interface ATM {
    Operation pushCash(BigDecimal Amount);
    Operation pullCash(BigDecimal Amount);
    BigDecimal getBalance();
}
