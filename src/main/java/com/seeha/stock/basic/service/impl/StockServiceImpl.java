package com.seeha.stock.basic.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seeha.stock.basic.components.HttpClientComponent;
import com.seeha.stock.basic.dto.FundDetailDto;
import com.seeha.stock.basic.dto.ResultDto;
import com.seeha.stock.basic.dto.ResultVO;
import com.seeha.stock.basic.entity.FundDetail;
import com.seeha.stock.basic.entity.RequestList;
import com.seeha.stock.basic.mapper.FundDetailMapper;
import com.seeha.stock.basic.mapper.RequestListMapper;
import com.seeha.stock.basic.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private HttpClientComponent clientComponent;

    @Autowired
    private RequestListMapper requestListMapper;

    @Autowired
    private FundDetailMapper fundDetailMapper;

    @Override
    public String getStocksInfo() {
        //String url, HttpMethod method, MultiValueMap<String, String> params
        RequestList request = new RequestList();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);
        request.setUpdateDate(today);
        // 获取请求list
        List<RequestList> requestLists = requestListMapper.selectDataNotProcess(request);

        for (RequestList requestList :requestLists){
            int sleep = (int) (Math.random()*(10-1)+1);
            // process data
            processSingleRequest(requestList);

            try {
                Thread.sleep(sleep*100);
                System.out.println("睡眠："+sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    @Override
    public List<ResultVO> getChatLine() {
        List<ResultVO> resultVOS = new ArrayList<>();

        List<FundDetail> fundDetails = fundDetailMapper.selectAll();
        List<FundDetail> selectStockSum = fundDetailMapper.selectStockSum();
        List<RequestList> requestLists = requestListMapper.selectAll();
        Map<String, List<RequestList>> requestMap = requestLists.stream().collect(Collectors.groupingBy(RequestList::getStockName));
        Map<String, List<FundDetail>> stockMap = fundDetails.stream().collect(Collectors.groupingBy(FundDetail::getStockName));
        Map<String, List<FundDetail>> stockMapSum = selectStockSum.stream().collect(Collectors.groupingBy(FundDetail::getStockName));

        for (FundDetail fundDetail: selectStockSum){
            ResultVO resultVO = new ResultVO();
            String stockName = fundDetail.getStockName();
            resultVO.setTitle(stockName);

            resultVO.setXAxis(stockMap.get(stockName).stream().map(v->v.getStockDate().substring(v.getStockDate().length()-5,v.getStockDate().length())).collect(Collectors.toList()));
            List<List<BigDecimal>> list = new ArrayList<>();
            List<BigDecimal> supper = stockMap.get(stockName).stream().map(v -> v.getSupperSum().divide(new BigDecimal(10000))).collect(Collectors.toList());
            List<BigDecimal> big = stockMap.get(stockName).stream().map(v -> v.getBigSum().divide(new BigDecimal(10000))).collect(Collectors.toList());
            List<BigDecimal> middle = stockMap.get(stockName).stream().map(v -> v.getMiddleSum().divide(new BigDecimal(10000))).collect(Collectors.toList());
            List<BigDecimal> small = stockMap.get(stockName).stream().map(v -> v.getSmallSum().divide(new BigDecimal(10000))).collect(Collectors.toList());
            list.add(supper);
            list.add(big);
            list.add(middle);
            list.add(small);
            resultVO.setSeries(list);

            // sum
            List<BigDecimal> sum = new ArrayList<>();
            sum.add(stockMapSum.get(stockName).get(0).getSupperSum());
            sum.add(stockMapSum.get(stockName).get(0).getBigSum());
            sum.add(stockMapSum.get(stockName).get(0).getMiddleSum());
            sum.add(stockMapSum.get(stockName).get(0).getSmallSum());
            resultVO.setSum(sum);
            // description
            resultVO.setStockDesc(requestMap.get(stockName).get(0).getStockDesc());
            resultVOS.add(resultVO);
        }

        return resultVOS;
    }

    @Override
    public List<ResultVO> getRender() {
        List<ResultVO> resultVOS = new ArrayList<>();
        List<RequestList> requestLists = requestListMapper.selectAll();
        List<FundDetail> fundDetails = fundDetailMapper.selectAll();
        Map<String, List<RequestList>> requestMap = requestLists.stream().collect(Collectors.groupingBy(RequestList::getStockName));
        // 汇总所有股票
        Map<String, List<FundDetail>> stockMap = fundDetails.stream().collect(Collectors.groupingBy(FundDetail::getStockName));
        // 设置返回值
        stockMap.forEach((stockName, stockList) ->{
            List<List<BigDecimal>> list = new ArrayList<>();
            ResultVO resultVO = new ResultVO();
            // 设置标题
            resultVO.setTitle(stockName);
            // 设置横坐标
            resultVO.setXAxis(stockMap.get(stockName).stream().map(v->v.getStockDate().substring(v.getStockDate().length()-5,v.getStockDate().length())).collect(Collectors.toList()));
            // 设置换手率值
            List<BigDecimal> collectTurnoverRate = stockList.stream().map(value -> value.getTurnoverRate()).collect(Collectors.toList());

            // 设置价格
            List<BigDecimal> collectUintPrice = stockList.stream().map(value -> value.getUnitPrice()).collect(Collectors.toList());
            // 获取14天之内，最高与最低换手率在5倍以上的
            List<FundDetail> collect15 = stockList.stream().sorted(Comparator.comparing(FundDetail::getStockDate)).collect(Collectors.toList());
            List<FundDetail> details15 = collect15.subList(0, collect15.size() > 14 ? 14 : collect15.size());
            //求最大值
            BigDecimal max = details15.stream().map(FundDetail::getTurnoverRate).max((x1, x2) -> x1.compareTo(x2)).get();
            //求最小值
            BigDecimal min = details15.stream().map(FundDetail::getTurnoverRate).min((x1, x2) -> x1.compareTo(x2)).get();

            BigDecimal scale = max.divide(min,2,BigDecimal.ROUND_HALF_UP);
            resultVO.setTurnRate(scale);


            list.add(collectTurnoverRate);
            list.add(collectUintPrice);
            resultVO.setSeries(list);
            // 设置描述
            resultVO.setStockDesc(requestMap.get(stockName).get(0).getStockDesc());
            resultVOS.add(resultVO);
        } );

        // 按换手率倍数排序
        List<ResultVO> resultVOList = resultVOS.stream().sorted(Comparator.comparing(ResultVO::getTurnRate).reversed()).collect(Collectors.toList());

        return resultVOList;
    }


    private void processSingleRequest(RequestList requestList){

        HttpMethod method = null;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        // 发送报文
        if ("GET".equals(requestList.getMethod())){
            method = HttpMethod.GET;
        }
        String result = clientComponent.httpClient(requestList.getUrl(), method, params);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        // 解析
        ResultDto resultDto = gson.fromJson(result, ResultDto.class);
        //转换
        List<FundDetail> fundDetails = covert2fundDetail(resultDto, requestList);
        // 保存
        saveStockData(fundDetails,requestList);

    }


    private List<FundDetail> covert2fundDetail(ResultDto resultDto,RequestList requestList){
        List<FundDetail> fundDetailList = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);
        if (resultDto.getData() != null && resultDto.getData().getDiff() != null){
            List<FundDetailDto> detailDtoList = resultDto.getData().getDiff();
            for (FundDetailDto fundDetailDto : detailDtoList ){
                FundDetail fundDetail = new FundDetail();
                fundDetail.setStockCode(requestList.getStockCode());
                fundDetail.setStockName(requestList.getStockName());
                // 超大
                fundDetail.setSupperIn(fundDetailDto.getF64());
                fundDetail.setSupperOut(fundDetailDto.getF65());
                // 大
                fundDetail.setBigIn(fundDetailDto.getF70());
                fundDetail.setBigOut(fundDetailDto.getF71());
                // 中
                fundDetail.setMiddleIn(fundDetailDto.getF76());
                fundDetail.setMiddleOut(fundDetailDto.getF77());
                // 小
                fundDetail.setSmallIn(fundDetailDto.getF82());
                fundDetail.setSmallOut(fundDetailDto.getF83());

                // 价格
                fundDetail.setUnitPrice(fundDetailDto.getF2());
                // 换手率
                fundDetail.setTurnoverRate(fundDetailDto.getF8());
                // 成交量
                fundDetail.setVolume(fundDetailDto.getF5());
                // 主板块
                fundDetail.setPlate(fundDetailDto.getF265());
                // 市流通值
                fundDetail.setCirculation(fundDetailDto.getF21());
                // sum
                fundDetail.setSupperSum(fundDetailDto.getF64().subtract(fundDetailDto.getF65()));
                fundDetail.setBigSum(fundDetailDto.getF70().subtract(fundDetailDto.getF71()));
                fundDetail.setMiddleSum(fundDetailDto.getF76().subtract(fundDetailDto.getF77()));
                fundDetail.setSmallSum(fundDetailDto.getF82().subtract(fundDetailDto.getF83()));
                // date
                fundDetail.setStockDate(today);
                // 回写成功数据
                requestList.setStockPlate(fundDetailDto.getF265());
                requestList.setCirculation(fundDetailDto.getF21());
                requestList.setUpdateDate(today);
                fundDetailList.add(fundDetail);
            }
        }
        return fundDetailList;
    }

    private void saveStockData( List<FundDetail> fundDetailList, RequestList requestList){

        for (FundDetail fundDetail : fundDetailList){
            FundDetail detail = new FundDetail();
            detail.setStockCode(fundDetail.getStockCode());
            detail.setStockDate(fundDetail.getStockDate());
            int countDetailFlag = fundDetailMapper.selectCount(detail);
            // 不存在插入，否则不处理
            if (countDetailFlag < 1){
                fundDetailMapper.insertSelective(fundDetail);
                requestListMapper.updateByPrimaryKeySelective(requestList);
            }

        }

    }
}
