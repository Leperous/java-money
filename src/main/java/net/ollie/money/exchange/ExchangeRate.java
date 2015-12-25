package net.ollie.money.exchange;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;
import net.ollie.money.MoneyFormat;

/**
 *
 * @author ollie
 */
public interface ExchangeRate {

    @Nonnull
    CurrencyCode fromCurrency();

    @Nonnull
    CurrencyCode toCurrency();

    @Nonnull
    @Nonnegative
    Amount multiplier();

    default String toString(final MoneyFormat format) {
        return format.toString(Amount.ONE, this.fromCurrency())
                + " = "
                + format.toString(this.multiplier(), this.toCurrency());
    }

    static ExchangeRate of(final CurrencyCode from, final CurrencyCode to, final Amount multiplier) {
        return new DefaultExchangeRate(from, to, multiplier);
    }

}
