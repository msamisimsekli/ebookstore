package com.sami.ebookstore.model;

public class MonthlyOrderStatistic {

	private Integer month;
	private Integer totalOrderCount;
	private Integer totalBookCount;
	private Double totalPurchasedAmount;

	public MonthlyOrderStatistic(Integer month, Integer totalOrderCount, Integer totalBookCount,
			Double totalPurchasedAmount) {
		super();
		this.month = month;
		this.totalOrderCount = totalOrderCount;
		this.totalBookCount = totalBookCount;
		this.totalPurchasedAmount = totalPurchasedAmount;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getTotalOrderCount() {
		return totalOrderCount;
	}

	public void setTotalOrderCount(Integer totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}

	public Integer getTotalBookCount() {
		return totalBookCount;
	}

	public void setTotalBookCount(Integer totalBookCount) {
		this.totalBookCount = totalBookCount;
	}

	public Double getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}

	public void setTotalPurchasedAmount(Double totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
}
