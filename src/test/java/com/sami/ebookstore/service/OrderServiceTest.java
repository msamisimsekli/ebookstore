package com.sami.ebookstore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import com.sami.ebookstore.exception.EBookStoreException;
import com.sami.ebookstore.model.ABookOrder;
import com.sami.ebookstore.model.OrderRequest;
import com.sami.ebookstore.repository.OrderRepository;
import com.sami.ebookstore.service.BookService;
import com.sami.ebookstore.service.CustomerService;
import com.sami.ebookstore.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private CustomerService customerService;

	@Mock
	private BookService bookService;

	@Test
	public void orderShouldBeSaved() {
		OrderRequest orderRequest = createOrderRequest(false);

		Customer customer = new Customer(1L, "cName", "cMail", "cAdress");
		when(customerService.getCustomer(any(Long.class))).thenReturn(customer);

		Book book = new Book("bookName", 10, 30.0);
		when(bookService.getBook(any(Long.class))).thenReturn(book);

		EBookStoreOrder order = orderService.saveOrder(orderRequest);
		Assert.assertNotNull(order);
	}

	@Test(expected = EBookStoreException.class)
	public void orderSaveFailDueToZeroItemInCart() {

		OrderRequest orderRequest = createOrderRequest(true);
		orderService.saveOrder(orderRequest);
	}

	private static OrderRequest createOrderRequest(boolean isEmpty) {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setCustomerId(1L);

		List<ABookOrder> purchasedBooks = new ArrayList<ABookOrder>();

		if (!isEmpty)
			purchasedBooks.add(new ABookOrder(3L, 3));

		orderRequest.setBookOrders(purchasedBooks);

		return orderRequest;
	}
}
