package com.seeha.stock.basic.mapper;

import com.seeha.stock.basic.entity.FundDetail;
import com.seeha.stock.basic.entity.Kline;
import com.seeha.stock.basic.entity.RequestList;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface KlineMapper extends Mapper<Kline> {

    /**
     * 获取股票k线信息
     * @param request
     * @return
     */
    List<FundDetail> selectKline(RequestList request);

    /**
     * 批量插入
     * @param klineList
     */
    Integer batchInsert(List<Kline> klineList);
}
