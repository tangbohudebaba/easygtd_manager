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

import com.nationsky.backstage.core.Factor;
import com.nationsky.backstage.core.Factor.C;
import com.nationsky.backstage.core.web.action.BaseAction;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.Intro;
import com.nationsky.backstage.manager.security.bsc.dao.po.TaskInfo;
import com.nationsky.backstage.manager.security.bsc.dao.po.UsersFavorites;
import com.nationsky.backstage.manager.security.util.DateJsonValueProcessor;
import com.nationsky.backstage.manager.security.web.SecurityUrls;
import com.nationsky.backstage.util.FileUtil;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/wuye/")
public class WuyeAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(WuyeAction.class);
	@Autowired
	private ISecurityService securityService;
	
	
	/**
	 * 搜索框搜索补全
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "searchAutocomplete", method = RequestMethod.GET)
	public void searchAutocomplete(HttpServletRequest request,HttpServletResponse response) {
		String keyword = request.getParameter("term");
		List<TaskInfo> introList = securityService.findList(TaskInfo.class, 0, 10, null, Factor.create("title", C.Like,  "%"+keyword+"%"));
		
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
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public void search(HttpServletRequest request,HttpServletResponse response) {
		try {
			String keyword = request.getParameter("term");
			List<TaskInfo> introList = securityService.findList(TaskInfo.class, 0, 10, null, Factor.create("title", C.Like,  "%"+keyword+"%"));
//			for (TaskInfo intro : introList) {
//				intro.setTitle("<a href=\"#\" onClick=\"to_other_page(\'wuye/checkWuye.ac?id="+intro.getId()+"\')\">"+intro.getTitle()+"</a>");
//			}
			JSONArray introListJsonArray = DateJsonValueProcessor.listToJSONArray(introList);
				
				logger.debug("introListJsonArray.toString()=="+introListJsonArray.toString());
			response.getWriter().write(introListJsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "getAllWuye", method = RequestMethod.GET)
	public void getAllBusinessEntity(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			List<TaskInfo> introList = securityService.findList(TaskInfo.class, 0, 10, "updatedAt:desc");
//			for (TaskInfo intro : introList) {
//				
//				intro.setTitle("<a href=\"#\" onClick=\"to_other_page(\'wuye/checkWuye.ac?id="+intro.getId()+"\')\">"+intro.getTitle()+"</a>");
//			}
			JSONArray introListJsonArray = DateJsonValueProcessor.listToJSONArray(introList);
				
			logger.debug("introListJsonArray.toString()=="+introListJsonArray.toString());
			response.getWriter().write(introListJsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "checkWuye", method = RequestMethod.GET)
	public ModelAndView checkBusinessEntities(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		TaskInfo intro=securityService.get(TaskInfo.class, Integer.parseInt(id));
		request.setAttribute("wuye", intro);
		return this.getModelAndView(SecurityUrls.WUYE_CHECK);
	}
	
	@RequestMapping(value = "compile", method = RequestMethod.GET)
	public ModelAndView compile(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Intro intro=securityService.get(Intro.class, Integer.parseInt(id));
		request.setAttribute("wuye", intro);
		return this.getModelAndView(SecurityUrls.WUYE_COMPILE);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response) {
//		用户收藏的商户0/
//		用户收藏的小区1/
//		用户关注的用户2/
//		用户关注的活动3/
//		用户报名的活动4/
//		商户管理员管理的商户5/
//		居委会用户管理的居委会6/
//		物业用户管理的物业6/
//		居委会管理的小区7/
//		物业管理的小区7/
//		社区代表管理的小区8/
		try {
			String id = request.getParameter("id");
			securityService.remove(Intro.class, Integer.parseInt(id));
			List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, 
					Factor.create("type", C.Eq,7),
					Factor.create("userId", C.Eq, Integer.parseInt(id)));
			for (UsersFavorites usersFavorites : usersFavoritesList) {
					securityService.remove(usersFavorites);
			}
			
			List<UsersFavorites> usersFavoritesList2 = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, 
					Factor.create("type", C.Eq,6),
					Factor.create("objectId", C.Eq, Integer.parseInt(id)));
			for (UsersFavorites usersFavorites : usersFavoritesList2) {
					securityService.remove(usersFavorites);
			}
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"删除成功\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response) {
			try {
				String title= request.getParameter("title");
				String phoneNumber= request.getParameter("phoneNumber");
				String content= request.getParameter("content");
				String address= request.getParameter("address");
				String latlng= request.getParameter("latlng");
				List<Intro> introList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, title), Factor.create("introType", C.Eq, 2));
				if(!ValidateUtil.isNullCollection(introList)){//如果不是空集合
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"物业名称已存在\"}");
					return;
				}
				Intro intro = new Intro();
				intro.setAddress(address);
				intro.setContent(content);
				intro.setCreateTime(new Timestamp(System.currentTimeMillis()));
				intro.setIntroType(2);
				intro.setPhoneNumber(phoneNumber);
				intro.setTitle(title);
				try {
					if (ValidateUtil.isNotNull(latlng)) {
						intro.setLatitude(Double.parseDouble(latlng.split(",")[0]));
						intro.setLongitude(Double.parseDouble(latlng.split(",")[1]));
					}
				} catch (Exception e) {
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"位置坐标不正确\"}");
					return;
				}
				
				securityService.create(intro);
				System.out.println("businessEntities.getId()==="+intro.getId());
				response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+intro.getId()+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(HttpServletRequest request,HttpServletResponse response) {
		try {
		String id= request.getParameter("id");
		String title= request.getParameter("title");
		String phoneNumber= request.getParameter("phoneNumber");
		String content= request.getParameter("content");
		String address= request.getParameter("address");
		String latlng= request.getParameter("latlng");
		List<Intro> introList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, title), Factor.create("id", C.Ne, Integer.parseInt(id)), Factor.create("introType", C.Eq, 2));
		if(!ValidateUtil.isNullCollection(introList)){//如果不是空集合
			response.getWriter().write("{\"code\":\"1\",\"msg\":\"物业名称已存在\"}");
			return;
		}
		Intro wuye = securityService.get(Intro.class, Integer.parseInt(id));
		wuye.setAddress(address);
		wuye.setContent(content);
		wuye.setCreateTime(new Timestamp(System.currentTimeMillis()));
		wuye.setIntroType(2);
		wuye.setPhoneNumber(phoneNumber);
		wuye.setTitle(title);
		try {
			if (ValidateUtil.isNotNull(latlng)) {
				wuye.setLatitude(Double.parseDouble(latlng.split(",")[0]));
				wuye.setLongitude(Double.parseDouble(latlng.split(",")[1]));
			}else{
				wuye.setLatitude(null);
				wuye.setLongitude(null);
			}
		} catch (Exception e) {
			response.getWriter().write("{\"code\":\"1\",\"msg\":\"位置坐标不正确\"}");
			return;
		}
		
		
		securityService.update(wuye);
		System.out.println("wuye.getId()==="+wuye.getId());
		response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+wuye.getId()+"\"}");
	} catch (IOException e) {
		e.printStackTrace();
	}}

	@RequestMapping(value = "batchSave", method = RequestMethod.POST)
	public void batchSave(HttpServletRequest request, HttpServletResponse response) {
//		// 创建一个通用的多部分解析器.
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
//				request.getSession().getServletContext());
//		// 设置编码
//		commonsMultipartResolver.setDefaultEncoding("utf-8");
//		// 判断 request 是否有文件上传,即多部分请求...
//		if (commonsMultipartResolver.isMultipart(request)) {
//			// 转换成多部分request
//			MultipartHttpServletRequest multipartRequest = commonsMultipartResolver
//					.resolveMultipart(request);
//
//			// file 是指 文件上传标签的 name=值
//			// 根据 name 获取上传的文件...
//			MultipartFile file = multipartRequest.getFile("file");
//
//			// 上传后记录的文件...
//			File imageFile = new File("c:\\a.xlsx");
//			// 上传...
//			try {
//				file.transferTo(imageFile);
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
		
		String filePath="c:\\";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");
        System.out.println(filePath+"a.xlsx");
        FileUtil.writeFile(filePath+"a.xlsx", file.getBytes());
		try {
			response.getWriter().write("{\"error\":\"eeee2\",\"msg\":\"ffffff2\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
