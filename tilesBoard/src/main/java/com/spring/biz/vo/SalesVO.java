package com.spring.biz.vo;

public class SalesVO {
	private String buyMonth;
	private int salesPerMonth;
	private String salesYear;
	private String buyDay;
	private int salesPerDay;
	
	
	
	
	
	
	public int getSalesPerDay() {
		return salesPerDay;
	}
	public void setSalesPerDay(int salesPerDay) {
		this.salesPerDay = salesPerDay;
	}
	public String getBuyDay() {
		return buyDay;
	}
	public void setBuyDay(String buyDay) {
		this.buyDay = buyDay;
	}
	public String getSalesYear() {
		return salesYear;
	}
	public void setSalesYear(String salesYear) {
		this.salesYear = salesYear;
	}
	public String getBuyMonth() {
		return buyMonth;
	}
	public void setBuyMonth(String buyMonth) {
		this.buyMonth = buyMonth;
	}
	public int getSalesPerMonth() {
		return salesPerMonth;
	}
	public void setSalesPerMonth(int salesPerMonth) {
		this.salesPerMonth = salesPerMonth;
	}
	@Override
	public String toString() {
		return "SalesVO [buyMonth=" + buyMonth + ", salesPerMonth=" + salesPerMonth + ", salesYear=" + salesYear
				+ ", buyDay=" + buyDay + ", salesPerDay=" + salesPerDay + "]";
	}
}
