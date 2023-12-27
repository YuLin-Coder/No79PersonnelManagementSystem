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
import com.orm.TQiantui;
import com.orm.TYuangong;
import com.service.liuService;
public class qiantui_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("qiantuiAdd"))
		{
			qiantuiAdd(req, res);
		}
		if(type.endsWith("qiantuiMine"))
		{
			qiantuiMine(req, res);
		}
		if(type.endsWith("qiantuiDel"))
		{
			qiantuiDel(req, res);
		}
		
		
		if(type.endsWith("qiantuiMana"))
		{
			qiantuiMana(req, res);
		}
	}
	
	
	public void qiantuiAdd(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");
				
		
		String id=String.valueOf(new Date().getTime());
		String yuangong_id=yuangong.getId();
		String riqi=req.getParameter("riqi").trim();
		String shijian=req.getParameter("shijian").trim();
		
		String shuxing=liuService.shifouzaotui(shijian, "17:00:00");
		
		
		String s=panduan_shifou_qiantui(yuangong_id, riqi);
		if(s.endsWith("yiqiantui"))
		{
			req.setAttribute("msg", "今日您已经签退，请不要重复签退");
	        String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		}
		else
		{
			String sql="insert into t_qiantui values(?,?,?,?,?)";
			Object[] params={id,yuangong_id,riqi,shijian,shuxing};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("msg", "操作成功");
	        String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		}
		
		
		
	}
	

	public void qiantuiMine(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");
		
		List qiantuiList=new ArrayList();
		String sql="select * from t_qiantui where yuangong_id=? order by id desc";
		Object[] params={yuangong.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TQiantui qiantui=new TQiantui();
				
				qiantui.setId(rs.getString("id"));
				qiantui.setYuangong_id(rs.getString("yuangong_id"));
				qiantui.setRiqi(rs.getString("riqi"));
				qiantui.setShijian(rs.getString("shijian"));
				
				qiantui.setShuxing(rs.getString("shuxing"));
				
				qiantui.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				qiantuiList.add(qiantui);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("qiantuiList", qiantuiList);
		req.getRequestDispatcher("admin/qiantui/qiantuiMine.jsp").forward(req, res);
	}
	
	
	public void qiantuiDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_qiantui where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	
	public void qiantuiMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List qiantuiList=new ArrayList();
		String sql="select * from t_qiantui order by id desc";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
                TQiantui qiantui=new TQiantui();
				
				qiantui.setId(rs.getString("id"));
				qiantui.setYuangong_id(rs.getString("yuangong_id"));
				qiantui.setRiqi(rs.getString("riqi"));
				qiantui.setShijian(rs.getString("shijian"));
				
				qiantui.setShuxing(rs.getString("shuxing"));
				
				qiantui.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				qiantuiList.add(qiantui);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("qiantuiList", qiantuiList);
		req.getRequestDispatcher("admin/qiantui/qiantuiMana.jsp").forward(req, res);
	}
	
	
	
	public String panduan_shifou_qiantui(String yuangong_id,String riqi)
	{
		String s="weiqiantui";
		
		String sql="select * from t_qiantui where yuangong_id=? and riqi=?";
		Object[] params={yuangong_id,riqi};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				s="yiqiantui";
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
