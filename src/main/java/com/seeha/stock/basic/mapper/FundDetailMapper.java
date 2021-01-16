package com.seeha.stock.basic.mapper;

import com.seeha.stock.basic.entity.FundDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FundDetailMapper extends Mapper<FundDetail> {

    /**
     * Sum Stock
     * @return
     */
    List<FundDetail> selectStockSum();


    /**
     * OrderBySupper
     * @return
     */
    List<FundDetail> selectStockAllOrderBySupper();
}
