package com.ccut.tradeexperment.util;

/**
 * @author season
 */
public class CalculateTools {
    public static double[] calcFundPrice(double baseValue,double[] valueRatio){
        System.out.println(valueRatio.length);
        double[] fundPrice = new double[valueRatio.length];

        for(int i=0;i<valueRatio.length;i++){
            fundPrice[i] = baseValue * valueRatio[i]/100;
        }

        return fundPrice;
    }
}
