package com.nationsky.backstage.manager.security.web.action.front;

import java.io.IOException;
import java.sql.Timestamp;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nationsky.backstage.Configuration;
import com.nationsky.backstage.core.Factor;
import com.nationsky.backstage.core.Factor.C;
import com.nationsky.backstage.core.Identities;
import com.nationsky.backstage.core.web.action.BaseAction;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.ClientUserHomeimg;
import com.nationsky.backstage.manager.security.util.DateJsonValueProcessor;
import com.nationsky.backstage.manager.security.web.SecurityUrls;
import com.nationsky.backstage.util.FileUtil;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/client_user_homeimg/")
public class ClientUserHomeimgAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(ClientUserHomeimgAction.class);
	@Autowired
	private ISecurityService securityService;
	
	@RequestMapping(value = "getAllHomeimg", method = RequestMethod.GET)
	public void getAllHomeimg(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			List<ClientUserHomeimg> clientUserHomeimgList = securityService.findList(ClientUserHomeimg.class, 0, Integer.MAX_VALUE, "updatedAt:desc");
			for (ClientUserHomeimg clientUserHomeimg : clientUserHomeimgList) {
				
				clientUserHomeimg.setImgName("<a href=\"#\" onClick=\"to_other_page(\'client_user_homeimg/checkHomeimg.ac?id="+clientUserHomeimg.getId()+"\')\">"+clientUserHomeimg.getImgName()+"</a>");
			}
			JSONArray clientUserHomeimgListJsonArray = DateJsonValueProcessor.listToJSONArray(clientUserHomeimgList);
			
			logger.debug("clientUserHomeimgListJsonArray.toString()=="+clientUserHomeimgListJsonArray.toString());
			response.getWriter().write(clientUserHomeimgListJsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "checkHomeimg", method = RequestMethod.GET)
	public ModelAndView checkHomeimg(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		ClientUserHomeimg clientUserHomeimg=securityService.get(ClientUserHomeimg.class, Integer.parseInt(id));
		request.setAttribute("clientUserHomeimg", clientUserHomeimg);
		return this.getModelAndView(SecurityUrls.CLIENT_USER_HOMEIMG_CHECK);
	}
	
	@RequestMapping(value = "compile", method = RequestMethod.GET)
	public ModelAndView compile(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		ClientUserHomeimg clientUserHomeimg=securityService.get(ClientUserHomeimg.class, Integer.parseInt(id));
		request.setAttribute("clientUserHomeimg", clientUserHomeimg);
		return this.getModelAndView(SecurityUrls.CLIENT_USER_HOMEIMG_COMPILE);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response) {

		try {
			String id = request.getParameter("id");
			ClientUserHomeimg clientUserHomeimg = securityService.getUnique(ClientUserHomeimg.class, Factor.create("id", C.Eq, Integer.parseInt(id)));
			securityService.remove(clientUserHomeimg);
			FileUtil.delete(Configuration.get("client.user.homeimg")+clientUserHomeimg.getImgUuid());
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"删除成功\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response) {
			try {
				String title= request.getParameter("title");
				String theme= request.getParameter("theme");
				String sort= request.getParameter("sort");
				String type= request.getParameter("type");
				String urlStr= request.getParameter("urlStr");
				String content= request.getParameter("content");
				
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");
		        if(ValidateUtil.isNull(file.getOriginalFilename())){
		        	response.getWriter().write("{\"code\":\"1\",\"msg\":\"文件名不正确\"}");
		        	return;
		        }
		        String originalFilename = file.getOriginalFilename();
		        String uuid = Identities.uuid2();
		        String uuidFileName = uuid+"."+originalFilename.split("\\.")[1];
		        logger.debug("uuidFileName=="+uuidFileName);
		        logger.debug("file.getName()=="+file.getName());
		        logger.debug("file.getOriginalFilename()=="+file.getOriginalFilename());
		        logger.debug("Configuration.get(\"client.user.homeimg\")+uuidFileName=="+Configuration.get("client.user.homeimg")+uuidFileName);
		        FileUtil.writeFile(Configuration.get("client.user.homeimg")+uuidFileName, file.getBytes());
				
				ClientUserHomeimg clientUserHomeimg = new ClientUserHomeimg();
				clientUserHomeimg.setContent(content);
				clientUserHomeimg.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				clientUserHomeimg.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				clientUserHomeimg.setImgUuid(uuidFileName);
				clientUserHomeimg.setImgName(file.getOriginalFilename());
				clientUserHomeimg.setSort(Integer.parseInt(sort));
				clientUserHomeimg.setTheme(theme);
				clientUserHomeimg.setTitle(title);
				clientUserHomeimg.setType(type);
				clientUserHomeimg.setUrlStr(urlStr);
				
				securityService.create(clientUserHomeimg);
				response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+clientUserHomeimg.getId()+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
				try {
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"保存失败\"}");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(HttpServletRequest request,HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String theme = request.getParameter("theme");
			String sort = request.getParameter("sort");
			String type = request.getParameter("type");
			String urlStr = request.getParameter("urlStr");
			String content = request.getParameter("content");
			
			ClientUserHomeimg clientUserHomeimg = securityService.getUnique(ClientUserHomeimg.class, Factor.create("id", C.Eq, Integer.parseInt(id)));
			String oldUuidFileName = clientUserHomeimg.getImgUuid();
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");
	        String uuidFileName = "";
	        if(!ValidateUtil.isNull(file.getOriginalFilename())){
	        	String originalFilename = file.getOriginalFilename();
		        String uuid = Identities.uuid2();
		        uuidFileName = uuid+"."+originalFilename.split("\\.")[1];
		        logger.debug("uuidFileName=="+uuidFileName);
		        logger.debug("file.getName()=="+file.getName());
		        logger.debug("file.getOriginalFilename()=="+file.getOriginalFilename());
		        logger.debug("Configuration.get(\"client.user.homeimg\")+uuidFileName=="+Configuration.get("client.user.homeimg")+uuidFileName);
		        FileUtil.delete(Configuration.get("client.user.homeimg")+oldUuidFileName);
		        FileUtil.writeFile(Configuration.get("client.user.homeimg")+uuidFileName, file.getBytes());
		        clientUserHomeimg.setImgUuid(uuidFileName);
				clientUserHomeimg.setImgName(file.getOriginalFilename());
			}
			
			clientUserHomeimg.setContent(content);
			clientUserHomeimg.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			clientUserHomeimg.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			clientUserHomeimg.setSort(Integer.parseInt(sort));
			clientUserHomeimg.setTheme(theme);
			clientUserHomeimg.setTitle(title);
			clientUserHomeimg.setType(type);
			clientUserHomeimg.setUrlStr(urlStr);
			
			securityService.update(clientUserHomeimg);
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+clientUserHomeimg.getId()+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().write("{\"code\":\"1\",\"msg\":\"保存失败\"}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
