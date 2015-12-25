package net.ollie.currency;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import net.ollie.amount.Amount;

/**
 *
 * @author ollie
 */
public interface CurrencyMinorUnit extends CurrencyUnit {

    @Nonnull
    CurrencyUnit major();

    int inMajor();

    @Nonnull
    default CurrencyUnit ultimateMajor() {
        final CurrencyUnit major = this.major();
        return major instanceof CurrencyMinorUnit
                ? ((CurrencyMinorUnit) major).ultimateMajor()
                : major;
    }

    @Nonnull
    default Amount asMajor(final Amount inMinor) {
        return inMinor.over(this.inMajor());
    }

    default int inUltimateMajor() {
        CurrencyUnit major = this.major();
        return this.inMajor() * (major instanceof CurrencyMinorUnit
                ? ((CurrencyMinorUnit) major).inUltimateMajor()
                : 1);
    }

    @Nonnull
    default Amount asUltimateMajor(final Amount inMinor) {
        return inMinor.over(this.inUltimateMajor());
    }

    @Override
    @CheckForNull
    default CurrencyMinorUnit minor() {
        return null;
    }

}
