package com.sami.ebookstore.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sami.ebookstore.entity.Book;
import com.sami.ebookstore.entity.Customer;
import com.sami.ebookstore.entity.EBookStoreOrder;
import com.sami.ebookstore.model.CustomerOrderStatistic;
import com.sami.ebookstore.model.MonthlyOrderStatistic;
import com.sami.ebookstore.service.BookService;
import com.sami.ebookstore.service.CustomerService;
import com.sami.ebookstore.service.OrderService;
import com.sami.ebookstore.service.StatisticService;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceTest {

	@InjectMocks
	private StatisticService statisticService;

	@Mock
	private OrderService orderService;

	@Mock
	private CustomerService customerService;

	@Mock
	private BookService bookService;

	@Test
	public void allCustomerStatisticsShouldBeRetrieved() {

		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1L, "cName", "cMail", "cAddress"));
		when(customerService.getCustomers()).thenReturn(customerList);

		List<EBookStoreOrder> customerOrders = new ArrayList<EBookStoreOrder>();
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("bName", 10, 10.0));
		LocalDateTime now = LocalDateTime.now();
		customerOrders.add(new EBookStoreOrder(null, now, 1L, 10.0, books, 2));

		when(orderService.getCustomerOrders(1L)).thenReturn(customerOrders);

		List<CustomerOrderStatistic> statistics = statisticService.getAllCustomerStatistics();
		Assert.assertTrue(1 == statistics.size());

		CustomerOrderStatistic cos = statistics.get(0);

		List<MonthlyOrderStatistic> customerStatistics = cos.getCustomerStatistics();
		Assert.assertTrue(1 == customerStatistics.size());

		MonthlyOrderStatistic mos = customerStatistics.get(0);

		Assert.assertTrue(now.getMonthValue() == mos.getMonth());
		Assert.assertTrue(1 == mos.getTotalOrderCount());
		Assert.assertTrue(2 == mos.getTotalBookCount());
		Assert.assertTrue(10.0 == mos.getTotalPurchasedAmount());
	}
}
