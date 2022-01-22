package com.sami.ebookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sami.ebookstore.entity.Book;
import com.sami.ebookstore.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book) {
		return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getBooks(@RequestParam int pageIndex, @RequestParam int pageSize) {
		return ResponseEntity.ok(bookService.getBooks(pageIndex, pageSize));
	}
}
