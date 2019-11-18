package com.cts.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(rs.getInt("cust_id"));
		customer.setEmail(rs.getString("cust_email"));
		customer.setFirstName(rs.getString("cust_first_name"));
		customer.setLastName(rs.getString("cust_last_name"));
		return customer;
	}

}
