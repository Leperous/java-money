package net.ollie.money.exchange;

import static java.util.Objects.requireNonNull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;

/**
 *
 * @author ollie
 */
public class DefaultExchangeRate implements ExchangeRate {

    private final CurrencyCode from;
    private final CurrencyCode to;
    private final Amount multiplier;

    public DefaultExchangeRate(final CurrencyCode from, final CurrencyCode to, final Amount multiplier) {
        this.from = requireNonNull(from);
        this.to = requireNonNull(to);
        this.multiplier = requireNonNull(multiplier);
    }

    @Override
    public CurrencyCode fromCurrency() {
        return from;
    }

    @Override
    public CurrencyCode toCurrency() {
        return to;
    }

    @Override
    public Amount multiplier() {
        return multiplier;
    }
    
}
