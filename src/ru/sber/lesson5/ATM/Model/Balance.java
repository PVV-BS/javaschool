package ru.sber.lesson5.ATM.Model;

import java.math.BigDecimal;

public class Balance {

    public BigDecimal getAvailableCash(){
        return AvailableCash;
    }

    public void setAvailableCash(BigDecimal Value){
        AvailableCash = Value;
    }

    public String getCurrencyIsoCode(){
        return CurrencyIsoCode;
    }

    public void setCurrencyIsoCode(String Value){
        CurrencyIsoCode = Value;
    }

    public int getCurrencyCode(){
        return CurrencyCode;
    }

    private BigDecimal AvailableCash;
    private String CurrencyIsoCode;
    private int CurrencyCode;

}
