package no.sensor.service.conversion;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jasand on 22.11.2016.
 */
public class SensorValueConverter {
    static final Logger LOG = LoggerFactory.getLogger(SensorValueConverter.class);

    public static Double convertRawValue(Double raw, String conversionFunc) {
        //
        // The raw value must always be the only variable in the expression
        // and it must always be called X
        //
        // See: http://www.objecthunter.net/exp4j/
        //
        if (raw == null || conversionFunc == null || conversionFunc.indexOf("X") < 0) {
            return null;
        }
        Expression e = new ExpressionBuilder(conversionFunc)
                .variables("X")
                .build()
                .setVariable("X", raw);
        double result = 0;
        try {
            result = e.evaluate();
        } catch (ArithmeticException aEx) {
            LOG.error("ArithmeticException. Check conversion function or sensor output/availability: " + aEx);
        } catch (Exception ex) {
            LOG.error("Exception caught: " + ex);
        }
        return result;
    }

}
