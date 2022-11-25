package com.ccut.tradeexperment.mapper;

import com.ccut.tradeexperment.pojo.Candidate;

import java.util.List;

/**
 * @author season
 */
//@Component
public interface CandidateMapper{
    public List<Candidate> findAll();
    public Candidate findEntityBySeatId(String seatID);
    public boolean updateEntityBySeatId(String seatID,Candidate candidate);
}
