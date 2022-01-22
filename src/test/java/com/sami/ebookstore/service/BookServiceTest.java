package com.sami.ebookstore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sami.ebookstore.entity.Book;
import com.sami.ebookstore.exception.EBookStoreException;
import com.sami.ebookstore.model.UpdateBookQuantityRequest;
import com.sami.ebookstore.repository.BookRepository;
import com.sami.ebookstore.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void bookShouldBeSaved() {
		Book book = new Book("bookName", 3, 30.0);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		Book savedBook = bookService.saveBook(book);
		Assert.assertNotNull(savedBook);
	}

	@Test(expected = EBookStoreException.class)
	public void bookSaveFailDueToNull() {
		bookService.saveBook(null);
	}

	@Test
	public void bookShouldBeRetrieved() {
		Book book = new Book("bookName", 3, 30.0);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
		Book retrievedBook = bookService.getBook(book.getId());
		Assert.assertNotNull(retrievedBook);
	}

	@Test(expected = EBookStoreException.class)
	public void getBookFailDueToNonExistingBookId() {
		bookService.getBook(5235434L);
	}

	@Test
	public void bookQuantityShouldBeChanged() {
		Book book = new Book(11L, "bookName", 3, 30.0);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

		Book savedBook = bookService.saveBook(book);

		UpdateBookQuantityRequest ubqr = new UpdateBookQuantityRequest(4, savedBook.getId());
		bookService.updateBookQuantity(ubqr);
	}

	@Test(expected = EBookStoreException.class)
	public void bookQuantityChangeFailDueLackOfStock() {

		Book book = new Book(11L, "bookName", 3, 30.0);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

		Book savedBook = bookService.saveBook(book);

		UpdateBookQuantityRequest ubqr = new UpdateBookQuantityRequest(-4, savedBook.getId());
		bookService.updateBookQuantity(ubqr);
	}

}
