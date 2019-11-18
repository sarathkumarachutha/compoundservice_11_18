package com.cts.service;

import com.cts.model.Customer;

public interface CustomerService {

	public void addCustomer(Customer customer);
	public boolean isCustomerExists(int customerId);
	
}
