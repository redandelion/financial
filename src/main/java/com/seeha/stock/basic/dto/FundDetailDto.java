package com.seeha.stock.basic.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundDetailDto {

    /**
     * 超大单流入(庄家)
      */
    private BigDecimal f64;
    /**
     * 超大的流出(庄家)
      */
    private BigDecimal f65;

    /**
     * 大单流入(主力)
     */
    private BigDecimal f70;
    /**
     * 大的流出(主力)
     */
    private BigDecimal f71;

    /**
     * 中单流入(投资者)
     */
    private BigDecimal f76;
    /**
     * 中单流出(投资者)
     */
    private BigDecimal f77;

    /**
     * 小单流入(韭菜)
     */
    private BigDecimal f82;
    /**
     * 小单流出(韭菜)
     */
    private BigDecimal f83;
}
