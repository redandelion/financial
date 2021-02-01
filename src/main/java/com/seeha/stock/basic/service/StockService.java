package com.seeha.stock.basic.service;

import com.seeha.stock.basic.dto.ResultVO;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface StockService {

   String getStocksInfo();

   List<ResultVO> getChatLine();

   /**
    * 换手率对比
    * @return
    */
   List<ResultVO> getRender();

   /**
    * 获取K线信息
    * @param todayFlag
    * @return
    */
   String getStocksKlineInfo(boolean todayFlag);
}
