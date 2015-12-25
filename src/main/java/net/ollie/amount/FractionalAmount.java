package net.ollie.amount;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author ollie
 */
public class FractionalAmount extends Amount {

    private static final long serialVersionUID = 1L;

    public static final FractionalAmount ONE_HALF = new FractionalAmount(of(1), of(2));

    private final Amount numerator, denominator;

    public static Amount of(final Amount numerator, final Amount denominator) {
        return numerator.isZero()
                ? ZERO
                : new FractionalAmount(numerator, denominator);
    }

    FractionalAmount(final Amount numerator, final Amount denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean isZero() {
        return numerator.isZero();
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return numerator.decimalValue(context).divide(denominator.decimalValue(context)); //FIXME increase accuracy
    }

    @Override
    public FractionalAmount negate() {
        return new FractionalAmount(numerator.negate(), denominator);
    }

    @Override
    public Amount over(final Amount that) {
        return that instanceof FractionalAmount
                ? this.over((FractionalAmount) that)
                : of(numerator, denominator.times(that));
    }

    public FractionalAmount over(final FractionalAmount that) {
        return new FractionalAmount(numerator.times(that.denominator), denominator.times(that.numerator));
    }

    @Override
    public Amount times(final Amount that) {
        return that instanceof FractionalAmount
                ? this.times((FractionalAmount) that)
                : of(numerator.times(that), denominator);
    }

    public FractionalAmount times(final FractionalAmount that) {
        return new FractionalAmount(numerator.times(that.numerator), denominator.times(that.denominator));
    }

    @Override
    public FractionalAmount inverse() {
        return new FractionalAmount(denominator, numerator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

}
