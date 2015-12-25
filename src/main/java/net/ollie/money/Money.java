package net.ollie.money;

import javax.annotation.Nonnull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;

/**
 *
 * @author ollie
 */
public interface Money {

    @Nonnull
    Amount amount();

    @Nonnull
    CurrencyCode currency();

    default String toString(final MoneyFormat format) {
        return format.toString(this);
    }
}
