package skadic.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by eti22 on 13.03.2017.
 */
public class Utils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
