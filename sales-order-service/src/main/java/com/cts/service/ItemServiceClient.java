package com.cts.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cts.exception.ItemNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ItemServiceClient {
	
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "callItemService_Fallback")
	public String callItemService(String itemName) {
		System.out.println("Getting Item Service details for " + itemName);
		String response = restTemplate
				.exchange("http://localhost:1011/service2/items/{itemname}"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, itemName).getBody();

		System.out.println("Response Received as " + response + " -  " + new Date());

		//return "NORMAL FLOW !!! - Item Name -  " + itemName + " :::  Item Details " + response + " -  " + new Date();
		
		if(response == null || response.equals("")){
			throw new ItemNotFoundException("Item with name:"+itemName +" not found!");

		}
		return response;
	}
	
	@SuppressWarnings("unused")
	private String callItemService_Fallback(String itemName) {
		System.out.println("Student Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly - " + new Date();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
