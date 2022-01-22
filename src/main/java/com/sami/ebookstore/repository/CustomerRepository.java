package com.sami.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sami.ebookstore.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
