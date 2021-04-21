package ru.sber.lesson5.ATM;


import java.math.BigDecimal;
import java.lang.*;

import ru.sber.lesson5.ATM.Model.Card;
import ru.sber.lesson5.ATM.Model.Operation;

public class DefaultATM implements ATM {

    private Card CurrentCard;


    public Card getCurrentCard() {
        return CurrentCard;
    }

    @Override
    public Operation pushCash(BigDecimal Amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Operation pullCash(BigDecimal Amount) {
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

}
