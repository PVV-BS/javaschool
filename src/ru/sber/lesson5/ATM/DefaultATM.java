package ru.sber.lesson5.ATM;


import java.math.BigDecimal;
import java.lang.*;
import java.util.Objects;

import ru.sber.lesson5.Card.Card;
import ru.sber.lesson5.Card.Operation;

public class DefaultATM implements ATM {

    public Card getCurrentCard() {
        return CurrentCard;
    }

    @Override
    public Operation pushCash(BigDecimal summa) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Operation pullCash(BigDecimal summa) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getBalance() {
        return CurrentCard.getAvailableCash();
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

    private Card CurrentCard;

}
