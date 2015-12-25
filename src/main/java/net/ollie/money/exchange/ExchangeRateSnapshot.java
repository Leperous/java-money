package net.ollie.money.exchange;

import javax.annotation.Nonnull;
import net.ollie.currency.CurrencyCode;
import net.ollie.money.Money;

/**
 *
 * @author ollie
 * @see ExchangeRateProvider
 */
public interface ExchangeRateSnapshot {

    @Nonnull
    ExchangeIntoRate rates(@Nonnull CurrencyCode currency);

    @Nonnull
    default Money exchange(@Nonnull final Money money, @Nonnull final CurrencyCode targetCurrency) {
        return this.rates(targetCurrency).exchange(money);
    }

}
