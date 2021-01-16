package com.seeha.stock.basic.mapper;

import com.seeha.stock.basic.entity.RequestList;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RequestListMapper extends Mapper<RequestList> {

    List<RequestList> selectDataNotProcess(RequestList requestList);
}
