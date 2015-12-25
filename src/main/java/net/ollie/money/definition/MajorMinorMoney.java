package net.ollie.money.definition;

import static java.util.Objects.requireNonNull;
import javax.annotation.Nonnull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;
import net.ollie.currency.CurrencyMinorUnit;
import net.ollie.currency.CurrencyUnit;
import net.ollie.money.Money;
import static net.ollie.utils.suppliers.Suppliers.firstNonNull;

/**
 *
 * @author ollie
 */
public class MajorMinorMoney implements Money {

    private final Amount majorAmount, minorAmount;
    private final CurrencyUnit majorUnit;
    private final CurrencyMinorUnit minorUnit;

    public MajorMinorMoney(
            final Amount major,
            final Amount minor,
            final CurrencyUnit majorUnit,
            final CurrencyMinorUnit minorUnit) {
        this.majorAmount = firstNonNull(major, Amount.ZERO);
        this.minorAmount = firstNonNull(minor, Amount.ZERO);
        this.majorUnit = requireNonNull(majorUnit);
        this.minorUnit = requireNonNull(minorUnit);
    }

    @Nonnull
    public Amount majorAmount() {
        return majorAmount;
    }

    @Nonnull
    public Amount minorAmount() {
        return minorAmount;
    }

    @Override
    public Amount amount() {
        return majorAmount.plus(minorUnit.asMajor(minorAmount));
    }

    @Override
    public CurrencyCode currency() {
        return majorUnit.code();
    }

    @Nonnull
    public CurrencyUnit majorUnit() {
        return majorUnit;
    }

    @Nonnull
    public CurrencyMinorUnit minorUnit() {
        return minorUnit;
    }

    @Nonnull
    public MajorMinorMoney normalize() {
        final Amount minor = Amount.of(this.minorAmount.intValue() % this.minorUnit.inMajor());
        final int extraMajor = this.minorAmount.intValue() / this.minorUnit.inMajor();
        final Amount major = this.majorAmount.plus(extraMajor);
        return new MajorMinorMoney(major, minor, majorUnit, minorUnit);
    }

}
