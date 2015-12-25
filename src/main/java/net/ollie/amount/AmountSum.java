package net.ollie.amount;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author ollie
 */
public class AmountSum extends Amount {

    private static final long serialVersionUID = 1L;

    public static Amount of(final Amount left, final Amount right) {
        if (left.isZero()) {
            return right;
        }
        if (right.isZero()) {
            return left;
        }
        return new AmountSum(Arrays.asList(left, right));
    }

    private final Collection<Amount> sum;

    AmountSum(final Collection<Amount> amounts) {
        this.sum = amounts;
    }

    @Override
    public boolean isZero() {
        return sum.stream().allMatch(Amount::isZero)
                || this.decimalValue(MathContext.DECIMAL128).signum() == 0; //FIXME
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        //TODO adjust context based on number of elements
        return sum.stream()
                .map(a -> a.decimalValue(context))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public AmountSum negate() {
        return new AmountSum(sum.stream().map(Amount::negate).collect(toList()));
    }

    @Override
    public AmountSum plus(final Amount that) {
        if (that instanceof AmountSum) {
            return this.plus((AmountSum) that);
        }
        final Collection<Amount> sum = new ArrayList<>(this.sum);
        sum.add(that);
        return new AmountSum(sum);
    }

    public AmountSum plus(final AmountSum that) {
        final Collection<Amount> sum = new ArrayList<>(this.sum);
        sum.addAll(that.sum);
        return new AmountSum(sum);
    }

    @Override
    public String toString() {
        return sum.stream()
                .map(Amount::toString)
                .collect(Collectors.joining(" + "));
    }

}
