package com.sami.ebookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sami.ebookstore.entity.Customer;
import com.sami.ebookstore.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
		return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Customer>> getCustomers(@RequestParam int pageIndex, @RequestParam int pageSize) {
		return ResponseEntity.ok(customerService.getCustomers(pageIndex, pageSize));
	}
}
