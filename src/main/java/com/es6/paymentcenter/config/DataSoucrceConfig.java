package com.es6.paymentcenter.config;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.jfaster.mango.datasource.DataSourceFactory;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.plugin.page.MySQLPageInterceptor;
import org.jfaster.mango.plugin.spring.MangoDaoScanner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 
 * @author Lonny
 * @version 1.0.0
 */
@Configuration
public class DataSoucrceConfig {

	@Bean
	public MangoDaoScanner mangoDaoScanner() {
		MangoDaoScanner mangoDaoScanner = new MangoDaoScanner();
		mangoDaoScanner.setPackages(Arrays.asList("com.es6.paymentcenter.dao"));
		return mangoDaoScanner;
	}

	@Bean
	public Mango mango() {
		Mango mango = Mango.newInstance();
		List<DataSourceFactory> list = new ArrayList<>();
		list.add(dsf1());
		list.add(dsf2());
		list.add(dsf3());
		list.add(dsf4());
		list.add(dsfcommon());
		mango.setDataSourceFactories(list);
		mango.addInterceptor(new MySQLPageInterceptor());
		return mango;
	}

	@Bean(value = "dsf1")
	public SimpleDataSourceFactory dsf1() {
		SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory("paymentDataSource_1",
				oneDataSource());
		return simpleDataSourceFactory;
	}

	@Bean(value = "dsf2")
	public SimpleDataSourceFactory dsf2() {
		SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory("paymentDataSource_2",
				twoDataSource());
		return simpleDataSourceFactory;
	}

	@Bean(value = "dsf3")
	public SimpleDataSourceFactory dsf3() {
		SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory("paymentDataSource_3",
				threeDataSource());
		return simpleDataSourceFactory;
	}

	@Bean(value = "dsf4")
	public SimpleDataSourceFactory dsf4() {
		SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory("paymentDataSource_4",
				fourDataSource());
		return simpleDataSourceFactory;
	}

	@Bean(value = "dsfcommon")
	public SimpleDataSourceFactory dsfcommon() {
		SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory("commonDataSource",
				commonDataSource());
		return simpleDataSourceFactory;
	}

	@Bean(name = "oneDataSource")
	@Qualifier("oneDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.one")
	public DataSource oneDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "twoDataSource")
	@Qualifier("twoDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.two")
	public DataSource twoDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "threeDataSource")
	@Qualifier("threeDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.three")
	public DataSource threeDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "fourDataSource")
	@Qualifier("fourDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.four")
	public DataSource fourDataSource() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "commonDataSource")
	@Qualifier("commonDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.common")
	public DataSource commonDataSource() {
		return DataSourceBuilder.create().build();
	}

}
