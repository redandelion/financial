package com.seeha.stock.basic.components;

import com.seeha.stock.basic.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author redandelion
 */
@Component
public class StockTask {

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 35 18,20,22 ? * MON-FRI")
    //@Scheduled(cron = "0 0/15 * * * *")
    public void getStockEveryDay(){

        stockService.getStocksInfo();
        System.out.println("每天执行:" + new Date().toString() );

    }

}
