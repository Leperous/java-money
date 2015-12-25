package net.ollie.money.definition;

import static java.util.Objects.requireNonNull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;
import net.ollie.money.Money;
import net.ollie.money.MoneyFormat;

/**
 *
 * @author ollie
 */
public class DefaultMoney implements Money {

    private final Amount amount;
    private final CurrencyCode currency;

    public DefaultMoney(final Amount amount, final CurrencyCode currency) {
        this.amount = requireNonNull(amount);
        this.currency = requireNonNull(currency);
    }

    @Override
    public Amount amount() {
        return amount;
    }

    @Override
    public CurrencyCode currency() {
        return currency;
    }

    @Override
    public String toString() {
        return this.toString(MoneyFormat.CURRENCY_UNLIMITED_AMOUNT);
    }

}
