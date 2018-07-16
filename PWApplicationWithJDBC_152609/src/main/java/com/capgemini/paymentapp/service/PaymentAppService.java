package com.capgemini.paymentapp.service;

import java.util.Map;

import com.capgemini.paymentapp.bean.Customer;

import com.capgemini.paymentapp.dao.PaymentAppdao;

public class PaymentAppService implements IPaymentAppService {
  
	
	PaymentAppdao dao=new PaymentAppdao();
	Customer customer= new Customer();
	public boolean createAccount(Customer customer) {
		// TODO Auto-generated method stub
		return dao.createAccount(customer);
	}

	public double showBalance() {
		// TODO Auto-generated method stub
		 return dao.showBalance();
		
	}
	public boolean logIn(String user_ID,String password)
	{
		return dao.logIn(user_ID,password);
	}

	public boolean deposite(double  amount) {
		// TODO Auto-generated method stub
		return dao.deposite(amount);
	}

	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		return dao.withdraw(amount);
	}

	public boolean fundTransfer(long receiverAccountNumber,double amount) {
		// TODO Auto-generated method stub
		return dao.fundTransfer(receiverAccountNumber,amount);
		
	}

	public void printTranscation() {
		// TODO Auto-generated method stub
	dao.printTranscation();
	}

}
