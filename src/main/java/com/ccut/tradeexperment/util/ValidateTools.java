package com.ccut.tradeexperment.util;

/**
 * @author season
 */
public class ValidateTools {
    public static <T> boolean isEntityValid(T entity){
        if(entity!=null){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean isTradeScaleValid(int totalScale){
        final int maxScale = 100;
        if(totalScale>maxScale){
            return false;
        }
        else{
            return true;
        }
    }
}
