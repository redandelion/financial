package com.seeha.stock.basic.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "FUND_DETAIL")
public class FundDetail {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(columnDefinition = "fund_detail_id")
    private Long fundDetailId;

    @Column(columnDefinition = "stock_code")
    private String stockCode;

    @Column(columnDefinition = "stock_name")
    private String stockName;

    private BigDecimal supperIn;

    private BigDecimal supperOut;

    private BigDecimal bigIn;

    private BigDecimal bigOut;

    private BigDecimal middleIn;

    private BigDecimal middleOut;

    private BigDecimal smallIn;

    private BigDecimal smallOut;

    private BigDecimal supperSum;

    private BigDecimal bigSum;

    private BigDecimal middleSum;

    private BigDecimal smallSum;

    @Column(columnDefinition = "stock_date")
    private String stockDate;


    /**
     * 成交量
     */
    private BigDecimal unitPrice;


    /**
     * 成交量
     */
    private BigDecimal volume;


    /**
     * 换手率
     */
    private BigDecimal turnoverRate;

    /**
     * 市流通值
     */
    private BigDecimal circulation;

    /**
     * 主板块
     */
    private String plate;
}
