package com.sami.ebookstore.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sami.ebookstore.entity.EBookStoreOrder;
import com.sami.ebookstore.model.OrderRequest;
import com.sami.ebookstore.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("newOrder")
	public ResponseEntity<EBookStoreOrder> saveOrder(@RequestBody @Valid OrderRequest orderRequest) {
		return new ResponseEntity<EBookStoreOrder>(orderService.saveOrder(orderRequest), HttpStatus.OK);
	}

	@GetMapping("getCustomerOrder")
	public ResponseEntity<List<EBookStoreOrder>> getCustomerOrders(@RequestParam Long customerId) {
		return new ResponseEntity<List<EBookStoreOrder>>(orderService.getCustomerOrders(customerId), HttpStatus.OK);
	}

	@GetMapping("getOrder")
	public ResponseEntity<EBookStoreOrder> getOrder(@RequestParam Long orderId) {
		return new ResponseEntity<EBookStoreOrder>(orderService.getOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("getOrderBetweenDate")
	public ResponseEntity<List<EBookStoreOrder>> getOrder(@RequestParam LocalDateTime start,
			@RequestParam LocalDateTime end) {
		return new ResponseEntity<List<EBookStoreOrder>>(orderService.getOrderByDate(start, end), HttpStatus.OK);
	}
}
