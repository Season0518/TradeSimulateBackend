package com.ccut.tradeexperment.util;

/**
 * @author season
 */

//在项目内，如果向sql保存超过两位小数，则无法进行正常的解析

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
