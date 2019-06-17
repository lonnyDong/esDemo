package com.es6.paymentcenter.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import com.es6.paymentcenter.bean.RechargeOrder;

public interface RechargeOrderService {

	/** 通过订单号更新 */
	void updateByOrderId(RechargeOrder rechargeOrder);

	/** 通过订单号插入 */
	void save(RechargeOrder rechargeOrder);

	/** uid查询列表 */
	List<RechargeOrder> getRechargeOrderRecordsByUid(Long userId);

	/** uid查询 */
	RechargeOrder getByUid(Long userId);

	/** 订单号查询 */
	RechargeOrder getByOrderID(String orderId);

	/** 订单号查询 */
	RechargeOrder getByOrderIDAndUid(String orderId, Long userId);

	/** uid查询 */
	BigDecimal getTodaysTotalRechargerAmount(Long userId);
}
