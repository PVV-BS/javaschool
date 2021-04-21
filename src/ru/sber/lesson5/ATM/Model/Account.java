package ru.sber.lesson5.ATM.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class Account {

    private Balance balance;
    private int clientNumber;
    private String account;
    private ArrayList<Card> cards;
    private boolean isCredit;
    private BigDecimal creditLimit;
    /* Percent on remainder or debt */
    private double percent;

    public Account()
    {
        balance = new Balance();
    }

    public String getAccount()
    {
        return account;
    }

    public boolean getIsCredit()
    {
        return isCredit;
    }

    public CurrencyIsoCode getCurrencyIsoCode()
    {
        return balance.getCurrencyIsoCode();
    }

    public void setCurrencyIsoCode(CurrencyIsoCode Value)
    {
        balance.setCurrencyIsoCode(Value);
    }

    /* Percent on remainder or debt */
    public double GetPercent()
    {
        return percent;
    }

    public BigDecimal getAvailableCash()
    {
        return balance.getAvailableCash();
    }

    public void setAvailableCash(BigDecimal Value)
    {
        balance.setAvailableCash(Value);
    }

}
