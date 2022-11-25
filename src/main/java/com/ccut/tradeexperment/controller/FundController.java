package com.ccut.tradeexperment.controller;

import com.ccut.tradeexperment.pojo.Fund;
import com.ccut.tradeexperment.service.FundDaoImpl;
import com.ccut.tradeexperment.util.CalculateTools;
import com.ccut.tradeexperment.util.InitializeTools;
import com.ccut.tradeexperment.util.ValidateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//      实例

//        fundDaoImpl.updateEntityByFundId("3",currentFund);
//        fundDaoImpl.updateEntityByFundId("4",currentFund);

//        double[] array = currentFund.getFundPrice();
//        System.out.println(array[2]);

@Controller
public class FundController {

    @Autowired
    private FundDaoImpl fundDaoImpl;

    @ResponseBody
    @RequestMapping("/api/description")
    public Map<String,Object> postFundDescription(int fundID){
        Map<String,Object> response = new HashMap<String,Object>();
        Fund currentFund = fundDaoImpl.findEntityByFundId(fundID);

        if(currentFund==null){
            response.put("isValid",false);
        }
        else{
            response.put("fundDescription",currentFund.getFundDescription());
        }

        return response;
    }

    @ResponseBody
    @RequestMapping("/api/queryTrend")
    public Map<String,Object> getFundTrend(int fundID){
        Map<String,Object> response = new HashMap<String,Object>();
        Fund currentFund = fundDaoImpl.findEntityByFundId(fundID);

        double[] fundPrices = CalculateTools.calcFundPrice(currentFund.getDefaultValue(),currentFund.getFundRatios());

        if(ValidateTools.isEntityValid(currentFund)) {
            response.put("isSeatValid",true);
            response.put("fundTrend",fundPrices);
        }
        else{
            response.put("isSeatValid",false);
            response.put("fundTrend",fundPrices);
        }

        return response;
    }

    @ResponseBody
    @RequestMapping("/api/initFund")
    public Map<String,Object> initFundTrend(){
        Map<String,Object> response = new HashMap<String,Object>();

        Fund fund1 = InitializeTools.initFundTrend(1,"这是第一支基金",100,0.65);
        Fund fund2 = InitializeTools.initFundTrend(2,"这是第二支基金",100,0.50);
        Fund fund3 = InitializeTools.initFundTrend(3,"这是第三支基金",100,0.35);

        fundDaoImpl.updateEntityByFundId(fund1.getFundID(),fund1);
        fundDaoImpl.updateEntityByFundId(fund2.getFundID(),fund2);
        fundDaoImpl.updateEntityByFundId(fund3.getFundID(),fund3);

        response.put("Succeed",true);
        return response;
    }
}
