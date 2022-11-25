package com.ccut.tradeexperment.util;

/**
 * @author season
 */
public class ValidateTools {
    public static <T> boolean checkEntityValidate(T entity){
        if(entity!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
