package com.seeha.stock.basic.api;

import com.seeha.stock.basic.dto.ResultVO;
import com.seeha.stock.basic.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/pull/list")
    public String list(){
//        String url = "http://push2.eastmoney.com/api/qt/ulist.np/get?fltt=2&secids=0.002982&fields=f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f64,f65,f70,f71,f76,f77,f82,f83,f164,f166,f168,f170,f172,f252,f253,f254,f255,f256,f124,f6,f278,f279,f280,f281,f282";
//        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        //params.add("fltt","2");
        //params.add("secids","0.002982");
        //params.add("query","STYPE%3AEQB%3BEXCHANGE%3ACNSESZ");
        //params.add("fields","f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f64,f65,f70,f71,f76,f77,f82,f83,f164,f166,f168,f170,f172,f252,f253,f254,f255,f256,f124,f6,f278,f279,f280,f281,f282");
//        params.add("count","100000");

        stockService.getStocksInfo();
        return "success";
    }

    @GetMapping("/chats/list")
    private List<ResultVO> lineChats(HttpServletResponse response){

        List<ResultVO> chatLine = stockService.getChatLine();
        response.addHeader("Access-Control-Allow-Origin","*");
        return chatLine;
    }

}
