package com.sami.ebookstore.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sami.ebookstore.entity.Customer;
import com.sami.ebookstore.exception.EBookStoreException;
import com.sami.ebookstore.exception.ErrorCode;
import com.sami.ebookstore.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerService.class);

	private final CustomerRepository customerRepo;

	@Autowired
	public CustomerService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Transactional
	public Customer saveCustomer(Customer customer) {
		if (customer == null)
			throw new EBookStoreException(ErrorCode.CANNOT_SAVE_NULL_CONTENT);

		logger.info(String.format("Customer %s saved to system", customer));
		return customerRepo.save(customer);
	}

	public Customer getCustomer(Long id) {
		Optional<Customer> customerEntity = customerRepo.findById(id);

		if (!customerEntity.isPresent()) {
			throw new EBookStoreException(ErrorCode.CUSTOMER_NOT_FOUND);
		}

		Customer customer = customerEntity.get();
		logger.info(String.format("Customer %s found in the system", customer));
		return customer;
	}

	public Page<Customer> getCustomers(int pageIndex, int pageSize) {
		Page<Customer> customerList = customerRepo.findAll(PageRequest.of(pageIndex, pageSize));
		logger.info(String.format("For pageIndex=%s pageSize=%s, customerListSize=%s ", pageIndex, pageSize,
				customerList.getNumberOfElements()));
		return customerList;
	}

	public List<Customer> getCustomers() {
		List<Customer> customerList = customerRepo.findAll();
		return customerList;
	}
}
