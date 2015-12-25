package net.ollie.currency;

import java.util.Currency;
import java.util.Locale;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Currency meta information.
 *
 * @author ollie
 * @see Currency
 */
public interface CurrencyUnit {

    @Nonnull
    CurrencyCode code();

    @Nonnull
    String symbol(Locale locale);

    @Nonnull
    default String symbol() {
        return this.symbol(Locale.getDefault());
    }

    @CheckForNull
    CurrencyMinorUnit minor();

    @CheckForNull
    default CurrencyMinorUnit ultimateMinor() {
        CurrencyMinorUnit minor = this.minor(), ultimateMinor = null;
        while (minor != null) {
            ultimateMinor = minor;
            minor = minor.minor();
        }
        return ultimateMinor;
    }

    int cashMultiple();
    
}
