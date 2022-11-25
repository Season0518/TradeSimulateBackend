package com.ccut.tradeexperment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * @author season
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    private String seatID;
    private Timestamp beginTime;
    private int currentRound;
    private double userAsset;
    private double totalProfit;
}
