package net.ollie.money;

import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;

/**
 *
 * @author ollie
 */
public interface MoneyFormat {

    String toString(Amount amount, CurrencyCode currency);

    default String toString(final Money money) {
        return this.toString(money.amount(), money.currency());
    }

    MoneyFormat DEFAULT = (amount, currency) -> currency.code() + ' ' + amount.roundDecimalPlaces(2);
    MoneyFormat CURRENCY_UNLIMITED_AMOUNT = (amount, currency) -> currency.code() + ' ' + amount;
    MoneyFormat CURRENCY_INTEGER_AMOUNT = (amount, currency) -> currency.code() + ' ' + amount.roundDecimalPlaces(0);

}
