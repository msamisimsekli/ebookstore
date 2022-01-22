package com.sami.ebookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sami.ebookstore.model.CustomerOrderStatistic;
import com.sami.ebookstore.service.StatisticService;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

	private final StatisticService statisticService;

	@Autowired
	public StatisticsController(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@GetMapping("getCustomerStatistic")
	public ResponseEntity<CustomerOrderStatistic> getCustomerStatistics(@RequestParam Long customerId) {
		return new ResponseEntity<CustomerOrderStatistic>(statisticService.getCustomerStatistics(customerId),
				HttpStatus.OK);
	}

	@GetMapping("getAllCustomerStatistic")
	public ResponseEntity<List<CustomerOrderStatistic>> getAllCustomerStatistics() {
		return new ResponseEntity<List<CustomerOrderStatistic>>(statisticService.getAllCustomerStatistics(),
				HttpStatus.OK);
	}
}
