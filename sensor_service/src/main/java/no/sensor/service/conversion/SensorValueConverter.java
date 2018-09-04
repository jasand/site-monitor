package no.sensor.service.conversion;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Created by jasand on 22.11.2016.
 */
public class SensorValueConverter {

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
        double result = e.evaluate();
        return result;
    }

}
