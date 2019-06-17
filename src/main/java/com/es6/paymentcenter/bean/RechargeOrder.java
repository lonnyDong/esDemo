package com.es6.paymentcenter.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 
 * 充值订单表
 * 
 * @author Lonny
 * @version 1.0.0
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "pc_payment_order", type = "paymentOrder", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
public class RechargeOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private int rid;

	/** 版本号 */
	private Integer version ;// 版本号默认为0

	/** 用户ID */
	private Long userId;

	/** 用户实名 */
	@Field(store = true, type = FieldType.Keyword)
	private String realName;

	/** 商品名称 **/
	private String productName;

	/** 商品名称 **/
	private String productId;

	@Id
	private String orderId;

	/** 充值金额 **/
	private BigDecimal amount;

	/** 充值发起时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date updateTime;

	/** 备注与附加信息 **/
	private String remark;


}
