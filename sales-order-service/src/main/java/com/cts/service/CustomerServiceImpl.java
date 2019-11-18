package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.dao.CustomerDAO;
import com.cts.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public void addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);		
	}

	@Override
	public boolean isCustomerExists(int customerId) {
		return customerDAO.isCustomerExists(customerId);
	}
	
}
