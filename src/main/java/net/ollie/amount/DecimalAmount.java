package net.ollie.amount;

import java.math.BigDecimal;
import java.math.MathContext;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class DecimalAmount extends Amount {

    private static final long serialVersionUID = 1L;

    private static final DecimalAmount ZERO = new DecimalAmount(BigDecimal.ZERO);

    public static DecimalAmount of(final Number number) {
        return number instanceof BigDecimal
                ? of((BigDecimal) number)
                : of(BigDecimal.valueOf(number.doubleValue()));
    }

    public static DecimalAmount of(final BigDecimal decimal) {
        if (decimal.signum() == 0) {
            return ZERO;
        }
        return new DecimalAmount(decimal);
    }

    @XmlValue
    private BigDecimal value;

    @Deprecated //XML unmarshalling
    private DecimalAmount() {
    }

    DecimalAmount(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean isZero() {
        return value.signum() == 0;
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return value.round(context);
    }

    @Override
    public Amount negate() {
        return DecimalAmount.of(value.negate());
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
