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
import com.orm.TYuangong;
import com.service.liuService;

public class yuangong_servlet extends HttpServlet
{ 
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		String type=req.getParameter("type");
		
		if(type.endsWith("yuangongAdd"))
		{
			yuangongAdd(req, res);
		}
		if(type.endsWith("yuangongMana"))
		{
			yuangongMana(req, res);
		}
		if(type.endsWith("yuangongDel"))
		{
			yuangongDel(req, res);
		}
		if(type.endsWith("yuangongSelect"))
		{
			yuangongSelect(req, res);
		}
		
		
		if(type.endsWith("yuangongEditMe"))
		{
			yuangongEditMe(req, res);
		}
		
		
	}
	
	
	//管理员添加员工
	public void yuangongAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		String org_id=(req.getParameter("org_id"));
		String gonghao=req.getParameter("gonghao");
		String loginpw=req.getParameter("loginpw");
		
		String xingming=req.getParameter("xingming");
		String xingbie=req.getParameter("xingbie");
		String chusheng=req.getParameter("chusheng");
		String zhuzhi=req.getParameter("zhuzhi");
		
		
		String del="no";
		
		String s=liuService.panduan_gonghao(gonghao.trim());
		if(s.equals("chongfu"))
		{
			req.setAttribute("msg", "工号重复，请重新输入");
			
	        String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
			return;
		}
		
		String sql="insert into t_yuangong(id,org_id,gonghao,loginpw,xingming,xingbie,chusheng,zhuzhi,del) values(?,?,?,?,?,?,?,?,?)";
		Object[] params={id,org_id,gonghao,loginpw,xingming,xingbie,chusheng,zhuzhi,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息添加完毕");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void yuangongMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no' order by org_id";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
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
				
				 yuangongList.add(yuangong);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yuangongList", yuangongList);
		req.getRequestDispatcher("admin/yuangong/yuangongMana.jsp").forward(req, res);
	}
	
	
	
	public void yuangongDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_yuangong set del=? where id=?";
		Object[] params={"yes",id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
        req.setAttribute("msg", "信息删除完毕");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void yuangongSelect(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no' order by org_id";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
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
				
				 yuangongList.add(yuangong);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yuangongList", yuangongList);
		req.getRequestDispatcher("admin/yuangong/yuangongSelect.jsp").forward(req, res);
	}
	
	
	public void yuangongEditMe(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		//String org_id=(req.getParameter("org_id"));
		//String gonghao=req.getParameter("gonghao");
		String loginpw=req.getParameter("loginpw");
		
		String xingming=req.getParameter("xingming");
		String xingbie=req.getParameter("xingbie");
		String chusheng=req.getParameter("chusheng");
		String zhuzhi=req.getParameter("zhuzhi");
		
		//String del="no";
		
		
		
		String sql="update t_yuangong set loginpw=?,xingming=?,xingbie=?,chusheng=?,zhuzhi=? where id=?";
		Object[] params={loginpw,xingming,xingbie,chusheng,zhuzhi,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		req.setAttribute("msg", "信息修改完毕,重新登陆后生效");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
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
