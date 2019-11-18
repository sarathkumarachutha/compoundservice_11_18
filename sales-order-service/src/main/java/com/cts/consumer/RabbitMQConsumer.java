package com.cts.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cts.model.Customer;
import com.cts.service.CustomerService;

/**
 * 
 * RabbitMq Consumer
 *
 */

@Component
@RabbitListener(queues = "${customer.rabbitmq.queue}")
public class RabbitMQConsumer {
	
	@Autowired
	private CustomerService customerService;
	
	@RabbitHandler
	public void recievedMessage(Customer customer)  {
		try {
			Thread.sleep(5000);
			
			//reading values from rabbirMq sender
			String emailId=customer.getEmail();
			String firstName=customer.getFirstName();
			String lastName=customer.getLastName();
			int customerId=customer.getCustomerId();
			
			//setting values to Customer
			Customer customerDetails=new Customer();
			customerDetails.setCustomerId(customerId);
			customerDetails.setFirstName(firstName);
			customerDetails.setLastName(lastName);
			customerDetails.setEmail(emailId);
			customerService.addCustomer(customerDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


