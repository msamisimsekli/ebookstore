package com.sami.ebookstore.model;

import java.util.List;

public class CustomerOrderStatistic {

	private Long customerId;
	private List<MonthlyOrderStatistic> customerStatistics;

	public CustomerOrderStatistic(Long customerId, List<MonthlyOrderStatistic> customerStatistics) {
		super();
		this.customerId = customerId;
		this.customerStatistics = customerStatistics;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<MonthlyOrderStatistic> getCustomerStatistics() {
		return customerStatistics;
	}

	public void setCustomerStatistics(List<MonthlyOrderStatistic> customerStatistics) {
		this.customerStatistics = customerStatistics;
	}
}
