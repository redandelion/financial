package com.seeha.stock.basic.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "KLINE_INTERFACE")
public class KlineInterface {

    private Long klineInterfaceId;
    private Long requestListId;
    // 代码
    private String stockCode;
    // 日期
    private String date;
    // 开盘
    private BigDecimal start;
    // 最高
    private BigDecimal kmax;
    // 最低
    private BigDecimal kmin;
    // 收盘
    private BigDecimal end;
    // 成交量
    private BigDecimal volume;
    // 总金额
    private BigDecimal totalPrice;
    // 振幅
    private BigDecimal swing;
    // 涨跌
    private BigDecimal updown;
    // 涨跌
    private BigDecimal updownSwing;

    // 换手率
    private BigDecimal turnoverRate;

}
