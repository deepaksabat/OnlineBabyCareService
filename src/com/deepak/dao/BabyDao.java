package com.deepak.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.deepak.bean.Baby;
public class BabyDao {
	public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","deepak");
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return con;
	}
	public static int save(Baby b)
	{
	
		int status =0;
		try
		{
			Connection con = getCon();
			System.out.println(con);
			String sql = "insert into babyname(name,meaning,sex,religion) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getName());
			ps.setString(2, b.getMeaning());
			ps.setString(3,b.getSex());
			ps.setString(4,b.getReligion());
			status = ps.executeUpdate();
			System.out.println(status);
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return status;
	}
	public static int delete(int id)
	{
		int status =0;
		try
		{
			Connection con = getCon();
			String sql = "delete from babyname where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			status = ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return status;
	}
	public static List<Baby> getAllRecords(){
		{
			List<Baby> list=new ArrayList<Baby>();
			try
			{
				Connection con = getCon();
				PreparedStatement ps = con.prepareStatement("select * from babyname");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Baby b=new Baby();
					b.setId(rs.getInt(1));
					b.setName(rs.getString(2));
					b.setMeaning(rs.getString(3));
					b.setSex(rs.getString(4));
					b.setReligion(rs.getString(5));
					list.add(b);
				}
				con.close();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			return list;
			
		}
	}
	public static List<Baby> getRecordsByStart(String start){
		List<Baby> list=new ArrayList<Baby>();
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select * from babyname where name like '"+start+"%' ");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Baby b=new Baby();
				b.setId(rs.getInt(1));
				b.setName(rs.getString(2));
				b.setMeaning(rs.getString(3));
				b.setSex(rs.getString(4));
				b.setReligion(rs.getString(5));
				list.add(b);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static List<Baby> getRecordsByReligion(String religion){
		List<Baby> list=new ArrayList<Baby>();
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select * from babyname where religion=? ");
			ps.setString(1,religion);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Baby b=new Baby();
				b.setId(rs.getInt(1));
				b.setName(rs.getString(2));
				b.setMeaning(rs.getString(3));
				b.setSex(rs.getString(4));
				b.setReligion(rs.getString(5));
				list.add(b);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}

}
