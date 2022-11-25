package com.ccut.tradeexperment.controller;

import com.ccut.tradeexperment.pojo.Candidate;
import com.ccut.tradeexperment.util.ValidateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccut.tradeexperment.service.CandidateDaoImpl;

import java.util.HashMap;
import java.util.Map;


/**
 * @author season
 */


@Controller
public class CandidateController {

    @Autowired
    private CandidateDaoImpl candidateDaoImpl;

    @ResponseBody
    @RequestMapping("/api/login")
    public Map<String,Object> postLoginStatus(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findBySeatId(seatID);

        if(!ValidateTools.checkEntityValidate(currentUser)) {
            response.put("isSeatValid", false);
            return response;
        }
        else {
            response.put("isSeatValid", true);
        }

        return response;
    }

    //RequestMapping用于接受前端的Param参数

    @ResponseBody
    @RequestMapping("/api/queryRound")
    public Map<String,Object> postCurrentRound(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findBySeatId(seatID);

        if(!ValidateTools.checkEntityValidate(currentUser)) {
            response.put("isSeatValid", false);
            response.put("currentRound",-1);
        }
        else {
            response.put("isSeatValid", true);
            response.put("currentRound", currentUser.getCurrentRound());
        }

        return response;
    }

    //PostMapping用于接受前端的Post请求

    @ResponseBody
    @PostMapping("/api/submitPolicy")
    public Map<String,Object> getTradePolicy(@RequestBody Map<String,Object> tradePolicy){
        Map<String,Object> response = new HashMap<String,Object>();

        //response.put("round",tradePolicy.get("round"));

        Candidate currentUser = candidateDaoImpl.findBySeatId((String)tradePolicy.get("seatID"));


        return response;
    }

}
