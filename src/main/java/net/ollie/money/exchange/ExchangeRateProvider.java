package net.ollie.money.exchange;

import java.time.Instant;
import javax.annotation.Nonnull;
import net.ollie.currency.CurrencyCode;
import net.ollie.money.Money;

/**
 *
 * @author ollie
 * @see ExchangeRateSnapshot
 */
public interface ExchangeRateProvider {

    @Nonnull
    ExchangeRateSnapshot snapshotAt(Instant when);

    @Nonnull
    default Money exchangeAt(@Nonnull final Money money, @Nonnull final CurrencyCode targetCurrency, final Instant time) {
        return this.snapshotAt(time).exchange(money, targetCurrency);
    }

    @Nonnull
    default Money exchangeNow(final Money money, final CurrencyCode targetCurrency) {
        return this.exchangeAt(money, targetCurrency, Instant.now());
    }

}
