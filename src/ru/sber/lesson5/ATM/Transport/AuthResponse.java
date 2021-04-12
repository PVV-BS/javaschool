package ru.sber.lesson5.ATM.Transport;

import lombok.Data;

@Data
public class AuthResponse {

    public AuthResponse(String codeResult)
    {
        CodeResult = codeResult;
    }

    private String CodeResult;
    private String Account;
    private String Balance;
    private String CurrencyCodeIso;
}
