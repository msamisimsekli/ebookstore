package com.sami.ebookstore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class EBookStoreExceptionHandler {

	@ExceptionHandler(EBookStoreException.class)
	public final ResponseEntity<EBookStoreException> handleException(EBookStoreException ex, WebRequest request) {
		return prepareResponse(ex);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Exception> handleException(Exception ex) {
		return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus()).body(ex);
	}

	private static ResponseEntity<EBookStoreException> prepareResponse(EBookStoreException exception) {
		return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(exception);
	}
}