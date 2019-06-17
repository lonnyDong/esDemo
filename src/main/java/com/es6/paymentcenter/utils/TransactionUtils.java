package com.es6.paymentcenter.utils;

import org.jfaster.mango.transaction.Transaction;
import org.jfaster.mango.transaction.TransactionFactory;
import org.jfaster.mango.transaction.TransactionIsolationLevel;


/**
 * @author Heather
 */
public class TransactionUtils {
	
	public static Transaction newTransaction(Boolean isDisk, Long userId) {
		String dataSource = null;
		if(isDisk) {
			dataSource = getTransactionDataSource(userId);
		}else{
			dataSource = getCommonTransactionDataSource();
		}
		return TransactionFactory.newTransaction(dataSource, TransactionIsolationLevel.DEFAULT);
	}
		
	

	
	/**
	 * 获取默认库
	 */
	public static String getCommonTransactionDataSource() {
		return "commonDataSource";
	}
	
    /**
     * 根据用户id选择库
     * 分8库
     */
    public static String getTransactionDataSource(Long userId) {
    	return "paymentDataSource_" + ((userId / 10) % 4 + 1);
    }

}