package com.es6.paymentcenter.config;

import java.net.InetAddress;

import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * 
 * @author Lonny
 * @version 1.0.0
 */
@Configuration
public class ElasticsearchConfig {
	
	
//	@Bean
//	public ElasticsearchTemplate elasticsearchTemplate() {
//		return new ElasticsearchTemplate(elasticClent());
//	}
//	
//	@Bean
//	public Client elasticClent() {
//
//		Client client = null;
//		try {
//			client = TransportClient
//					.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.128.132"), 9300));
//
//		} catch (UnknownHostException e) {
//
//			e.printStackTrace();
//		}
//
//		return client;
//	}


	
}
