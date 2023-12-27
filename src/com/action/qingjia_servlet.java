package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.orm.Tqingjia;
import com.service.liuService;

public class qingjia_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException
	{
		String type=req.getParameter("type");


		if(type.endsWith("qingjiaAdd"))
		{
			qingjiaAdd(req, res);
		}
		if(type.endsWith("qingjiaMine"))
		{
			qingjiaMine(req, res);
		}
		if(type.endsWith("qingjiaDel"))
		{
			qingjiaDel(req, res);
		}
		if(type.endsWith("qingjiaMana"))
		{
			qingjiaMana(req, res);
		}
		if(type.endsWith("qingjiaShenhe"))
		{
			qingjiaShenhe(req, res);
		}
	}


	public void qingjiaAdd(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");

		String id=String.valueOf(new Date().getTime());
		String kaishishijian=req.getParameter("kaishishijian");
		String jieshushijian=req.getParameter("jieshushijian");
		String beizhu=req.getParameter("beizhu");

		String yuangong_id=yuangong.getId();
		String zt="待审核";
		String huifu="";


		String sql="insert into t_qingjia(id,kaishishijian,jieshushijian,beizhu,yuangong_id,zt,huifu) values(?,?,?,?,?,?,?)";
		Object[] params={id,kaishishijian,jieshushijian,beizhu,yuangong_id,zt,huifu};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);

	}



	//我的请假
	public void qingjiaMine(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");

		List qingjiaList=new ArrayList();
		String sql="select * from t_qingjia where yuangong_id=?";
		Object[] params={yuangong.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tqingjia qingjia=new Tqingjia();

				qingjia.setId(rs.getString("id"));
				qingjia.setKaishishijian(rs.getString("kaishishijian"));
				qingjia.setJieshushijian(rs.getString("jieshushijian"));
				qingjia.setBeizhu(rs.getString("beizhu"));

				qingjia.setYuangong_id(rs.getString("yuangong_id"));
				qingjia.setZt(rs.getString("zt"));
				qingjia.setHuifu(rs.getString("huifu"));

				qingjia.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));

				qingjiaList.add(qingjia);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();

		req.setAttribute("qingjiaList", qingjiaList);
		req.getRequestDispatcher("admin/qingjia/qingjiaMine.jsp").forward(req, res);
	}

	public void qingjiaDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");

		String sql="delete from t_qingjia where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}



	//部门领导请假审核管理
	public void qingjiaMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		TYuangong yuangong=(TYuangong)session.getAttribute("yuangong");

		List qingjiaList1=new ArrayList();
		String sql="select * from t_qingjia";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tqingjia qingjia=new Tqingjia();

				qingjia.setId(rs.getString("id"));
				qingjia.setKaishishijian(rs.getString("kaishishijian"));
				qingjia.setJieshushijian(rs.getString("jieshushijian"));
				qingjia.setBeizhu(rs.getString("beizhu"));

				qingjia.setYuangong_id(rs.getString("yuangong_id"));
				qingjia.setZt(rs.getString("zt"));
				qingjia.setHuifu(rs.getString("huifu"));

				qingjia.setYuangong(liuService.getYuangong(rs.getString("yuangong_id")));

				qingjiaList1.add(qingjia);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();

//		List qingjiaList=new ArrayList();
//		for(int i=0;i<qingjiaList1.size();i++)
//		{
//			Tqingjia qingjia=(Tqingjia)qingjiaList1.get(i);
//			TYuangong yuangong1=qingjia.getYuangong();
//			if(yuangong1.getOrg_id().equals(yuangong.getOrg_id()))
//			{
//				qingjiaList.add(qingjia);
//			}
//		}

		req.setAttribute("qingjiaList", qingjiaList1);
		req.getRequestDispatcher("admin/qingjia/qingjiaMana.jsp").forward(req, res);
	}



	public void qingjiaShenhe(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		String zt=req.getParameter("zt");
		String huifu=req.getParameter("huifu");

		String sql="update t_qingjia set zt=?,huifu=? where id=?";
		Object[] params={zt,huifu,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("msg", "操作成功");
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