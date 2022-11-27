package com.ccut.tradeexperment.util;

import com.ccut.tradeexperment.pojo.Fund;

/**
 * @author season
 */
public class InitializeTools {
    public static Fund initFundTrend(int fundID,String description,double defaultValue,double risePercent){
        Fund fund = new Fund();
        fund.setFundID(fundID);
        fund.setFundDescription(description);
        fund.setDefaultValue(defaultValue);

        final int maximumRound = 12;
        final double ascendIndex = 0.06;
        final double descendIndex= 0.05;

        double[] fundTrend = new double[maximumRound+3];
        fundTrend[0] = 100;
        for(int i=1;i<=maximumRound;i++){
            double fundValue = fundTrend[i-1]/100;

            //设基金上涨概率为0.65 则大于0.65时(概率0.35)为下降
            if(Math.random()>risePercent){
                fundValue = Math.round((1-fundValue*descendIndex)*100);
            }
            else{
                fundValue = Math.round((1+fundValue*ascendIndex)*100);
            }

            fundTrend[i] = fundValue;
        }

        fund.setFundRatios(fundTrend);

        return fund;
    }

}
