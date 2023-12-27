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
import com.orm.TYuangong;
import com.orm.Tgongzi;
import com.service.liuService;

public class gongzi_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("gongziAdd"))
		{
			gongziAdd(req, res);
		}
		if(type.endsWith("gongziMana"))
		{
			gongziMana(req, res);
		}
		if(type.endsWith("gongziDel"))
		{
			gongziDel(req, res);
		}
		
		if(type.endsWith("gongziMine"))
		{
			gongziMine(req, res);
		}
	}
	
	
	public void gongziAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		String yuangong_id=req.getParameter("yuangong_id");
		String yuefen=req.getParameter("yuefen");
		int jiben=Integer.parseInt(req.getParameter("jiben"));
		
		int jiangjin=Integer.parseInt(req.getParameter("jiangjin"));
		int kouchu=Integer.parseInt(req.getParameter("kouchu"));
		String beizhu=req.getParameter("beizhu");
		int zong=jiben+jiangjin-kouchu;
		
		String sql="insert into t_gongzi(id,yuangong_id,yuefen,jiben,jiangjin,kouchu,beizhu,zong) values(?,?,?,?,?,?,?,?)";
		Object[] params={id,yuangong_id,yuefen,jiben,jiangjin,kouchu,beizhu,zong};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息添加完毕");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void gongziMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List gongziList=new ArrayList();
		String sql="select * from t_gongzi";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongzi gongzi=new Tgongzi();
				
				gongzi.setId(rs.getString("id"));
				gongzi.setYuangong_id(rs.getString("yuangong_id"));
				gongzi.setYuefen(rs.getString("yuefen"));
				gongzi.setJiben(rs.getInt("jiben"));
				
				gongzi.setJiangjin(rs.getInt("jiangjin"));
				gongzi.setKouchu(rs.getInt("kouchu"));
				gongzi.setBeizhu(rs.getString("beizhu"));
				gongzi.setZong(rs.getInt("zong"));
				
				gongzi.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				gongziList.add(gongzi);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongziList", gongziList);
		req.getRequestDispatcher("admin/gongzi/gongziMana.jsp").forward(req, res);
	}
	
	public void gongziDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		String sql="delete from t_gongzi where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息删除完毕");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void gongziMine(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		TYuangong yuan=(TYuangong)session.getAttribute("yuangong");
		
		List gongziList=new ArrayList();
		String sql="select * from t_gongzi where yuangong_id="+yuan.getId();
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongzi gongzi=new Tgongzi();
				
				gongzi.setId(rs.getString("id"));
				gongzi.setYuangong_id(rs.getString("yuangong_id"));
				gongzi.setYuefen(rs.getString("yuefen"));
				gongzi.setJiben(rs.getInt("jiben"));
				
				gongzi.setJiangjin(rs.getInt("jiangjin"));
				gongzi.setKouchu(rs.getInt("kouchu"));
				gongzi.setBeizhu(rs.getString("beizhu"));
				gongzi.setZong(rs.getInt("zong"));
				
				gongzi.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				gongziList.add(gongzi);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongziList", gongziList);
		req.getRequestDispatcher("admin/gongzi/gongziMine.jsp").forward(req, res);
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
