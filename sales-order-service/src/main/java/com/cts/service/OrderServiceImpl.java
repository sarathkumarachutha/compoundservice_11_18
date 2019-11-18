package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.dao.OrderDAO;
import com.cts.model.Order;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public long createOrder(Order oder) {
		return orderDAO.createOrder(oder);
	}

	
	
}
