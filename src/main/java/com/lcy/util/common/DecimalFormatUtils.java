package com.lcy.util.common;

import java.math.BigDecimal;

public class DecimalFormatUtils {

    public static float floatFormat(float f){
        BigDecimal bd = new BigDecimal(f);
        return bd.setScale(2, 4).floatValue();
    }

}
