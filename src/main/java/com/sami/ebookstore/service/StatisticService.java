package com.sami.ebookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sami.ebookstore.entity.Customer;
import com.sami.ebookstore.entity.EBookStoreOrder;
import com.sami.ebookstore.model.CustomerOrderStatistic;
import com.sami.ebookstore.model.MonthlyOrderStatistic;

@Service
public class StatisticService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(StatisticService.class);

	private final CustomerService customerService;
	private final OrderService orderService;

	@Autowired
	public StatisticService(CustomerService customerService, OrderService orderService) {
		this.customerService = customerService;
		this.orderService = orderService;
	}

	public CustomerOrderStatistic getCustomerStatistics(Long customerId) {
		List<EBookStoreOrder> customerOrders = orderService.getCustomerOrders(customerId);

		Map<Integer, MonthlyOrderStatistic> statisticMap = new TreeMap<Integer, MonthlyOrderStatistic>();

		for (EBookStoreOrder order : customerOrders) {
			LocalDateTime orderTime = order.getCreateDateTime();
			Integer monthValue = orderTime.getMonthValue();

			MonthlyOrderStatistic mos = statisticMap.get(monthValue);
			if (mos == null) {
				mos = new MonthlyOrderStatistic(monthValue, 0, 0, 0.0);
				statisticMap.put(monthValue, mos);
			}

			Integer bookCount = order.getTotalPurchasedBook() + mos.getTotalBookCount();
			mos.setTotalBookCount(bookCount);

			Integer totalOrderCount = mos.getTotalOrderCount() + 1;
			mos.setTotalOrderCount(totalOrderCount);

			Double totalPurchasedAmount = mos.getTotalPurchasedAmount() + order.getTotalPrice();
			mos.setTotalPurchasedAmount(totalPurchasedAmount);
		}

		List<MonthlyOrderStatistic> mosList = new ArrayList<MonthlyOrderStatistic>(statisticMap.values());
		CustomerOrderStatistic cos = new CustomerOrderStatistic(customerId, mosList);

		logger.info(String.format("Statistics for customerId=%s are calculated", customerId));

		return cos;
	}

	public List<CustomerOrderStatistic> getAllCustomerStatistics() {
		List<Customer> customerList = customerService.getCustomers();

		List<CustomerOrderStatistic> cosList = new ArrayList<CustomerOrderStatistic>();
		for (Customer customer : customerList) {
			CustomerOrderStatistic cos = getCustomerStatistics(customer.getId());
			cosList.add(cos);
		}

		logger.info(String.format("Statistics for all customers are calculated"));

		return cosList;
	}

}
