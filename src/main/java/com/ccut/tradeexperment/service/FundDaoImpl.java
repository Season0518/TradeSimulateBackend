package com.ccut.tradeexperment.service;

import com.ccut.tradeexperment.mapper.FundMapper;
import com.ccut.tradeexperment.pojo.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author season
 */
@Service("fundDaoImpl")
public class FundDaoImpl implements FundMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Fund> findAll() {
        String sql = "select * from fund";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Fund>(Fund.class));
        } catch (Exception e){
            System.out.println("exception");
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public Fund findEntityByFundId(int fundID) {
        String sql = "select * from fund where fundID=?";
        Object[] params = new Object[]{fundID};
        try {
            return jdbcTemplate.queryForObject(sql,
                    params,
                    new BeanPropertyRowMapper<Fund>(Fund.class));
        } catch (Exception e){
            System.out.println(e.toString());
            System.out.println("failed");
            return null;
        }
    }

    public void updateEntityByFundId(int fundID,Fund fund) {
        String sql = "insert into fund values(?,?,?,?) on duplicate key update fundDescription=?,defaultValue=?,fundRatios=?";

        StringBuilder arrayString = new StringBuilder();
        boolean isBegin = false;
        for(double fundPrice : fund.getFundRatios()){
            if(isBegin==true){
                arrayString.append(',');
            }
            arrayString.append(fundPrice);
            isBegin = true;
        }

        Object[] params = new Object[]{
                fundID,
                fund.getFundDescription(),
                fund.getDefaultValue(),
                arrayString.toString(),
                fund.getFundDescription(),
                fund.getDefaultValue(),
                arrayString.toString(),
        };
        try {
            jdbcTemplate.update(sql,
                    params);
        } catch (Exception e){
            System.out.println("false");
        }
    }
}
