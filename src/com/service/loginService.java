package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.TYuangong;

public class loginService
{
	public String login(String userName,String userPw,int userType)
	{
		System.out.println(userType+"UUU"); 
		
		WebContext ctx = WebContextFactory.get(); 
		 HttpSession session=ctx.getSession(); 
		 
		 String result="no";
		 
		
		
		
		if(userType==0)//ÏµÍ³¹ÜÀíÔ±µÇÂ½
		{
			String sql="select * from t_admin where userName=? and userPw=?";
			Object[] params={userName.trim(),userPw.trim()};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 TAdmin admin=new TAdmin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 
					 session.setAttribute("userType", 0);
		             session.setAttribute("admin", admin);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		
		
		if(userType==1)
		{
			String sql="select * from t_yuangong where del='no' and gonghao=? and loginpw=?";
			Object[] params={userName.trim(),userPw.trim()};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
				     result="yes";
				 
				     TYuangong yuangong=new TYuangong();
					
				     yuangong.setId(rs.getString("id"));
					 yuangong.setOrg_id(rs.getString("org_id"));
					 yuangong.setGonghao(rs.getString("gonghao"));
					 yuangong.setLoginpw(rs.getString("loginpw"));
						
					 yuangong.setXingming(rs.getString("xingming"));
					 yuangong.setXingbie(rs.getString("xingbie"));
					 yuangong.setChusheng(rs.getString("chusheng"));
					 yuangong.setZhuzhi(rs.getString("zhuzhi"));
						
					 yuangong.setDel(rs.getString("del"));
						
					 yuangong.setOrg(liuService.get_org(rs.getString("org_id")));
					 
					 session.setAttribute("userType", 1);
		             session.setAttribute("yuangong", yuangong);
					 
					
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
		}
		if(userType==2)
		{
			
		}
		return result;
	}
	
	
	
	
	public String userlogin(String userName,String userPw,int userType)
	{
			
		return "";
	}
	
	
    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		String sql="update t_admin set userPw=? where userId=?";
		Object[] params={userPwNew,admin.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
    }
}
