package com.ccut.tradeexperment.service;


import com.ccut.tradeexperment.mapper.CandidateMapper;
import com.ccut.tradeexperment.pojo.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Service("candidateDaoImpl")
public class CandidateDaoImpl implements CandidateMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Candidate> findAll() {
        String sql = "select * from candidate";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Candidate.class));
    }

    @Override
    public Candidate findEntityBySeatId(String seatID) {
        String sql = "select * from candidate where seatID=?";
        Object[] params = new Object[]{seatID};
        try {
            return jdbcTemplate.queryForObject(sql,
                    params,
                    new BeanPropertyRowMapper<Candidate>(Candidate.class));
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public boolean updateEntityBySeatId(String seatID,Candidate candidate){
        String sql = "insert into Main.candidate values(?,?,?,?,?) on duplicate key update beginTime=?,currentRound=?,userAsset=?,totalProfit=?";

        Object[] params = new Object[]{
                candidate.getSeatID(),
                candidate.getBeginTime(),
                candidate.getCurrentRound(),
                candidate.getUserAsset(),
                candidate.getTotalProfit(),
                candidate.getBeginTime(),
                candidate.getCurrentRound(),
                candidate.getUserAsset(),
                candidate.getTotalProfit(),
                };
        System.out.println(candidate);
        try {
            return jdbcTemplate.update(sql,
                    params)>0;
        } catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}
