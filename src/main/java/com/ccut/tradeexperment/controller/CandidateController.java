package com.ccut.tradeexperment.controller;

import com.ccut.tradeexperment.pojo.Candidate;
import com.ccut.tradeexperment.pojo.Fund;
import com.ccut.tradeexperment.service.FundDaoImpl;
import com.ccut.tradeexperment.util.ValidateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ccut.tradeexperment.service.CandidateDaoImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author season
 */


@Controller
@CrossOrigin
public class CandidateController {

    @Autowired
    private CandidateDaoImpl candidateDaoImpl;
    @Autowired
    private FundDaoImpl fundDaoImpl;

    @ResponseBody
    @RequestMapping("/api/login")
    @CrossOrigin
    public Map<String,Object> postLoginStatus(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findEntityBySeatId(seatID);

        if(!ValidateTools.isEntityValid(currentUser)) {
            response.put("isSeatValid", false);
            return response;
        }
        else {
            if(currentUser.isOccupy()){
                response.put("isSeatValid",false);
            }
            else{
                response.put("isSeatValid", true);
                currentUser.setOccupy(true);
            }
        }

        candidateDaoImpl.updateEntityBySeatId(currentUser.getSeatID(),currentUser);
        return response;
    }

//    @ResponseBody
//    @RequestMapping("/api/createCandidate")
//    @CrossOrigin
//    public Map<String,Object> cr(String seatID) {
//
//    }



    //RequestMapping用于接受前端的Param参数

    @ResponseBody
    @RequestMapping("/api/queryRound")
    @CrossOrigin
    public Map<String,Object> postCurrentRound(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findEntityBySeatId(seatID);

        if(!ValidateTools.isEntityValid(currentUser)) {
            response.put("isSeatValid", false);
            response.put("currentRound",-1);
        }
        else {
            response.put("isSeatValid", true);
            response.put("currentRound", currentUser.getCurrentRound());
        }

        return response;
    }

    @ResponseBody
    @RequestMapping("/api/getAssets")
    @CrossOrigin
    public Map<String,Object> postUserAssets(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findEntityBySeatId(seatID);

        if(!ValidateTools.isEntityValid(currentUser)) {
            response.put("isSeatValid", false);
            response.put("userAssets",-1);
        }
        else {
            response.put("isSeatValid", true);
            response.put("userAssets", currentUser.getUserAsset());
        }

        return response;
    }

    @ResponseBody
    @RequestMapping("/api/queryStartTime")
    @CrossOrigin
    public Map<String,Object> postBeginTime(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findEntityBySeatId(seatID);

        if(!ValidateTools.isEntityValid(currentUser)) {
            response.put("isSeatValid", false);
            response.put("beginTime", -1);
        }
        else {
            response.put("isSeatValid", true);
            response.put("beginTime", currentUser.getBeginTime());
        }

        return response;
    }

    @ResponseBody
    @PostMapping("/api/postStartTime")
    @CrossOrigin
    public Map<String,Object> getBeginTime(@RequestBody Map<String,Object> requestBody){
        Map<String,Object> response = new HashMap<String,Object>();

        long startTime = Long.parseLong(requestBody.get("startTime").toString());
        String seatID = (String)requestBody.get("seatID");

        Candidate currentUser = candidateDaoImpl.findEntityBySeatId(seatID);
        System.out.println(currentUser.getSeatID());
        System.out.println(startTime);

        if(ValidateTools.isEntityValid(currentUser)) {
            currentUser.setBeginTime(new Timestamp(startTime));
            candidateDaoImpl.updateEntityBySeatId(seatID,currentUser);
            response.put("isValid",true);
        }
        else {
            response.put("isValid",false);
        }

        return response;
    }

    //PostMapping用于接受前端的Post请求

    @ResponseBody
    @PostMapping("/api/submitPolicy")
    @CrossOrigin
    public Map<String,Object> getTradePolicy(@RequestBody Map<String,Object> tradePolicy){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findEntityBySeatId((String)tradePolicy.get("seatID"));
        List<Fund> funds = fundDaoImpl.findAll();

        List<Integer> invPolicies = (List)tradePolicy.get("invPolicy");
        double userCurrentAsset = currentUser.getUserAsset();
        int userCurrentRound = currentUser.getCurrentRound();

        final int maxRound = 12;

        System.out.println(userCurrentAsset);

        if(ValidateTools.isEntityValid(currentUser)){
            if(!ValidateTools.isTradeScaleValid((int)tradePolicy.get("totalScale"))){
                response.put("isSeatValid",false);
            }
            currentUser.setCurrentRound((int)tradePolicy.get("round")+1);
            double totalInvCost = 0,totalInvReward = 0;

            for(int i=0;i<invPolicies.size();i++){
                totalInvCost += userCurrentAsset * (invPolicies.get(i)/100.0);
                System.out.println(totalInvCost);
                System.out.println(funds.size());
                totalInvReward += (userCurrentAsset * (invPolicies.get(i)/100.0))*(funds.get(i).getFundRatios()[userCurrentRound]/100);
                System.out.println(totalInvReward);
                System.out.println("---");
            }

            currentUser.setUserAsset(currentUser.getUserAsset()+(totalInvReward-totalInvCost));
            currentUser.setTotalProfit(currentUser.getTotalProfit()+(totalInvReward-totalInvCost));

            response.put("isSeatValid",true);
            response.put("totalAssets",currentUser.getUserAsset());
            response.put("currentProfit",totalInvReward-totalInvCost);
            response.put("totalProfit",currentUser.getTotalProfit());

            if(currentUser.getCurrentRound()>maxRound){
                currentUser.setOccupy(false);
            }

            candidateDaoImpl.updateEntityBySeatId(currentUser.getSeatID(),currentUser);
        }

        return response;
    }
}
