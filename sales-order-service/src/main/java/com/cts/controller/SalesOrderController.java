package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.exception.CustomerNotFoundException;
import com.cts.model.Item;
import com.cts.model.Order;
import com.cts.service.CustomerService;
import com.cts.service.ItemServiceClient;
import com.cts.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import static com.cts.controller.APIConstants.CREATE_ORDER;

import java.io.IOException;


@RestController
public class SalesOrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemServiceClient itemServiceClient;
	
	
	
	@ApiOperation(value = "Create new Item")
	@PostMapping(value = CREATE_ORDER,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> createOrder(@RequestBody Order order) {
		
		//validate customer
		if(!customerService.isCustomerExists(order.getCustomerId())){
			throw new CustomerNotFoundException("Customer with Id:"+order.getCustomerId() +" not found!");
		}
		
		//validate itemname
		String itemResponse=itemServiceClient.callItemService(order.getItemName());
		ObjectMapper objectMapper = new ObjectMapper();
        Item item=null;
		try {
			item = objectMapper.readValue(itemResponse, Item.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		
		long orderId=orderService.createOrder(order);
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
		
}
