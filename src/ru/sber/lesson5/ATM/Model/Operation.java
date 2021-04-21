package ru.sber.lesson5.ATM.Model;

import java.math.BigDecimal;
import java.util.Date;

public interface Operation {
    String guid();
    String account();
    String deviceId();
    Date date();
    int type();
    BigDecimal amount();
}
