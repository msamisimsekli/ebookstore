package com.sami.ebookstore.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sami.ebookstore.entity.Book;
import com.sami.ebookstore.exception.EBookStoreException;
import com.sami.ebookstore.exception.ErrorCode;
import com.sami.ebookstore.model.UpdateBookQuantityRequest;
import com.sami.ebookstore.repository.BookRepository;

@Service
public class BookService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BookService.class);

	private final BookRepository bookRepo;

	@Autowired
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Transactional
	public Book saveBook(Book book) {
		if (book == null)
			throw new EBookStoreException(ErrorCode.CANNOT_SAVE_NULL_CONTENT);

		logger.info(String.format("Book %s saved to repo", book));
		return bookRepo.save(book);
	}

	public Book getBook(Long id) {
		Optional<Book> bookEntity = bookRepo.findById(id);

		if (!bookEntity.isPresent()) {
			throw new EBookStoreException(ErrorCode.BOOK_NOT_FOUND);
		}

		Book book = bookEntity.get();
		logger.info(String.format("Book %s found in the repo", book));
		return book;

	}

	public Page<Book> getBooks(int pageIndex, int pageSize) {
		Page<Book> bookList = bookRepo.findAll(PageRequest.of(pageIndex, pageSize));
		logger.info(String.format("For pageIndex=%s pageSize=%s, bookListSize=%s ", pageIndex, pageSize,
				bookList.getNumberOfElements()));
		return bookList;
	}

	@Transactional
	public void updateBookQuantity(UpdateBookQuantityRequest request) {
		
		Book book = getBook(request.getBookId());

		int bookQuantity = book.getQuantity();
		int newBookQuantity = bookQuantity + request.getUpdateQuantity();

		if (newBookQuantity < 0) {
			throw new EBookStoreException(ErrorCode.BOOK_NOT_AVAIBLE);
		}

		logger.info(String.format("Stock count is going to set as %s for book=%s", newBookQuantity, book));
		book.setQuantity(newBookQuantity);
		bookRepo.save(book);
	}
}
