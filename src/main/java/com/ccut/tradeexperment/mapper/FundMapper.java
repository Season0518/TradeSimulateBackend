package com.ccut.tradeexperment.mapper;

import com.ccut.tradeexperment.pojo.Fund;

import java.util.List;

public interface FundMapper {
    public List<Fund> findAll();
    public Fund findEntityByFundId(int fundID);
    public void updateEntityByFundId(int fundID,Fund fund);
}
