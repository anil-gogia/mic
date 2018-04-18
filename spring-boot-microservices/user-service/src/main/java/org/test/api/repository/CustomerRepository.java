package org.test.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.test.api.services.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
