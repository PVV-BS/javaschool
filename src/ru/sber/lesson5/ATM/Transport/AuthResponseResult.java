package ru.sber.lesson5.ATM.Transport;

import java.util.HashMap;
import java.util.Map;

public enum AuthResponseResult {
    AUTH_BAD_REQUEST ("Bad request"),
    AUTH_CLIENT_NOT_FOUND("Client not found"),
    AUTH_ACCOUNT_NOT_FOUND ("Account not found"),
    AUTH_CARD_NOT_REGISTERED ("The card is not registered"),
    AUTH_WRONG_CVS ("Wrong cvs code"),
    AUTH_WRONG_CODE ("Wrong pin code"),
    AUTH_WRONG_DATE_CARD ("The card date is not valid"),
    AUTH_AUTHORIZATION_SUCCESS ("AUTHORIZATION_SUCCESS");

    AuthResponseResult(String alias) {
        Alias = alias;
    }

    private final String Alias;

}
