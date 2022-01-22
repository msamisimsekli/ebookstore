package com.sami.ebookstore.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sami.ebookstore.entity.EBookStoreOrder;

@Repository
public interface OrderRepository extends JpaRepository<EBookStoreOrder, Long> {
	public List<EBookStoreOrder> findByCustomerId(Long customerId);
	public List<EBookStoreOrder> findByCreateDateTimeBetween(LocalDateTime startInterval, LocalDateTime endDateTime);
}
