package ru.sber.lesson5.ATM.Transport;

import lombok.Data;
import ru.sber.lesson5.ATM.Model.CurrencyIsoCode;

@Data
public class AuthResponse {

    public AuthResponse(AuthResponseResult codeResult)
    {
        this.codeResult = codeResult;
    }

    private AuthResponseResult codeResult;
    private String account;
    private String balance;
    private CurrencyIsoCode currencyCodeIso;
}
