package com.sami.ebookstore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sami.ebookstore.entity.Customer;
import com.sami.ebookstore.exception.EBookStoreException;
import com.sami.ebookstore.repository.CustomerRepository;
import com.sami.ebookstore.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void customerShouldBeSaved() {
		Customer customer = new Customer(1L, "customerName", "customer@mail.com", "address");
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		Customer savedCustomer = customerService.saveCustomer(customer);
		Assert.assertNotNull(savedCustomer);
	}

	@Test(expected = EBookStoreException.class)
	public void customerSaveFailDueToNull() {
		customerService.saveCustomer(null);
	}

	@Test
	public void customerShouldBeRetrieved() {
		Customer customer = new Customer(1L, "customerName", "customer@mail.com", "address");
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
		Customer retrievedCustomer = customerService.getCustomer(customer.getId());
		Assert.assertNotNull(retrievedCustomer);
	}

	@Test(expected = EBookStoreException.class)
	public void getCustomerFailDueToNonExistingCustomerId() {
		customerService.getCustomer(5235434L);
	}
}
