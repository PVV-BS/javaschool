package ru.sber.lesson5.Card;

import java.math.BigDecimal;
import java.util.Date;

public interface Operation {
    String Guid();
    String Account();
    String DeviceId();
    Date date();
    int Type();
    BigDecimal Sum();
}
