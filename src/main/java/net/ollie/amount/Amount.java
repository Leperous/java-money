package net.ollie.amount;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ollie
 */
@XmlTransient
public abstract class Amount extends Number {

    private static final long serialVersionUID = 1L;

    public static final Amount ZERO = IntegerAmount.of(0);
    public static final Amount ONE = IntegerAmount.of(1);

    public static IntegerAmount of(final int amount) {
        return IntegerAmount.of(amount);
    }

    public abstract BigDecimal decimalValue(MathContext context);

    public abstract boolean isZero();

    public abstract Amount negate();

    @Override
    public int intValue() {
        return this.decimalValue(MathContext.DECIMAL32).intValue();
    }

    @Override
    public long longValue() {
        return this.decimalValue(MathContext.DECIMAL64).longValue();
    }

    @Override
    public float floatValue() {
        return this.decimalValue(MathContext.DECIMAL32).floatValue();
    }

    @Override
    public double doubleValue() {
        return this.decimalValue(MathContext.DECIMAL64).doubleValue();
    }

    public Amount plus(final Amount that) {
        return that.isZero()
                ? this
                : AmountSum.of(this, that);
    }

    public Amount plus(final int that) {
        return this.plus(of(that));
    }

    public Amount minus(final Amount that) {
        return this.plus(that.negate());
    }

    public Amount minus(final int that) {
        return this.plus(-that);
    }

    public Amount times(final Amount that) {
        return that.isZero()
                ? ZERO
                : AmountProduct.of(this, that);
    }

    public Amount times(final int that) {
        return this.times(Amount.of(that));
    }

    @Nonnull
    public Amount over(@Nonnull final Amount that) {
        return FractionalAmount.of(this, that);
    }

    @Nonnull
    public Amount over(final int that) {
        return this.over(Amount.of(that));
    }

    @Nonnull
    public Amount inverse() {
        return ONE.over(this);
    }

    @Nonnull
    public Amount round(@Nonnull final MathContext rounding) {
        return DecimalAmount.of(this.decimalValue(rounding));
    }

    @Nonnull
    public Amount roundDecimalPlaces(final int decimalPlaces) {
        return this.round(new MathContext(decimalPlaces, RoundingMode.HALF_UP));
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(final Object object) {
        return object instanceof Amount
                && this.equals((Amount) object, MathContext.DECIMAL128);
    }

    public boolean equals(final Amount that, final MathContext precision) {
        return this.decimalValue(precision).compareTo(that.decimalValue(precision)) == 0;
    }

    @Override
    public int hashCode() {
        return this.decimalValue(MathContext.DECIMAL128).hashCode();
    }

}
