package com.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.DB;
import com.orm.TYuangong;
import com.orm.Torg;


public class liuService
{
	public static Torg get_org(String id)
	{
		Torg org=new Torg();
		
		String sql="select * from t_org where org_id=?";
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				org.setOrg_id(rs.getString("org_id"));
				org.setOrg_mingcheng(rs.getString("org_mingcheng"));
				org.setP_org_id(rs.getString("p_org_id"));
				org.setLeixing(rs.getString("leixing"));
				
				org.setOrg_del(rs.getString("org_del"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return org;
	}
	
	
	
	public static List get_ziorg_list(String org_id)
	{
		List ziorgList=new ArrayList();
		String sql="select * from t_org where org_del='no' and p_org_id=?";
		Object[] params={org_id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Torg org=new Torg();
				
				org.setOrg_id(rs.getString("org_id"));
				org.setOrg_mingcheng(rs.getString("org_mingcheng"));
				org.setP_org_id(rs.getString("p_org_id"));
				org.setLeixing(rs.getString("leixing"));
				
				org.setOrg_del(rs.getString("org_del"));
				
				ziorgList.add(org);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return ziorgList;
	}
	
	public static String panduan_gonghao(String gonghao)
	{
	    String s="buchongfu";
	    
	    String sql="select * from t_yuangong where del='no' and gonghao=?";
		Object[] params={gonghao.trim()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				s="chongfu";
			}
			
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return s;
	}
	
	
	public static TYuangong getYuangong(String id)
	{
		    TYuangong yuangong=new TYuangong();
		    
		    String sql="select * from t_yuangong where id=?";
			Object[] params={id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				rs.next();

				 yuangong.setId(rs.getString("id"));
				 yuangong.setOrg_id(rs.getString("org_id"));
				 yuangong.setGonghao(rs.getString("gonghao"));
				 yuangong.setLoginpw(rs.getString("loginpw"));
					
				 yuangong.setXingming(rs.getString("xingming"));
				 yuangong.setXingbie(rs.getString("xingbie"));
				 yuangong.setChusheng(rs.getString("chusheng"));
				 yuangong.setZhuzhi(rs.getString("zhuzhi"));
					
				 yuangong.setDel(rs.getString("del"));
					
				
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
		
		return yuangong;
	}
	
	
	public static String shifouchidao(String shijian1,String shijian2)
	{
		String s="";
		
		String s1=shijian1;
		String s2=shijian2;

		java.text.DateFormat df=new java.text.SimpleDateFormat("HH:mm:ss");

		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();

		try

		{
		    c1.setTime(df.parse(s1));
		    c2.setTime(df.parse(s2));
		}
		catch(java.text.ParseException e)
		{
		   System.err.println("格式不正确");
		}

		int result=c1.compareTo(c2);

		if(result==0)
		{
			System.out.println("c1相等c2");
			s="正常";
		}

		if(result<0)
		{
			System.out.println("c1小于c2");
			s="正常";
		}
		
		if(result>0)
		{
			System.out.println("c1>于c2");
			s="迟到";
		}

		return s;
	}
	
	
	
	
	public static String shifouzaotui(String shijian1,String shijian2)
	{
		String s="";
		
		String s1=shijian1;
		String s2=shijian2;

		java.text.DateFormat df=new java.text.SimpleDateFormat("HH:mm:ss");

		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();

		try

		{
		    c1.setTime(df.parse(s1));
		    c2.setTime(df.parse(s2));
		}
		catch(java.text.ParseException e)
		{
		   System.err.println("格式不正确");
		}

		int result=c1.compareTo(c2);

		if(result==0)
		{
			System.out.println("c1相等c2");
			s="正常";
		}

		if(result<0)
		{
			System.out.println("c1小于c2");
			s="早退";
		}
		
		if(result>0)
		{
			System.out.println("c1>于c2");
			s="正常";
		}

		return s;
	}
}
