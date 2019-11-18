package com.cts.dao;

import com.cts.model.Customer;

public interface CustomerDAO {
	public void addCustomer(Customer customer);
	public boolean isCustomerExists(int customerId);
	
}
