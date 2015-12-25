package net.ollie.money.exchange;

import javax.annotation.Nonnull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;
import net.ollie.money.Money;
import net.ollie.money.definition.DefaultMoney;

/**
 * Rates into a particular currency.
 *
 * @author ollie
 */
public interface ExchangeIntoRate {

    @Nonnull
    CurrencyCode intoCurrency();

    @Nonnull
    Amount multiplier(@Nonnull CurrencyCode from);

    default ExchangeRate from(final CurrencyCode from) {
        return new DefaultExchangeRate(from, this.intoCurrency(), this.multiplier(from));
    }

    default ExchangeRate to(final CurrencyCode to) {
        return new DefaultExchangeRate(this.intoCurrency(), to, this.multiplier(to).inverse());
    }

    default ExchangeRate between(final CurrencyCode from, final CurrencyCode to) {
        return new DefaultExchangeRate(from, to, this.multiplier(from).over(this.multiplier(to)));
    }

    default Money exchange(final Money from) {
        if (this.intoCurrency().equals(from.currency())) {
            return from;
        }
        final Amount exchanged = from.amount().times(this.multiplier(from.currency()));
        return new DefaultMoney(exchanged, this.intoCurrency());
    }

}
