package com.es6.paymentcenter.utils;

/**
 * @author ash
 */
public class IDGenerator {
	

	/**
	 * 根据uid 生成的分库分表识别的码
	 * @param uid  用户id
	 * @param baseId 全局唯一
	 * @return
	 */
	public static String genBaseId(long uid,Long baseId) {
		String dbInfo = String.valueOf((uid / 10) % 4 + 1);
		String tableInfo = String.valueOf(uid % 4);
		return dbInfo + tableInfo + baseId;
	}
	

	/**
	 * 6位随机数
	 * 
	 * @param args
	 */
	private static String getRandomStr() {
		return (int) ((Math.random() * 9 + 1) * 100000) + "";
	}

}
