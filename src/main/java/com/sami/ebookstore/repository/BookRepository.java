package com.sami.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sami.ebookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
