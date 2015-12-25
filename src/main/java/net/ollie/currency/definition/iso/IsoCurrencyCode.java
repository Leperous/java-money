package net.ollie.currency.definition.iso;

import static java.util.Objects.requireNonNull;
import net.ollie.currency.CurrencyCode;

/**
 *
 * @author ollie
 */
public class IsoCurrencyCode implements CurrencyCode {

    private final String code;

    public IsoCurrencyCode(final String code) {
        this.code = requireNonNull(code);
    }

    @Override
    public String code() {
        return code;
    }

    public boolean isXCurrency() {
        return code.charAt(0) == 'X';
    }

    @Override
    public String toString() {
        return code;
    }

}
