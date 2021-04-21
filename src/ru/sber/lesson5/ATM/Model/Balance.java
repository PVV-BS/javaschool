package ru.sber.lesson5.ATM.Model;

import java.math.BigDecimal;

public class Balance {

    public BigDecimal getAvailableCash(){
        return AvailableCash;
    }

    public void setAvailableCash(BigDecimal Value){
        AvailableCash = Value;
    }

    public CurrencyIsoCode getCurrencyIsoCode(){
        return CurrencyIsoCode;
    }

    public void setCurrencyIsoCode(CurrencyIsoCode Value){
        CurrencyIsoCode = Value;
    }

    private BigDecimal AvailableCash;
    private CurrencyIsoCode CurrencyIsoCode;

}
