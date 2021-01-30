package com.seeha.stock.basic.dto;

import lombok.Data;

import java.util.List;

@Data
public class FundDto {
    private Integer total;
    private List<FundDetailDto> diff;

    // k线
    private List<String> klines;

}
