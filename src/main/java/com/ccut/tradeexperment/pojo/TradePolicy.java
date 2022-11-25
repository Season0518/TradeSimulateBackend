package com.ccut.tradeexperment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author season
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradePolicy {
    private String seatID;
    private int round;
    private int[] invPolicy;
    private int totalScale;
}
