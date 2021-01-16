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
}
