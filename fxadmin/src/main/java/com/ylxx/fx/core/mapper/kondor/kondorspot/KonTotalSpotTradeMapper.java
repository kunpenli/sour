package com.ylxx.fx.core.mapper.kondor.kondorspot;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ylxx.fx.service.po.Kon_TotalSpotTradeBean;

public interface KonTotalSpotTradeMapper{
	
	//查询页面数据
	public List<Kon_TotalSpotTradeBean> selTotalSpotList(@Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("prcd")String prcd,
			@Param("rpcNo")String rpcNo) throws Exception;
	
}