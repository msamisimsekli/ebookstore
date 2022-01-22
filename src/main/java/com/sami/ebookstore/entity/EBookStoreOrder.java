package com.sami.ebookstore.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class EBookStoreOrder {

	@Id
	@GeneratedValue
	private Long id;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	private Long customerId;

	private Double totalPrice;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	private Integer totalPurchasedBook;

	public EBookStoreOrder() {

	}

	public EBookStoreOrder(Long id, LocalDateTime createDateTime, Long customerId, Double totalPrice, List<Book> books,
			Integer totalPurchasedBook) {
		super();
		this.id = id;
		this.createDateTime = createDateTime;
		this.customerId = customerId;
		this.totalPrice = totalPrice;
		this.books = books;
		this.totalPurchasedBook = totalPurchasedBook;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Integer getTotalPurchasedBook() {
		return totalPurchasedBook;
	}

	public void setTotalPurchasedBook(Integer totalPurchasedBook) {
		this.totalPurchasedBook = totalPurchasedBook;
	}

	@Override
	public String toString() {
		return "EBookStoreOrder [id=" + id + ", createDateTime=" + createDateTime + ", customerId=" + customerId
				+ ", totalPrice=" + totalPrice + ", books=" + books + ", totalPurchasedBook=" + totalPurchasedBook
				+ "]";
	}
}
