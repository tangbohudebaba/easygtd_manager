/**
 * 
 */
package com.nationsky.backstage.manager.security.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：处理页面访问的安全问题
 * @author yubaojian0616@163.com
 *
 * mobile enterprise application platform
 * Version 0.1
 */
public class LoginFilter implements Filter {
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		Object user = request.getSession().getAttribute("systemuser");
		String requestURI = request.getRequestURI();
		if(user==null&&(!requestURI.contains("login.ac"))&&(!requestURI.contains("logout.ac"))){
			if(request.getHeader("x-requested-with")!=null &&request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
				response.getWriter().write("请登录");
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/manager/login.jhtml");
				return;
			}
		}else if(user!=null&&(requestURI.contains("login.ac"))){
			response.sendRedirect(request.getContextPath() + "/manager/index.jhtml");
		}
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
