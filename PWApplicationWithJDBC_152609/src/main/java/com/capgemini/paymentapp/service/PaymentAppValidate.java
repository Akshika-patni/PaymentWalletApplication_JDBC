package com.capgemini.paymentapp.service;

public class PaymentAppValidate {
	public boolean validatePhoneNo(String phonenumber)
	{
		     
				if(phonenumber.length()==10)
					return true;
				else
					return false;
	}
	public boolean validateCustomerName(String customerName)
	{
		     
				if(customerName == null)
					return false;
				else
					return true;
	}
	public boolean validateGenderName(String genderName)
	{
		if(genderName.equals("female") ||genderName.equals("Male"))
			return true;
		else 
			return false;
	}
	

}
