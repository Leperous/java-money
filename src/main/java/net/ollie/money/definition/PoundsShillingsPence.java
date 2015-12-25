package net.ollie.money.definition;

import java.util.Locale;
import javax.annotation.Nonnull;
import net.ollie.amount.Amount;
import net.ollie.currency.CurrencyCode;
import net.ollie.currency.CurrencyMinorUnit;
import net.ollie.currency.CurrencyUnit;
import net.ollie.currency.definition.DefaultCurrencyCode;
import net.ollie.money.Money;
import static net.ollie.utils.suppliers.Suppliers.firstNonNull;

/**
 *
 * @author ollie
 */
public class PoundsShillingsPence implements Money {

    public static final Pound POUND = new Pound();
    public static final Shilling SHILLING = new Shilling();
    public static final Penny PENCE = new Penny();

    private final Amount pounds, shillings, pence;

    public PoundsShillingsPence(final Amount pounds, final Amount shillings, final Amount pence) {
        this.pounds = firstNonNull(pounds, Amount.ZERO);
        this.shillings = firstNonNull(shillings, Amount.ZERO);
        this.pence = firstNonNull(pence, Amount.ZERO);
    }

    @Override
    public CurrencyCode currency() {
        return POUND.code();
    }

    @Nonnull
    public Amount pounds() {
        return pounds;
    }

    @Nonnull
    public Amount shillings() {
        return shillings;
    }

    @Nonnull
    public Amount pence() {
        return pence;
    }

    @Override
    public Amount amount() {
        return pounds
                .plus(SHILLING.asUltimateMajor(shillings))
                .plus(PENCE.asUltimateMajor(pence));
    }

    public PoundsShillingsPence normalize() {
        final Amount pence = Amount.of(this.pence.intValue() % PENCE.inMajor());
        final int extraShillings = this.pence.intValue() / PENCE.inMajor();
        final Amount shillings = this.shillings.plus(extraShillings);
        final int extraPounds = shillings.intValue() / SHILLING.inMajor();
        final Amount pounds = this.pounds.plus(extraPounds);
        return new PoundsShillingsPence(pounds, shillings, pence);
    }

    public PoundsShillingsPence plus(final PoundsShillingsPence that) {
        return new PoundsShillingsPence(
                pounds.plus(that.pounds),
                shillings.plus(that.shillings),
                pence.plus(that.pence));
    }

    private interface PoundsShillingPenceUnit extends CurrencyUnit {

        @Override
        default Penny ultimateMinor() {
            return PENCE;
        }

        @Override
        default CurrencyCode code() {
            return new DefaultCurrencyCode("£sd/" + this.symbol());
        }

        @Override
        default int cashMultiple() {
            return 1;
        }

    }

    public static class Pound implements PoundsShillingPenceUnit {

        @Override
        public String symbol(final Locale locale) {
            return "l";
        }

        @Override
        public CurrencyMinorUnit minor() {
            return SHILLING;
        }

    }

    public static class Shilling implements PoundsShillingPenceUnit, CurrencyMinorUnit {

        @Override
        public int inMajor() {
            return 20;
        }

        @Override
        public int inUltimateMajor() {
            return this.inMajor();
        }

        @Override
        public Pound major() {
            return POUND;
        }

        @Override
        public CurrencyUnit ultimateMajor() {
            return POUND;
        }

        @Override
        public String symbol(Locale locale) {
            return "s";
        }

        @Override
        public CurrencyMinorUnit minor() {
            return PENCE;
        }

    }

    public static class Penny implements PoundsShillingPenceUnit, CurrencyMinorUnit {

        @Override
        public int inMajor() {
            return 12;
        }

        @Override
        public int inUltimateMajor() {
            return this.inMajor() * SHILLING.inUltimateMajor();
        }

        @Override
        public CurrencyUnit major() {
            return SHILLING;
        }

        @Override
        public CurrencyUnit ultimateMajor() {
            return POUND;
        }

        @Override
        public String symbol(Locale locale) {
            return "d";
        }

    }

}
