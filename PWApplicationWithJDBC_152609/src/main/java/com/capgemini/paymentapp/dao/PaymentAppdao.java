package com.capgemini.paymentapp.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.capgemini.paymentapp.bean.Customer;
import com.capgemini.paymentapp.util.DBUtil;

public class PaymentAppdao implements IPaymentAppDao {
	Connection con;
	static double d, t;
	public static ResultSet rs1;
	public static ResultSet rs2, rs3, rs4;

	public boolean createAccount(Customer customer) {
		int n1 = 0;
		int n2 = 0;
		try {
			con = DBUtil.getConnection();
			String insertquery1 = "insert into customer values( ?,?,?,?,?,?,?,?)";

			java.sql.PreparedStatement pstmt1 = con.prepareStatement(insertquery1);
			pstmt1.setString(1, customer.getCustomerName());
			pstmt1.setLong(2, customer.getAdharNumber());
			pstmt1.setString(3, customer.getAddress());

			pstmt1.setString(4, customer.getPhoneNumber());
			pstmt1.setString(5, customer.getGender());
			pstmt1.setInt(6, customer.getAge());
			pstmt1.setString(7, customer.getUser_ID());
			pstmt1.setString(8, customer.getPassword());

			String insertquery2 = "insert into wallet values( ?,curdate(),?,?)";
			java.sql.PreparedStatement pstmt2 = con.prepareStatement(insertquery2);
			pstmt2.setLong(1, customer.getWallet().getAccountNumber());
			pstmt2.setDouble(2, customer.getWallet().getInitalBalance());
			pstmt2.setLong(3, customer.getWallet().getAdharNumber());
			n1 = pstmt1.executeUpdate();
			n2 = pstmt2.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				System.out.println("connection closed");
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (n1 == 1 && n2 == 1) {

			return true;
		} else
			return false;
	}

	public double showBalance() {

		try {
			if (rs2.first()) {
				d = rs2.getDouble(3);
				return d;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public boolean deposite(double amount) {
		try {
			if (rs2.first()) {
				d = rs2.getDouble(3) + amount;
				String updatequery = "update wallet set initialBalance='" + d + "' where adharNo='" + rs2.getDouble(4)
						+ "'";
				String select = "select * from wallet where adharNo='" + rs2.getDouble(4) + "'";
				java.sql.PreparedStatement stmt = con.prepareStatement(updatequery);
				long tid = (long) (Math.random() * 1234 + 1234);
				LocalDateTime date=LocalDateTime.now();
				String s = "Deposited " + Double.toString(amount) + " with transaction id " + Long.toString(tid)+" on "+date.toString();
				String insrt = "insert into transaction values('" + rs2.getDouble(4) + "','" + s + "')";
				int r = stmt.executeUpdate();
				rs2 = stmt.executeQuery(select);
				if (r == 1) {
					java.sql.PreparedStatement pstmt = con.prepareStatement(insrt);
					pstmt.executeUpdate();
					return true;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean logIn(String user_ID, String password) {
		try {
			con = DBUtil.getConnection();
			java.sql.Statement stmt1 = con.createStatement();
			String selectquery1 = "select * from customer where user_ID='" + user_ID + "' and password='" + password
					+ "'";
			rs1 = stmt1.executeQuery(selectquery1);
			if (rs1.first()) {
				int k = rs1.getInt(2);
				java.sql.Statement stmt2 = con.createStatement();
				String selectquery2 = "select * from wallet where adharNo='" + k + "'";
				rs2 = stmt2.executeQuery(selectquery2);
				return true;
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean withdraw(double amount) {
		try {
			if (rs2.first()) {

				d = rs2.getDouble(3) - amount;
				long tid = (long) (Math.random() * 1234 + 1234);
				LocalDateTime date=LocalDateTime.now();
				String s = "Withdrawn " + Double.toString(amount) + " with transaction id " + Long.toString(tid)+" on "+date.toString();

				String withdraw = "insert into transaction values('" + rs2.getDouble(4) + "','" + s + "')";

				String updatequery = "update wallet set initialBalance='" + d + "' where adharNo='" + rs2.getDouble(4)
						+ "'";

				String select = "select * from wallet where adharNo='" + rs2.getDouble(4) + "'";
				java.sql.PreparedStatement stmt = con.prepareStatement(updatequery);
				int r = stmt.executeUpdate();
				rs2 = stmt.executeQuery(select);
				if (r == 1) {
					java.sql.PreparedStatement pstmt = con.prepareStatement(withdraw);
					pstmt.executeUpdate();
					return true;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean fundTransfer(long receiverAccountNumber, double amount) {
		int r = 0, r1 = 0;
		try {

			String sel = "select * from wallet where accountNumber=" + receiverAccountNumber;
			java.sql.PreparedStatement smt = con.prepareStatement(sel);
			rs3 = smt.executeQuery();
			
			if (rs3.first()) {
				d = rs3.getDouble(3) + amount;
				String updatequery = "update wallet set initialBalance=" + d + " where adharNo=" + rs3.getDouble(4);
				java.sql.PreparedStatement stmt = con.prepareStatement(updatequery);
				r1 = stmt.executeUpdate();
				if(r1==1) {
				long tid = (long) (Math.random() * 1234 + 1234);
				LocalDateTime date=LocalDateTime.now();
				String s = "Recieved " + Double.toString(amount) +" with transaction id " + Long.toString(tid)+" on "+date.toString();
				String recieve = "insert into transaction values('" + rs3.getDouble(4) + "','" + s + "')";
				java.sql.PreparedStatement pstmt = con.prepareStatement(recieve);
				pstmt.executeUpdate();
				
				if (rs2.first()) {
					 double d1 = rs2.getDouble(3) - amount;
					String updatequery1 = "update wallet set initialBalance=" + d1 + " where adharNo=" + rs2.getDouble(4);
					java.sql.PreparedStatement stmt1 = con.prepareStatement(updatequery1);
					r = stmt1.executeUpdate();
					if(r==1) {
					long tid1 = (long) (Math.random() * 1234 + 1234);
					String s1 = "Transfered " + Double.toString(amount) + " with transaction id " + Long.toString(tid)+" on "+date.toString();
					String transfer = "insert into transaction values('" + rs2.getDouble(4) + "','" + s1 + "')";
					String select = "select * from wallet where adharNo=" + rs2.getDouble(4);
					rs2 = stmt.executeQuery(select);
					java.sql.PreparedStatement pstmt1 = con.prepareStatement(transfer);
					pstmt1.executeUpdate();
					}

				}
				}
			}
			if (r == 1 && r1 == 1) {
				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void printTranscation() {
		try {
			if (rs2.first()) {
				d = rs2.getDouble(4);
				String select = "select * from transaction where adharNo='" + d + "'";
				java.sql.PreparedStatement stmt = con.prepareStatement(select);
				rs4 = stmt.executeQuery();
				while (rs4.next()) {
					System.out.println(rs4.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	}

}