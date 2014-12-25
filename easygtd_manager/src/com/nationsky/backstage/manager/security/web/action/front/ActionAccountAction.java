package com.nationsky.backstage.manager.security.web.action.front;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nationsky.backstage.core.Factor;
import com.nationsky.backstage.core.Factor.C;
import com.nationsky.backstage.core.web.action.BaseAction;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.ActionAccount;
import com.nationsky.backstage.manager.security.util.DateJsonValueProcessor;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/actionaccount/")
public class ActionAccountAction extends BaseAction {

	static final Logger logger = LoggerFactory.getLogger(ActionAccountAction.class);
	@Autowired
	private ISecurityService securityService;
	
	@RequestMapping(value = "addDownloadCount", method = RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response) {
		try {
			String facilityType = request.getParameter("facilityType");
			String clientType = request.getParameter("clientType");
			String addCount = request.getParameter("addCount");
			ActionAccount actionAccount = securityService.getUnique(ActionAccount.class, Factor.create("facilityType", C.Eq, facilityType), Factor.create("clientType", C.Eq, clientType), Factor.create("actionType", C.Eq, "download"));
			actionAccount.setCount(actionAccount.getCount()+Integer.parseInt(addCount));
			actionAccount.setCreatedAt(new Date(System.currentTimeMillis()));
			actionAccount.setUpdatedAt(new Date(System.currentTimeMillis()));
			securityService.update(actionAccount);
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"下载成功\"}");
		} catch (IOException e) {
			logger.error("addDownloadCount error"+e.toString());
		}
	}
	
	@RequestMapping(value = "getAllDownloadCount", method = RequestMethod.GET)
	public void getAllJuweihui(HttpServletRequest request,HttpServletResponse response) {
		 try {
			List<ActionAccount> actionAccountList = securityService.findList(ActionAccount.class, 0, Integer.MAX_VALUE, null, Factor.create("actionType", C.Eq, "download"));
			for (ActionAccount actionAccount : actionAccountList) {
				if(ValidateUtil.isEquals(actionAccount.getClientType(), "Commcan_for_user")){
					actionAccount.setClientType("社区家园用户端");
				}else if(ValidateUtil.isEquals(actionAccount.getClientType(), "Commcan_for_staff")){
					actionAccount.setClientType("社区代表端");
				}
				
			}
			JSONArray actionAccountListJsonArray = DateJsonValueProcessor.listToJSONArray(actionAccountList);
				
			logger.debug("actionAccountListJsonArray.toString()=="+actionAccountListJsonArray.toString());
			response.getWriter().write(actionAccountListJsonArray.toString());
		} catch (IOException e) {
			logger.error("getAllDownloadCount error"+e.toString());
		}
	}
}
