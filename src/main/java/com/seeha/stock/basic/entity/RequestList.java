package com.seeha.stock.basic.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "REQUEST_LIST")
public class RequestList {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long requestListId;
    @Column(columnDefinition = "stock_code")
    private String stockCode;
    @Column(columnDefinition = "stock_name")
    private String stockName;

    private String url;

    private String kurl;
    @Column(columnDefinition = "body_param")
    private String bodyParam;
    @Column(columnDefinition = "header_param")
    private String headerParam;

    private String method;
    @Column(columnDefinition = "update_date")
    private String updateDate;

    @Column(columnDefinition = "stock_desc")
    private String stockDesc;

    //主板块
    private String stockPlate;

    /**
     * 市流通值
     */
    private BigDecimal circulation;
}
