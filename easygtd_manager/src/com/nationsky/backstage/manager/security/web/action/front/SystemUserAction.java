package com.nationsky.backstage.manager.security.web.action.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nationsky.backstage.core.Factor;
import com.nationsky.backstage.core.Factor.C;
import com.nationsky.backstage.core.I18n;
import com.nationsky.backstage.core.web.action.BaseAction;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.SystemUser;
import com.nationsky.backstage.manager.security.web.SecurityUrls;
import com.nationsky.backstage.util.HashUtil;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/")
public class SystemUserAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(SystemUserAction.class);
	
	@Autowired
	private ISecurityService securityService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request,HttpServletResponse response) {
		return loginPost(request, response);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request,HttpServletResponse response) {
		Object systemuser = request.getSession().getAttribute("systemuser");
		if(systemuser!=null){
			return this.getModelAndView(SecurityUrls.INDEX);
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username==null||password==null){
			return this.getModelAndView(SecurityUrls.SECURITY_LOGIN);
		}
		if(ValidateUtil.isNull(username)){
			this.getMessageMap().put("username", I18n.FILED_REQUIRED);
		}
		
		if(ValidateUtil.isNull(password)){
			this.getMessageMap().put("password", I18n.FILED_REQUIRED);
		}
		
		if(!this.getMessageMap().isEmpty()){
			return this.getModelAndView(SecurityUrls.SECURITY_LOGIN);
		}
		
		SystemUser systemUser = securityService.getUnique(SystemUser.class, Factor.create("name", C.Eq, username));
		if(systemUser!=null){
			if(ValidateUtil.isEquals(HashUtil.MD5Hashing(systemUser.getPassword()), HashUtil.MD5Hashing(password))){
				logger.info("登陆成功");
				request.getSession().setAttribute("systemuser",systemUser);
				return this.getModelAndView(SecurityUrls.INDEX);
			}
		}
		this.getMessageMap().put("error", "用户名或密码错误");
		return this.getModelAndView(SecurityUrls.SECURITY_LOGIN);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {
		String systemuserId = request.getParameter("systemuserId");
		boolean isExists=securityService.isExists(SystemUser.class, Factor.create("id", C.Eq, systemuserId));
		if(isExists){
			request.getSession().removeAttribute("systemuser");
		}
		return this.getModelAndView(SecurityUrls.SECURITY_LOGIN);
	}
}
