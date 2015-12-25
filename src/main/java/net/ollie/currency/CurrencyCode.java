package net.ollie.currency;

import javax.annotation.Nonnull;
import net.ollie.currency.definition.iso.IsoCurrencyCode;

/**
 *
 * @author ollie
 */
public interface CurrencyCode {

    @Nonnull
    String code();

    static IsoCurrencyCode ofIso(@Nonnull final String iso) {
        return new IsoCurrencyCode(iso);
    }

}
