package net.ollie.amount;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author ollie
 */
public class IntegerAmount extends Amount {

    private static final long serialVersionUID = 1L;
    private static final IntegerAmount ZERO = new IntegerAmount(0);

    public static IntegerAmount of(final int i) {
        return i == 0
                ? ZERO
                : new IntegerAmount(i);
    }

    private final long value;

    IntegerAmount(final long value) {
        this.value = value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public int intValue() {
        return Math.toIntExact(value);
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(value);
    }

    @Override
    public IntegerAmount negate() {
        return new IntegerAmount(-value);
    }

    @Override
    public IntegerAmount roundDecimalPlaces(int decimalPlaces) {
        return this;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

}
