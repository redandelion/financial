package com.seeha.stock.basic.mapper;

import com.seeha.stock.basic.entity.FundDetail;
import com.seeha.stock.basic.entity.Kline;
import com.seeha.stock.basic.entity.KlineInterface;
import com.seeha.stock.basic.entity.RequestList;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface KlineInterfaceMapper extends Mapper<KlineInterface> {


    /**
     * 删除原数据
     * @return
     */
    int deleteAll();
}
