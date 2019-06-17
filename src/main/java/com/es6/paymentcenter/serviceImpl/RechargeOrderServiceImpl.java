package com.es6.paymentcenter.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.es6.paymentcenter.bean.RechargeOrder;
import com.es6.paymentcenter.dao.ElasticRechargeOrderDao;
import com.es6.paymentcenter.dao.RechargeOrderDao;
import com.es6.paymentcenter.utils.GenerateChineseWords;
import com.es6.paymentcenter.utils.IdWorker;

/**
 * 
 * @author Lonny
 *
 */

@RestController
@RequestMapping("/payment")
public class RechargeOrderServiceImpl implements RechargeOrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RechargeOrderServiceImpl.class);

	@Autowired
	RechargeOrderDao rechargeOrderDao;

//	@Autowired
//	ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	ElasticRechargeOrderDao elasticRechargeOrderDao;

	private static String genBaseId(long uid, Long baseId) {
		String dbInfo = String.valueOf((uid / 10) % 4 + 1);
		String tableInfo = String.valueOf(uid % 4);
		System.out.println("db:"+dbInfo+" table: "+tableInfo);
		return dbInfo + tableInfo + baseId;
	}

	private static String getRandomStr() {
		return (int) ((Math.random() * 9 + 1) * 100000) + "";
	}

	private String getOrderID(Long uid) {
		return genBaseId(uid, IdWorker.getWorkID());
	}

	private RechargeOrder getRechargeOrder() {
		int nextInt = RandomUtils.nextInt();
		System.out.println();
		RechargeOrder build;
		String namestring = null;
		try {
			namestring = new String(GenerateChineseWords.getName().getBytes(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		build = RechargeOrder.builder()
				.amount(new BigDecimal(RandomUtils.nextDouble() + ""))
				.createTime(new Date())
				.orderId(getOrderID(new Long(nextInt)))
				.productId(IdWorker.getWorkID() + "")
				.productName("ebook")
				.realName(namestring)
				.remark("mark")
				.userId(new Long(nextInt))
				.updateTime(new Date())
				.version(0)
				.build();
		return build;

	}

	@Override
	public void updateByOrderId(RechargeOrder rechargeOrder) {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public void save(RechargeOrder rechargeOrder) {
		RechargeOrder rechargeOrder2 = getRechargeOrder();
		rechargeOrderDao.save(rechargeOrder2);
		saveDataToEs(rechargeOrder2);
	}


	private void saveDataToEs(RechargeOrder rechargeOrder) {
		RechargeOrder save = elasticRechargeOrderDao.save(rechargeOrder);
		LOGGER.info("elastic save: " + save);
	}
	
	@Override
	public List<RechargeOrder> getRechargeOrderRecordsByUid(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RechargeOrder getByUid(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RechargeOrder getByOrderID(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RechargeOrder getByOrderIDAndUid(String orderId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getTodaysTotalRechargerAmount(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
