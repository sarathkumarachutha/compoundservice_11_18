package com.cts.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cts.model.Customer;

@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public void addCustomer(Customer customer) {
		String sql = "INSERT INTO CUSTOMER_SOS ( cust_id,cust_first_name,cust_last_name,cust_email) values (?, ?, ?,?)";
		jdbcTemplate.update(sql,customer.getCustomerId(),customer.getFirstName(),customer.getLastName(),customer.getEmail());
	}

	@Override
	public boolean isCustomerExists(int customerId) {
		boolean result = false;
		String sql = "SELECT count(1) FROM CUSTOMER_SOS where cust_id=?";
		int count = jdbcTemplate.queryForObject(
	                        sql, new Object[] { customerId }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}
}
