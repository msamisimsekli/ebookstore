package com.sami.ebookstore.model;

public class ABookOrder {

	private Long bookId;
	private Integer quantity;

	public ABookOrder(Long bookId, Integer quantity) {
		super();
		this.bookId = bookId;
		this.quantity = quantity;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
