package com.sami.ebookstore.model;

public class UpdateBookQuantityRequest {

	private Integer updateQuantity; // if sell (-), if addition to storage (+)
	private Long bookId;

	public UpdateBookQuantityRequest(Integer updateQuantity, Long bookId) {
		super();
		this.updateQuantity = updateQuantity;
		this.bookId = bookId;
	}

	public Integer getUpdateQuantity() {
		return updateQuantity;
	}

	public void setUpdateQuantity(Integer updateQuantity) {
		this.updateQuantity = updateQuantity;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
}
