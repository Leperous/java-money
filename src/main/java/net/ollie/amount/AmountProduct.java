package net.ollie.amount;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author ollie
 */
public class AmountProduct extends Amount {

    private static final long serialVersionUID = 1L;

    public static Amount of(final Amount left, final Amount right) {
        return left.isZero() || right.isZero()
                ? ZERO
                : new AmountProduct(Arrays.asList(left, right));
    }

    private final Collection<Amount> product;

    AmountProduct(final Collection<Amount> product) {
        this.product = product;
    }

    @Override
    public boolean isZero() {
        return product.stream().anyMatch(Amount::isZero);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        //TODO adjust context based on numbers of elements
        return product.stream()
                .map(a -> a.decimalValue(context))
                .reduce(BigDecimal.ONE, BigDecimal::multiply);

    }

    private AmountProduct with(final Amount amount) {
        final Collection<Amount> product = new ArrayList<>(this.product);
        product.add(amount);
        return new AmountProduct(product);
    }

    @Override
    public Amount times(final Amount that) {
        if (that.isZero()) {
            return ZERO;
        }
        return that instanceof AmountProduct
                ? this.times((AmountProduct) that)
                : this.with(that);
    }

    public AmountProduct times(final AmountProduct that) {
        final Collection<Amount> product = new ArrayList<>(this.product);
        product.addAll(that.product);
        return new AmountProduct(product);
    }

    @Override
    public AmountProduct negate() {
        return this.with(Amount.ONE.negate());
    }

    @Override
    public String toString() {
        return product.stream()
                .map(Amount::toString)
                .collect(Collectors.joining(" * "));
    }

}
