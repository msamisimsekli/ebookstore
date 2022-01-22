package com.sami.ebookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer quantity;
	private Double price;

	public Book() {

	}

	public Book(String name, Integer numberOfStock, Double price) {
		super();
		this.name = name;
		this.quantity = numberOfStock;
		this.price = price;
	}

	public Book(Long id, String name, Integer numberOfStock, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = numberOfStock;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer numberOfStock) {
		this.quantity = numberOfStock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
