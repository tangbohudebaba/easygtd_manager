package com.nationsky.backstage.manager.security.web.action.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nationsky.backstage.core.web.action.BaseAction;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;

@Controller
@RequestMapping(value = "/manager/businesscategories/")
public class BusinessCategoriesAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(BusinessCategoriesAction.class);
	@Autowired
	private ISecurityService securityService;
	
	
	
}
