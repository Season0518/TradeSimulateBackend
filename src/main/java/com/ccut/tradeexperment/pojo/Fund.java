package com.ccut.tradeexperment.pojo;

//import com.sun.org.apache.xpath.internal.operations.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author season
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fund {
    int fundID;
    String fundDescription;
    double defaultValue;
    double[] fundRatios;
}
