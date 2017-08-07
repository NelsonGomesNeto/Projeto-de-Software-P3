package com.system.payments;

import java.util.Date;

public class Payment {

	private Date date;
	private double value;

	public Payment(Date date, double value) {

		this.date = date;
		this.value = value;
	}

	public Date getDate() {
		return this.date;
	}

	public double getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "Payment{" +
			"date=" + date +
			", value=" + value +
			'}';
	}
}
