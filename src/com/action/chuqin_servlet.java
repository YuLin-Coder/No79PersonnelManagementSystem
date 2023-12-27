package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.TChuqin;
import com.orm.TQiantui;
import com.orm.TYuangong;
import com.orm.Tgonggao;
import com.service.liuService;

public class chuqin_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("chuqinMine"))
		{
			chuqinMine(req, res);
		}
		
		if(type.endsWith("chuqinMana"))
		{
			chuqinMana(req, res);
		}
		
	}
	
	public void chuqinMine(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{	
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");
		
		List chuqinList=new ArrayList();
		String sql="select * from t_qiandao where yuangong_id=? order by riqi desc";
		Object[] params={yuangong.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TChuqin chuqin=new TChuqin();
				
				chuqin.setId(rs.getString("id"));
				chuqin.setYuangong_id(rs.getString("yuangong_id"));
				chuqin.setRiqi(rs.getString("riqi"));
				chuqin.setQiandaoshi(rs.getString("shijian"));
				
				chuqin.setQiandaoshuxing(rs.getString("shuxing"));
				chuqin.setQiantuishi("");
				chuqin.setQiantuishuxing("");
				
				chuqin.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				chuqinList.add(chuqin);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		for(int i=0;i<chuqinList.size();i++)
		{
			TChuqin chuqin=(TChuqin)chuqinList.get(i);
			String riqi=chuqin.getRiqi();
			String yuangong_id=chuqin.getYuangong_id();
					
			chuqin.setQiantuishi(get_qiantuishi(yuangong_id, riqi));
			chuqin.setQiantuishuxing(get_qiantuiShuxing(yuangong_id, riqi));
		}
		
		req.setAttribute("chuqinList", chuqinList);
		req.getRequestDispatcher("admin/chuqin/chuqinMine.jsp").forward(req, res);
	}
	
	
	public void chuqinMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{	
		List chuqinList=new ArrayList();
		String sql="select * from t_qiandao order by riqi desc";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TChuqin chuqin=new TChuqin();
				
				chuqin.setId(rs.getString("id"));
				chuqin.setYuangong_id(rs.getString("yuangong_id"));
				chuqin.setRiqi(rs.getString("riqi"));
				chuqin.setQiandaoshi(rs.getString("shijian"));
				
				chuqin.setQiandaoshuxing(rs.getString("shuxing"));
				chuqin.setQiantuishi("");
				chuqin.setQiantuishuxing("");
				
				chuqin.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				chuqinList.add(chuqin);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		for(int i=0;i<chuqinList.size();i++)
		{
			TChuqin chuqin=(TChuqin)chuqinList.get(i);
			String riqi=chuqin.getRiqi();
			String yuangong_id=chuqin.getYuangong_id();
					
			chuqin.setQiantuishi(get_qiantuishi(yuangong_id, riqi));
			chuqin.setQiantuishuxing(get_qiantuiShuxing(yuangong_id, riqi));
		}
		
		req.setAttribute("chuqinList", chuqinList);
		req.getRequestDispatcher("admin/chuqin/chuqinMana.jsp").forward(req, res);
	}
	
	
	
	public String get_qiantuishi(String yuangong_id,String riqi)
	{
		String qiantuishi="";
		
		List qiantuiList=new ArrayList();
		String sql="select * from t_qiantui where yuangong_id=? and riqi=?";
		Object[] params={yuangong_id,riqi};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				qiantuishi=rs.getString("shijian");
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return qiantuishi;
	}
	
	public String get_qiantuiShuxing(String yuangong_id,String riqi)
	{
		String qiantuiShuxing="";
		
		List qiantuiList=new ArrayList();
		String sql="select * from t_qiantui where yuangong_id=? and riqi=?";
		Object[] params={yuangong_id,riqi};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				qiantuiShuxing=rs.getString("shuxing");
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return qiantuiShuxing;
	}
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
