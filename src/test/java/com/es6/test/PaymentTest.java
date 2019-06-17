package com.es6.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.es6.paymentcenter.bean.RechargeOrder;
import com.es6.paymentcenter.serviceImpl.RechargeOrderService;
@RunWith(SpringRunner.class)
@ComponentScan("com.es6")
@SpringBootTest(classes= PaymentTest.class)
@WebAppConfiguration
@ContextConfiguration
public class PaymentTest {
	
	@Autowired
	RechargeOrderService rechargeOrderService;
	
	@Test
	public void mysave() {
		RechargeOrder rechargeOrder = new RechargeOrder();
		rechargeOrderService.save(rechargeOrder);

	} 

}
