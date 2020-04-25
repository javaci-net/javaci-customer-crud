package net.javaci.customerCrud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.javaci.customerCrud.model.Customer;

@Component
@Slf4j
public class CustomerDaoJdbcImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_ALL = "SELECT id, name, middleName,lastName, address, birthDate FROM customer";

	private static final String SQL_INSERT = "INSERT INTO customer (name, middleName,lastName, address, birthDate) VALUES (?,?,?,?,?)";

	@Override
	public List<Customer> findAll() {
		log.debug("Finding all");
		return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	@Override
	public Customer insert(Customer newCustomer) {
		log.debug("Inserting ...");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
			connection -> createCustomerInsertPs(newCustomer, connection), 
			keyHolder
		);
		newCustomer.setId(keyHolder.getKey().longValue());
		log.debug("Insert successful {}", newCustomer);
		return newCustomer;
	}

	private PreparedStatement createCustomerInsertPs(Customer newCustomer, Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
		ps.setString(1, newCustomer.getName());
		ps.setString(2, newCustomer.getMiddleName());
		ps.setString(3, newCustomer.getLastName());
		ps.setString(4, newCustomer.getAddress());
		ps.setDate(5, Date.valueOf(newCustomer.getBirthDate()));
		return ps;
	}

	
}
