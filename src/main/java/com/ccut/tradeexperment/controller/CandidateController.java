package com.ccut.tradeexperment.controller;

import com.ccut.tradeexperment.pojo.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public Map<String,Object> getLoginStatus(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findBySeatId(seatID);

        if(currentUser!=null) {
            response.put("isValid", true);
        }
        else{
            response.put("isValid",false);
        }

        return response;
    }

    @ResponseBody
    @RequestMapping("/api/queryRound")
    public Map<String,Object> getCurrentRound(String seatID){
        Map<String,Object> response = new HashMap<String,Object>();
        Candidate currentUser = candidateDaoImpl.findBySeatId(seatID);

        if(currentUser!=null) {
            response.put("isValid", true);
            response.put("currentRound", currentUser.getCurrentRound()); }
        else{
            response.put("isValid",false);
        }

        return response;
    }
}
