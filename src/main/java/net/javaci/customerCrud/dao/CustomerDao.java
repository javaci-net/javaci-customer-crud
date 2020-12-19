package net.javaci.customerCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaci.customerCrud.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
