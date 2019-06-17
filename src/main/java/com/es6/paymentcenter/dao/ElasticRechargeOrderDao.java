package com.es6.paymentcenter.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.es6.paymentcenter.bean.RechargeOrder;


public interface ElasticRechargeOrderDao extends ElasticsearchRepository<RechargeOrder,String> {

	
	
}
