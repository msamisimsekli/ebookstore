package com.sami.ebookstore.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	INTERNAL_SERVER_ERROR(800, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
	FIELD_VALIDATION_ERROR(801, "Field validation error.", HttpStatus.BAD_REQUEST),
	CANNOT_SAVE_NULL_CONTENT(802, "Cannot save null content", HttpStatus.BAD_REQUEST),
	CANNOT_ORDER_THIS_AMOUNT(803, "Cannot order non positive amount of book", HttpStatus.BAD_REQUEST),
	ZERO_ITEM_IN_CART(804, "Cannot order without having an item in shopping cart", HttpStatus.BAD_REQUEST),
	CUSTOMER_NOT_FOUND(805, "Customer was not found", HttpStatus.BAD_REQUEST),
	BOOK_NOT_FOUND(806, "Book was not found", HttpStatus.BAD_REQUEST),
	BOOK_NOT_AVAIBLE(807, "Book not available in stock", HttpStatus.BAD_REQUEST),
	ORDER_NOT_FOUND(808, "Order not found", HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(809, "User not found", HttpStatus.BAD_REQUEST);

	private ErrorCode(int code, String msg, HttpStatus httpStatus) {
		this.errorCode = code;
		this.errorMsg = msg;
		this.httpStatus = httpStatus;
	}

	private int errorCode;
	private String errorMsg;
	private HttpStatus httpStatus;

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}