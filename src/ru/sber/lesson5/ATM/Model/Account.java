package ru.sber.lesson5.ATM.Model;

import lombok.Data;
import ru.sber.lesson5.Card.Card;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class Account {

    public Account()
    {
        CurrentBalance = new Balance();
    }

    public String getAccount()
    {
        return Account;
    }

    public boolean getIsCredit()
    {
        return IsCredit;
    }

    public String getCurrencyIsoCode()
    {
        return CurrentBalance.getCurrencyIsoCode();
    }

    public void setCurrencyIsoCode(String Value)
    {
        CurrentBalance.setCurrencyIsoCode(Value);
    }

    public int getCurrencyCode()
    {
        return CurrentBalance.getCurrencyCode();
    }

    /* Percent on remainder or debt */
    public float GetPercent()
    {
        return Percent;
    }

    public BigDecimal getAvailableCash()
    {
        return CurrentBalance.getAvailableCash();
    }

    public void setAvailableCash(BigDecimal Value)
    {
        CurrentBalance.setAvailableCash(Value);
    }

    private Balance CurrentBalance;
    private int ClientNumber;
    private String Account;
    private ArrayList<Card> Cards;
    private boolean IsCredit;
    private BigDecimal CreditLimit;
    /* Percent on remainder or debt */
    private float Percent;
}
