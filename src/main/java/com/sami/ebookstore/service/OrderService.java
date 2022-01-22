package com.sami.ebookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sami.ebookstore.entity.Book;
import com.sami.ebookstore.entity.Customer;
import com.sami.ebookstore.entity.EBookStoreOrder;
import com.sami.ebookstore.exception.EBookStoreException;
import com.sami.ebookstore.exception.ErrorCode;
import com.sami.ebookstore.model.ABookOrder;
import com.sami.ebookstore.model.OrderRequest;
import com.sami.ebookstore.model.UpdateBookQuantityRequest;
import com.sami.ebookstore.repository.OrderRepository;

@Service
public class OrderService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderService.class);

	private final OrderRepository orderRepo;

	private final BookService bookService;
	private final CustomerService customerService;

	@Autowired
	public OrderService(OrderRepository orderRepo, BookService bookService, CustomerService customerService) {
		this.orderRepo = orderRepo;
		this.bookService = bookService;
		this.customerService = customerService;
	}

	@Transactional
	public EBookStoreOrder saveOrder(OrderRequest request) {

		List<ABookOrder> bookOrders = request.getBookOrders();

		if (bookOrders.isEmpty())
			throw new EBookStoreException(ErrorCode.ZERO_ITEM_IN_CART);

		Customer customer = customerService.getCustomer(request.getCustomerId());

		EBookStoreOrder order = new EBookStoreOrder();
		Double cartTotal = 0.0;
		Integer purchasedBookCount = 0;
		List<Book> books = new ArrayList<Book>();

		for (ABookOrder aBookOrder : bookOrders) {
			Book book = bookService.getBook(aBookOrder.getBookId());

			Integer bookQuantity = aBookOrder.getQuantity();

			if (bookQuantity <= 0)
				throw new EBookStoreException(ErrorCode.CANNOT_ORDER_THIS_AMOUNT);

			Double currentBookPrice = bookQuantity * book.getPrice();
			cartTotal += currentBookPrice;
			books.add(book);
			purchasedBookCount += bookQuantity;

			UpdateBookQuantityRequest ubqr = new UpdateBookQuantityRequest(-1 * bookQuantity, aBookOrder.getBookId());
			bookService.updateBookQuantity(ubqr);
		}

		order.setTotalPrice(cartTotal);
		order.setBooks(books);
		order.setCustomerId(customer.getId());
		order.setTotalPurchasedBook(purchasedBookCount);

		logger.info(String.format("Order=%s is going to be saved to system", order));
		orderRepo.save(order);

		return order;
	}

	public EBookStoreOrder getOrder(Long orderId) {
		Optional<EBookStoreOrder> orderEntity = orderRepo.findById(orderId);

		if (!orderEntity.isPresent()) {
			throw new EBookStoreException(ErrorCode.ORDER_NOT_FOUND);
		}

		EBookStoreOrder order = orderEntity.get();
		logger.info(String.format("Order %s found in the repo", order));
		return order;
	}

	public List<EBookStoreOrder> getCustomerOrders(Long customerId) {

		customerService.getCustomer(customerId);// might throw an error if id not present
		List<EBookStoreOrder> customerOrders = orderRepo.findByCustomerId(customerId);

		logger.info(String.format("CustomerId=%s has %s orders", customerId, customerOrders.size()));

		return customerOrders;
	}

	public List<EBookStoreOrder> getOrderByDate(LocalDateTime start, LocalDateTime end) {
		List<EBookStoreOrder> customerOrders = orderRepo.findByCreateDateTimeBetween(start, end);

		logger.info(
				String.format("Orders within %s - %s is listed and orderSize=%s", start, end, customerOrders.size()));

		return customerOrders;
	}
}
