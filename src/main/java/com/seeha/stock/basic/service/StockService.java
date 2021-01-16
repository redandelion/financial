package com.seeha.stock.basic.service;

import com.seeha.stock.basic.dto.ResultVO;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface StockService {

   String getStocksInfo();

   List<ResultVO> getChatLine();

}
