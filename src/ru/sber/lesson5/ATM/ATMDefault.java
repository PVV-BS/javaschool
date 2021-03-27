package ru.sber.lesson5.ATM;


import java.math.BigDecimal;
import ru.sber.lesson5.Card.Card;
import ru.sber.lesson5.Card.Operation;

public class ATMDefault implements ATM {

    private Card CurrentCard;

    // need to subscribe on events of a device
    private void EventCardInsert(Card card)
    {
        CurrentCard = card;
    }

    private void EventCardEject()
    {
        CurrentCard = null;
    }

    @Override
    public Card CurrentCard() {
        return CurrentCard;
    }

    @Override
    public Operation PushCash() {
        return null;
    }

    @Override
    public Operation PullCash() {
        return null;
    }

    @Override
    public BigDecimal Balance() {
        return CurrentCard.AvailableCash();
    }
}
