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
import com.orm.TQiandao;
import com.orm.TQiantui;
import com.orm.TYuangong;
import com.service.liuService;
public class qiandao_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("qiandaoAdd"))
		{
			qiandaoAdd(req, res);
		}
		if(type.endsWith("qiandaoMine"))
		{
			qiandaoMine(req, res);
		}
		if(type.endsWith("qiandaoDel"))
		{
			qiandaoDel(req, res);
		}
		
		
		if(type.endsWith("qiandaoMana"))
		{
			qiandaoMana(req, res);
		}
	}
	
	
	public void qiandaoAdd(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");
				
		
		String id=String.valueOf(new Date().getTime());
		String yuangong_id=yuangong.getId();
		String riqi=req.getParameter("riqi").trim();
		String shijian=req.getParameter("shijian").trim();
		
		String shuxing=liuService.shifouchidao(shijian, "09:00:00");
		
		
		String s=panduan_shifou_qiandao(yuangong_id, riqi);
		if(s.endsWith("yiqiandao"))
		{
			req.setAttribute("msg", "今日您已经签到，请不要重复签到");
	        String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		}
		else
		{
			String sql="insert into t_qiandao values(?,?,?,?,?)";
			Object[] params={id,yuangong_id,riqi,shijian,shuxing};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("msg", "操作成功");
	        String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		}
		
		
		
	}
	

	public void qiandaoMine(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");
		
		List qiandaoList=new ArrayList();
		String sql="select * from t_qiandao where yuangong_id=? order by id desc";
		Object[] params={yuangong.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TQiandao qiandao=new TQiandao();
				
				qiandao.setId(rs.getString("id"));
				qiandao.setYuangong_id(rs.getString("yuangong_id"));
				qiandao.setRiqi(rs.getString("riqi"));
				qiandao.setShijian(rs.getString("shijian"));
				
				qiandao.setShuxing(rs.getString("shuxing"));
				
				qiandao.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				qiandaoList.add(qiandao);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("qiandaoList", qiandaoList);
		req.getRequestDispatcher("admin/qiandao/qiandaoMine.jsp").forward(req, res);
	}
	
	
	public void qiandaoDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_qiandao where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	
	public void qiandaoMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List qiandaoList=new ArrayList();
		String sql="select * from t_qiandao order by id desc";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
                TQiandao qiandao=new TQiandao();
				
				qiandao.setId(rs.getString("id"));
				qiandao.setYuangong_id(rs.getString("yuangong_id"));
				qiandao.setRiqi(rs.getString("riqi"));
				qiandao.setShijian(rs.getString("shijian"));
				
				qiandao.setShuxing(rs.getString("shuxing"));
				
				qiandao.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));
				
				qiandaoList.add(qiandao);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		for(int i=0;i<qiandaoList.size();i++)
		{
			TQiandao qiandao=(TQiandao)qiandaoList.get(i);
			TQiantui qiantui=get_qiantui(qiandao.getYuangong_id(), qiandao.getRiqi());
			qiandao.setQiantuishi(qiantui.getShijian());
			qiandao.setQiantuishuxing(qiantui.getShuxing());
		}
		
		req.setAttribute("qiandaoList", qiandaoList);
		req.getRequestDispatcher("admin/qiandao/qiandaoMana.jsp").forward(req, res);
	}
	
	
	
	public String panduan_shifou_qiandao(String yuangong_id,String riqi)
	{
		String s="weiqiandao";
		
		String sql="select * from t_qiandao where yuangong_id=? and riqi=?";
		Object[] params={yuangong_id,riqi};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				s="yiqiandao";
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
	
	
	public TQiantui get_qiantui(String yuangong_id,String riqi)
	{
		TQiantui qiantui=new TQiantui();
		String sql="select * from t_qiantui where yuangong_id=? and riqi=?";
		Object[] params={yuangong_id,riqi};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				qiantui.setId(rs.getString("id"));
				qiantui.setYuangong_id(rs.getString("yuangong_id"));
				qiantui.setRiqi(rs.getString("riqi"));
				qiantui.setShijian(rs.getString("shijian"));
				
				qiantui.setShuxing(rs.getString("shuxing"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return qiantui;
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
