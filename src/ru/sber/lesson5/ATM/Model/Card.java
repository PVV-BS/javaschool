package ru.sber.lesson5.ATM.Model;

import lombok.Data;
import ru.sber.lesson5.ATM.Model.Account;
import ru.sber.lesson5.ATM.Model.CurrencyIsoCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Card {

    private String number;
    private String fistName;
    private String secondName;
    private int cvs;
    private LocalDate endService;
    private int pinCode;
    private Account account;

    public String getNumber()
    {
        return number;
    }

    public String getFistName()
    {
        return fistName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public int getCVS()
    {
        return cvs;
    }

    public LocalDate getEndService()
    {
        return endService;
    }

    public int getPinCode()
    {
        return pinCode;
    }

    public CurrencyIsoCode getCurrencyIsoCode(){
        return account.getCurrencyIsoCode();
    }

    public void setCurrencyIsoCode(CurrencyIsoCode code){
        account.setCurrencyIsoCode(code);
    }

    public BigDecimal getAvailableCash()
    {
        return account.getAvailableCash();
    }

    public String getAccount()
    {
        return account.getAccount();
    }

}
