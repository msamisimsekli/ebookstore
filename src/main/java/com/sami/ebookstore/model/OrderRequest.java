package com.sami.ebookstore.model;

import java.util.List;

public class OrderRequest {

	private Long customerId;
	private List<ABookOrder> bookOrders;
	
	public OrderRequest() {
		
	}

	public OrderRequest(Long customerId, List<ABookOrder> bookOrders) {
		super();
		this.customerId = customerId;
		this.bookOrders = bookOrders;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<ABookOrder> getBookOrders() {
		return bookOrders;
	}

	public void setBookOrders(List<ABookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}
}
