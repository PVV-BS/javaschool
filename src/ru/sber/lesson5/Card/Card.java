package ru.sber.lesson5.Card;

import javafx.scene.input.KeyCode;
import lombok.Data;
import ru.sber.lesson5.ATM.Model.Account;
import ru.sber.lesson5.ATM.Model.Balance;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Card {

    public String getNumber()
    {
        return Number;
    }

    public String getFistName()
    {
        return FistName;
    }

    public String getSecondName()
    {
        return SecondName;
    }

    public int getCSV()
    {
        return CSV;
    }

    public LocalDate getEndService()
    {
        return EndService;
    }

    public int getPinCode()
    {
        return PinCode;
    }

    public String getCurrencyIsoCode(){
        return Account.getCurrencyIsoCode();
    }

    public int getCurrencyCode(){
        return Account.getCurrencyCode();
    }

    public BigDecimal getAvailableCash()
    {
        return Account.getAvailableCash();
    }

    public String getAccount()
    {
        return Account.getAccount();
    }

    private String Number;
    private String FistName;
    private String SecondName;
    private int CSV;
    private LocalDate EndService;
    private int PinCode;
    private Account Account;
}
