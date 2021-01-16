package com.seeha.stock.basic.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultDto<T> {

    private Integer rc;

    private Integer rt;

    private String svr;

    private Integer lt;

    private Integer full;

    private String time;


    private FundDto data;

}
