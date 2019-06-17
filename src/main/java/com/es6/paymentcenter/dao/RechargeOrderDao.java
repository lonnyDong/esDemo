package com.es6.paymentcenter.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.DatabaseShardingBy;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.Sharding;
import org.jfaster.mango.annotation.ShardingBy;
import org.jfaster.mango.annotation.TableShardingBy;
import org.jfaster.mango.sharding.ShardingStrategy;

import com.es6.paymentcenter.bean.RechargeOrder;

/**
 * TODO 增加注释
 * 
 * @author MangoG
 * @version 1.0
 * @since 2017-09-17 11:39:12
 */
@DB(table = "payment_order")
@Results({ @Result(column = "rid", property = "rid"), 
		@Result(column = "version", property = "version"),
		@Result(column = "user_id", property = "userId"),
		@Result(column = "real_name", property = "realName"),
		@Result(column = "product_name", property = "productName"),
		@Result(column = "product_id", property = "productId"),
		@Result(column = "order_id", property = "orderId"),
		@Result(column = "amount", property = "amount"),
		@Result(column = "create_time", property = "createTime"),
		@Result(column = "update_time", property = "updateTime"),
		@Result(column = "remark", property = "remark") })
public interface RechargeOrderDao {
	String COLUMNS = " rid,  version,  user_id,  real_name,  product_name, "
			+ " product_id,  order_id,  amount,   create_time,  update_time,  remark";

	/** 通过订单号更新 */
	@SQL("UPDATE #table SET version = :version+1, user_id = :userId,  real_name = :realName,  product_name = :productName,  "
			+ "product_id = :productId,  amount = :amount,   create_time = :createTime,  update_time = :updateTime,  remark = :remark"
			+ " WHERE order_id = :orderId AND version = :version")
	@Sharding(shardingStrategy = OrderIdShardingStrategy.class)
	void updateByOrderId(@ShardingBy("orderId") RechargeOrder rechargeOrder);

	/** 通过订单号插入 */
	@SQL("INSERT INTO #table(" + COLUMNS
			+ ") VALUES (:rid, :version, :userId, :realName, :productName, :productId, :orderId, :amount, :createTime, :updateTime, :remark)")
	@Sharding(shardingStrategy = OrderIdShardingStrategy.class)
	void save(@ShardingBy("orderId") RechargeOrder rechargeOrder);

	/** uid查询 */
	@SQL("SELECT " + COLUMNS + " FROM #table")
	@Sharding(shardingStrategy = UidShardingStrategy.class)
	List<RechargeOrder> getRechargeOrderRecordsByUid(@DatabaseShardingBy @TableShardingBy Long userId);

	/** uid查询 */
	@SQL("SELECT " + COLUMNS + " FROM #table WHERE  user_id = :1 ")
	@Sharding(shardingStrategy = UidShardingStrategy.class)
	RechargeOrder getByUid(@DatabaseShardingBy @TableShardingBy Long userId);

	/** 订单号查询 */
	@SQL("SELECT " + COLUMNS + " FROM #table WHERE  merchant_order_no = :1 ")
	@Sharding(shardingStrategy = OrderIdShardingStrategy.class)
	RechargeOrder getByOrderID(@DatabaseShardingBy @TableShardingBy String orderId);

	/** 订单号查询 */
	@SQL("SELECT " + COLUMNS + " FROM #table WHERE  merchant_order_no = :1 and user_id =:2 limit 1")
	@Sharding(shardingStrategy = OrderIdShardingStrategy.class)
	RechargeOrder getByOrderIDAndUid(@DatabaseShardingBy @TableShardingBy String orderId, Long userId);

	/** uid查询 */
	@SQL("select COALESCE(SUM(amount),0)  from #table where to_days(create_time) = to_days(now()) and user_id = :1 and recharge_type = 0 and pay_status in(1,0)")
	@Sharding(shardingStrategy = UidShardingStrategy.class)
	BigDecimal getTodaysTotalRechargerAmount(@DatabaseShardingBy @TableShardingBy Long userId);

	class IdShardingStrategy implements ShardingStrategy<String, String> {

		@Override
		public String getDataSourceFactoryName(String rid) {
			return "paymentDataSource_" + rid.substring(0, 1);
		}

		@Override
		public String getTargetTable(String table, String rid) {
			return table + "_" + rid.substring(1, 2);
		}

	}

	class UidShardingStrategy implements ShardingStrategy<Long, Long> {

		@Override
		public String getDataSourceFactoryName(Long userId) {
			String num = String.valueOf((userId / 10) % 4 + 1);
			return "paymentDataSource_" + num;
		}

		@Override
		public String getTargetTable(String table, Long userId) {
			return table + "_" + String.valueOf(userId % 4);
		}

	}

	class OrderIdShardingStrategy implements ShardingStrategy<String, String> {

		@Override
		public String getDataSourceFactoryName(String rid) {
			return "paymentDataSource_" + rid.substring(0, 1);
		}

		@Override
		public String getTargetTable(String table, String rid) {
			return table + "_" + rid.substring(1, 2);
		}

	}

}