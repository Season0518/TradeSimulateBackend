package com.ccut.tradeexperment.pojo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private int userAsset;
}
