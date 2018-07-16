package com.capgemini.paymentapp.bean;

import java.sql.Date;
import java.time.LocalDate;

public class Wallet {
	private long accountNumber;
	private double initalBalance;
	private long adharNumber;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(long adharNumber) {
		this.adharNumber = adharNumber;
	}

	public double getInitalBalance() {
		return initalBalance;
	}

	public void setInitalBalance(double initalBalance) {
		this.initalBalance = initalBalance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Wallet [accountNumber=" + accountNumber + ", initalBalance=" + initalBalance + ", adharNumber="
				+ adharNumber + ", date=" + date + "]";
	}

}
