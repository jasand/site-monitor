package no.sensor.service.conversion;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */
public class SensorValueConverterTest {

    @Test
    public void testSmallFunction() {
        double result = SensorValueConverter.convertRawValue(6d, "X / 2");
        Assert.assertEquals(result,3d, 0.00001);
    }

    @Test
    public void testSmallFunctionDecimalInput() {
        double result = SensorValueConverter.convertRawValue(7.123456d, "X / 2");
        Assert.assertEquals(result,3.561728d, 0.00001);
    }

    @Test
    public void testSmallFunctionDecimal() {
        double result = SensorValueConverter.convertRawValue(122d, "X / 2.5");
        Assert.assertEquals(result,48.8, 0.00001);
    }

    @Test
    public void testParenthesisAndDecimal() {
        double result = SensorValueConverter.convertRawValue(122d, "((X + 4) / 3.14) * 2");
        Assert.assertEquals(result,80.254777d, 0.00001);
    }

    @Test
    public void testParenthesisAndDecimalNoSpace() {
        double result = SensorValueConverter.convertRawValue(122d, "((X+4)/3.14)*2");
        Assert.assertEquals(result,80.254777d, 0.00001);
    }

}
