package com.seeha.stock.basic.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author redandelion
 */
@Component
public class StockTask {

    @Scheduled(cron = "0/10 * * * * *")
    public void getStockEveryDay(){

        System.out.println("每天执行:" + new Date().toString() );
    }

}
