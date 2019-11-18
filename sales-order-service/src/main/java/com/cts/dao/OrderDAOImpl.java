package com.cts.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.model.Order;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO{

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public long createOrder(Order oder) {
		
		String sql = "INSERT INTO SALES_ORDER (order_date,cust_id,order_desc,total_price) values (?, ?, ?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		
		long totalprice=0;
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				String orderDate=oder.getOrderDate();
				//ps.setDate(1, (Date) oder.getOrderDate());
				ps.setString(1, orderDate);
				ps.setInt(2, 123);
				ps.setString(3, oder.getOrderDescription());	
				ps.setLong(4,totalprice);
				return ps;
			}
		}, holder);
		long orderId= holder.getKey().longValue();
		
		//insert order_line_item
		String ORDER_LINE_ITEM_INSERT_QUERY = "INSERT INTO ORDER_LINE_ITEM ( item_name,item_quantity,order_id) values (?, ?, ?)";
		
		jdbcTemplate.update(ORDER_LINE_ITEM_INSERT_QUERY,"",oder.getQuantity(),orderId);
		return orderId;
		
	}

	
}
