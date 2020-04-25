package net.javaci.customerCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaci.customerCrud.util.DBMigration;

@SpringBootApplication
public class JavaciCustomerCrudApplication implements CommandLineRunner {

	@Autowired
	private DBMigration dbMigration;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaciCustomerCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbMigration.migrate();
	}

}
