package com.seeha.stock.basic.dto;

import com.seeha.stock.basic.entity.FundDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResultVO {

    private String title;

    private String tooltip;

    private String[] legend;

    private List<String> xAxis;

    private List<List<BigDecimal>> series;

    private List<BigDecimal> sum;

    private String stockDesc;

    // 换手率，14天之内最高值除最低值大于 5倍的股票
    private BigDecimal turnRate;
}
