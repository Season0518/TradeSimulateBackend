package com.ccut.tradeexperment.mapper;

import com.ccut.tradeexperment.pojo.Candidate;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author season
 */
//@Component
public interface CandidateMapper{
    public List<Candidate> findAll();
    public Candidate findBySeatId(String seatID);
    public boolean isSeatExisted(String seatID);
//    boolean updateBySeatId(String seatID);
}
