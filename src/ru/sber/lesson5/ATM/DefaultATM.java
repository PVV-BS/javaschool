package ru.sber.lesson5.ATM;


import java.math.BigDecimal;
import java.lang.*;
import java.util.Objects;

import ru.sber.lesson5.Card.Card;
import ru.sber.lesson5.Card.Operation;

public class DefaultATM implements ATM {

    private Card CurrentCard;

    @Override
    public Card GetCurrentCard() {
        return CurrentCard;
    }

    @Override
    public Operation PushCash() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Operation PullCash() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String GetBalance() {
        return CurrentCard.GetAvailableCash().toString() + ' ' + CurrentCard.GetCurrencyIso();
    }

    private boolean DoAuthorization (Card card)
    {
        return true;
    }

    // need to subscribe on events of a device
    private void EventCardInsert(Card card)
    {
        if (DoAuthorization (card))
            CurrentCard = card;
    }

    private void EventCardEject()
    {

        CurrentCard = null;
    }

}
