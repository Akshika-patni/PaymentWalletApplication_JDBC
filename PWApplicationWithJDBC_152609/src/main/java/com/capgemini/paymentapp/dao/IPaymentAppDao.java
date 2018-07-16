package com.capgemini.paymentapp.dao;

import java.util.Map;

import com.capgemini.paymentapp.bean.Customer;


public interface IPaymentAppDao {
	public boolean createAccount(Customer customer);

	public double showBalance();

	public boolean deposite(double amount);
	public boolean logIn(String user_ID,String password);

	public boolean withdraw( double amount);

	public boolean fundTransfer( long receiverAccountNumber,double amount);

	public void printTranscation();
}
