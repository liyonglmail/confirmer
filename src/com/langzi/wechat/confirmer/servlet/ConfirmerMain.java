package com.langzi.wechat.confirmer.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.langzi.wechat.confirmer.log.Logger;

import org.json.simple.*;



/**
 * Servlet implementation class ConfirmerMain
 */
//@WebServlet("/ConfirmerMain")
public class ConfirmerMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String PARAM_NAME_ORDER = "order";
	public static final String PARAM_NAME_CODE = "code";
	public static final String ORDER_GET3RDSESSION="get3rdSession";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmerMain() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getPrameterFromRequest(HttpServletRequest request, String paramName) {
    	String paramValue = request.getParameter(paramName);
		if(paramValue == null || paramValue.isEmpty()) {
			paramValue = (String)request.getAttribute(paramName);
		}
		
		return paramValue;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String order = getPrameterFromRequest(request, PARAM_NAME_ORDER);
		if(order == null || order.isEmpty())
			throw new RuntimeException("Error: order is not set");
		Logger.debug("order=" + order);
		switch (order) {
			case ORDER_GET3RDSESSION:
				String code = getPrameterFromRequest(request, PARAM_NAME_CODE);
				if(code == null || code.isEmpty())
					throw new RuntimeException("Error: code not set.");
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", "foo");
				jsonObj.put("num", new Integer(100));
				jsonObj.put("balance", new Double(1000.21));
				jsonObj.put("is_vip", new Boolean(true));

				response.getWriter().write(jsonObj.toJSONString());

				break;
			default:
				Logger.debug("order is not valid: " + order);
				break;
		}
//		response.setStatus(arg0);
//		System.out.println("servlet is called.");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
}
