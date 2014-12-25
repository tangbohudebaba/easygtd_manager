package com.nationsky.backstage.manager.security.web.action.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.nationsky.backstage.manager.security.bsc.dao.po.Intro;

@Controller
@RequestMapping(value = "/manager/intro/")
public class IntroAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(IntroAction.class);
	@Autowired
	private ISecurityService securityService;
	
	
	//1 居委会;2 物业
	@RequestMapping(value = "searchwuyeAutocomplete", method = RequestMethod.GET)
	public void searchwuyeAutocomplete(HttpServletRequest request,HttpServletResponse response) {
		String keyword = request.getParameter("term");
		List<Intro> introList = securityService.findList(Intro.class, 0, 10, null, Factor.create("title", C.Like,  "%"+keyword+"%"),Factor.create("introType", C.Eq, 2));
		
		StringBuffer resultNamesAndIds = new StringBuffer();
		resultNamesAndIds.append("[");
		
		for (int i = 0; i < introList.size(); i++) {
			String id =  introList.get(i).getId().toString();
			String name = introList.get(i).getTitle();
			if(i==(introList.size()-1)){
				resultNamesAndIds.append("\""+name+"\"]");
			}else{
				resultNamesAndIds.append("\""+name+"\",");
			}
		}
		logger.debug("jsonArray="+resultNamesAndIds.toString());
		try {
			response.getWriter().write(resultNamesAndIds.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "searchjuweihuiAutocomplete", method = RequestMethod.GET)
	public void searchjuweihuiAutocomplete(HttpServletRequest request,HttpServletResponse response) {
		String keyword = request.getParameter("term");
		List<Intro> introList = securityService.findList(Intro.class, 0, 10, null, Factor.create("title", C.Like, "%"+keyword+"%"),Factor.create("introType", C.Eq, 1));
		
		StringBuffer resultNamesAndIds = new StringBuffer();
		resultNamesAndIds.append("[");
		
		for (int i = 0; i < introList.size(); i++) {
			String name = introList.get(i).getTitle();
			if(i==(introList.size()-1)){
				resultNamesAndIds.append("\""+name+"\"]");
			}else{
				resultNamesAndIds.append("\""+name+"\",");
			}
		}
		logger.debug("jsonArray="+resultNamesAndIds.toString());
		try {
			response.getWriter().write(resultNamesAndIds.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
