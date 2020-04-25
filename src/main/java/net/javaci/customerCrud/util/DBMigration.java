package net.javaci.customerCrud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DBMigration {

	private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS customer ("
			+ "id int primary key auto_increment, "
			+ "name varchar(30), "
			+ "middleName varchar(30), "
			+ "lastName varchar(30), "
			+ "address varchar(30), "
			+ "birthDate DATE"
	+ ")";
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void migrate() {
		createTable();
		// insertRows();
	}
	
	private void createTable() {
		log.debug("Creating table {}", CREATE_TABLE_SQL);
		jdbcTemplate.execute(CREATE_TABLE_SQL);
	}

	private void insertRows() {
		log.debug("Inserting rows");
		jdbcTemplate.execute("INSERT INTO customer VALUES ("
				+ "1, 'Volkan', '', 'Istek', 'Canakkale Bulvari No 5', CURRENT_TIMESTAMP"
		+ ")");
	}
}
