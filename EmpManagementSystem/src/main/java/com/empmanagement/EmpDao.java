package com.empmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

//Initializing SQl Queries
	
	private static final String insert = "INSERT INTO employees.emp(name, phone, email) VALUES (?,?,?);";
	private static final String update = "update emp set name = ?, phone = ?, email = ? where id = ?";
	private static final String selectUserById = "select * from emp where id = ?";
	private static final String selectAll = "select * from employees.emp";
	private static final String delete = "delete from emp where id = ?";
	//database connection
	
	public static Connection getConnection() 
	{
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "root");
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return con;
	}
	
	//insert operation
	
	public static int  save(Emp e)
	{
		int status=0;
		
		try 
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement(insert);
			ps.setString(1, e.getName());
			ps.setString(2, e.getPhone());
			ps.setString(3, e.getEmail());
			
			status=ps.executeUpdate();
			
			con.close();
		} 
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
		return status;
	}
	
	//select entire table
	
	public static List<Emp> getAllEmployees(){
		List<Emp> list=new ArrayList<Emp>();
		
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement(selectAll);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Emp e=new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhone(rs.getString(3));
				e.setEmail(rs.getString(4));
				
				list.add(e);
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//get elemenBy id
	
	public static Emp getEmpById(int id) {
		Emp e= new Emp();
		
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement(selectUserById);
			
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhone(rs.getString(3));
				e.setEmail(rs.getString(4));
			}
			
			con.close();
			
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		return e;
	}
	
	
	//Update Operation
	
	public static int update(Emp e) {
		int status=0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement(update);
			
			ps.setString(1, e.getName());
			ps.setString(2, e.getPhone());
			ps.setString(3, e.getEmail());
			ps.setInt(4, e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
					
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return status;
	}
	
	
	//Delete Operation
	
	public static int delete(int id) {
		int status=0;
		
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement(delete);
			
			ps.setInt(1, id);
			
			status=ps.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
}
