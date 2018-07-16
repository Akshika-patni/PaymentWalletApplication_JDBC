package com.capgemini.paymentapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.paymentapp.bean.Customer;
import com.capgemini.paymentapp.util.DBUtil;
import com.mysql.jdbc.Statement;

public class PaymentAppdao implements IPaymentAppDao{
	Connection con;
	ResultSet rs1;
	ResultSet rs2;
	public boolean createAccount(Customer customer) {
		int n1=0;
		int n2=0;
		try {
			System.out.println("1");
			con = DBUtil.getConnection();
			System.out.println("2");
		String insertquery1="insert into customer values( ?,?,?,?,?,?,?,?)";
		System.out.println("3");
		java.sql.PreparedStatement pstmt1=con.prepareStatement(insertquery1);
		pstmt1.setString(1,customer.getCustomerName());
		pstmt1.setLong(2,customer.getAdharNumber());
		pstmt1.setString(3,customer.getAddress());
		System.out.println("4");
		pstmt1.setString(4, customer.getPhoneNumber());
		pstmt1.setString(5, customer.getGender());
		pstmt1.setInt(6, customer.getAge());
		pstmt1.setString(7, customer.getUser_ID());
		pstmt1.setString(8, customer.getPassword());
		System.out.println("5");
		String insertquery2="insert into wallet values( ?,curdate(),?,?)";
		System.out.println("6");
		java.sql.PreparedStatement pstmt2=con.prepareStatement(insertquery2);
		pstmt2.setLong(1,customer.getWallet().getAccountNumber());
		System.out.println("7");
		pstmt2.setDouble(2, customer.getWallet().getInitalBalance());
		pstmt2.setLong(3, customer.getWallet().getAdharNumber());
		System.out.println("8");
		n1=pstmt1.executeUpdate();
		System.out.println("9");
		n2=pstmt2.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("10");
		if(n1==1 && n2==1) {
			System.out.println("11");
			return true;
		}
		else
			return false;
		
		
	}

	public double showBalance() {
		double d=0;
		try { 
			java.sql.Statement stmt=con.createStatement();
			String selectquery1="select initialBalance from wallet where adharNo='"+rs2.getInt(3)+"'";
			ResultSet rs3=stmt.executeQuery(selectquery1);
			
			d=rs3.getDouble(3);
			//return d;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	return d;
	}

	public boolean deposite(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean logIn(String user_ID, String password) {
		
		try {
			con = DBUtil.getConnection();
			java.sql.Statement stmt1=con.createStatement();
			String selectquery1="select * from customer where user_ID='"+user_ID+"' and password='"+password+"'";
			rs1=stmt1.executeQuery(selectquery1);
			int d=rs1.getInt(2);
			java.sql.Statement stmt2=con.createStatement();
			String selectquery2="select * from wallet where adharNo='"+d+"'";
			rs2=stmt2.executeQuery(selectquery2);
			
			System.out.println("yup");
			if(rs1.first() || rs2.first())
			{
				return true;
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean fundTransfer(long receiverAccountNumber, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void printTranscation() {
		// TODO Auto-generated method stub
	
	}
	
}
