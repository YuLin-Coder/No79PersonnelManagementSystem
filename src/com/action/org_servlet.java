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

import com.dao.DB;
import com.orm.Torg;
import com.service.liuService;


public class org_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("orgMana"))
		{
			orgMana(req, res);
		}
		if(type.endsWith("org_ding_add"))
		{
			org_ding_add(req, res);
		}
		if(type.endsWith("org_xia_add"))
		{
			org_xia_add(req, res);
		}
		if(type.endsWith("orgDel"))
		{
			orgDel(req, res);
		}
		if(type.endsWith("orgPre"))
		{
			orgPre(req, res);
		}
		if(type.endsWith("orgEdit"))
		{
			orgEdit(req, res);
		}
		
		
		if(type.endsWith("orgAll"))
		{
			orgAll(req, res);
		}
		if(type.endsWith("orgAll1"))
		{
			orgAll1(req, res);
		}
		
		if(type.endsWith("orgAll2"))
		{
			orgAll2(req, res);
		}
	}
	
	
	public void orgMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List orgList=new ArrayList();
		String sql="select * from t_org where org_del='no' order by org_id";
		Object[] params={};
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
				
				orgList.add(org);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("orgList", orgList);
		req.getRequestDispatcher("admin/org/orgMana.jsp").forward(req, res);
	}
	
	public void org_ding_add(HttpServletRequest req,HttpServletResponse res)
	{
		String org_id=String.valueOf(new Date().getTime());
		String org_mingcheng=req.getParameter("org_mingcheng").trim();
		String p_org_id="";
		String leixing="部门";
		String org_del="no";
		
		String sql="insert into t_org(org_id,org_mingcheng,p_org_id,leixing,org_del) values(?,?,?,?,?)";
		Object[] params={org_id,org_mingcheng,p_org_id,leixing,org_del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void org_xia_add(HttpServletRequest req,HttpServletResponse res)
	{
		String org_id=String.valueOf(new Date().getTime());
		String org_mingcheng=req.getParameter("org_mingcheng").trim();
		String p_org_id=req.getParameter("p_org_id").trim();
		String leixing="部门";
		String org_del="no";
		
		String sql="insert into t_org(org_id,org_mingcheng,p_org_id,leixing,org_del) values(?,?,?,?,?)";
		Object[] params={org_id,org_mingcheng,p_org_id,leixing,org_del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void orgDel(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String org_id=req.getParameter("org_id").trim();
			   
		List ziorgList=liuService.get_ziorg_list(org_id);
		if(ziorgList.size()>0)
		{
			req.setAttribute("msg", "此部门下有子部门，不能删除");
			String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		}
		else
		{
			/*List userList=liuService.get_user_list(org_id);
			if(userList.size()>0)
			{
				req.setAttribute("msg", "此部门有员工，不能删除");
				String targetURL = "/common/msg.jsp";
				dispatch(targetURL, req, res);
			}*/
			
			String sql="update t_org set org_del='yes' where org_id=?";
			Object[] params={org_id};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("msg", "操作成功");
			String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		}
		
	}
	
	
	public void orgPre(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Torg org=new Torg();
		String sql="select * from t_org where org_id=?";
		Object[] params={req.getParameter("org_id").trim()};
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
		
		req.setAttribute("org", org);
		req.getRequestDispatcher("admin/org/orgPre.jsp").forward(req, res);
	}
	
	public void orgEdit(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String org_mingcheng=req.getParameter("org_mingcheng").trim();
		String org_id=req.getParameter("org_id").trim();
		
		String sql="update t_org set org_mingcheng=? where org_id=?";
		Object[] params={org_mingcheng,org_id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void orgAll(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List orgList=new ArrayList();
		String sql="select * from t_org where org_del='no' order by org_id";
		Object[] params={};
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
				
				orgList.add(org);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("orgList", orgList);
		req.getRequestDispatcher("admin/org/orgAll.jsp").forward(req, res);
	}
	
	public void orgAll1(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List orgList=new ArrayList();
		String sql="select * from t_org where org_del='no' order by org_id";
		Object[] params={};
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
				
				orgList.add(org);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("orgList", orgList);
		req.getRequestDispatcher("admin/org/orgAll1.jsp").forward(req, res);
	}
	
	
	
	public void orgAll2(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List orgList=new ArrayList();
		String sql="select * from t_org where org_del='no' order by org_id";
		Object[] params={};
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
				
				orgList.add(org);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("orgList", orgList);
		req.getRequestDispatcher("qiantai/org/orgAll2.jsp").forward(req, res);
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
