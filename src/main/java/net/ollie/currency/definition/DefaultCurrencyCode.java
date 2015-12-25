package net.ollie.currency.definition;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import net.ollie.currency.CurrencyCode;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class DefaultCurrencyCode implements CurrencyCode {

    @Nonnull
    public static DefaultCurrencyCode of(@Nonnull final CurrencyCode code) {
        return code instanceof DefaultCurrencyCode
                ? (DefaultCurrencyCode) code
                : new DefaultCurrencyCode(code.code());
    }

    @XmlValue
    private String code;

    @Deprecated
    DefaultCurrencyCode() {
    }

    public DefaultCurrencyCode(final String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
    
    @Override
    public String toString() {
        return code;
    }

}
