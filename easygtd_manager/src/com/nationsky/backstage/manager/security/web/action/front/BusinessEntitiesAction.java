package com.nationsky.backstage.manager.security.web.action.front;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nationsky.backstage.core.Factor;
import com.nationsky.backstage.core.Factor.C;
import com.nationsky.backstage.core.web.action.BaseAction;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.BusinessCategories;
import com.nationsky.backstage.manager.security.bsc.dao.po.BusinessEntities;
import com.nationsky.backstage.manager.security.bsc.dao.po.Users;
import com.nationsky.backstage.manager.security.util.DateJsonValueProcessor;
import com.nationsky.backstage.manager.security.web.SecurityUrls;
import com.nationsky.backstage.util.HttpUtil;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/businessentities/")
public class BusinessEntitiesAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(BusinessEntitiesAction.class);
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
		StringBuffer resultNamesAndIds = new StringBuffer();
		resultNamesAndIds.append("[");
		String result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/business/keywordSearch.ac?keyword="+keyword);
		JSONObject jsonObject =JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data").getJSONObject(0).getJSONArray("result");
		for (int i = 0; i < jsonArray.size(); i++) {
			String name = jsonArray.getJSONObject(i).getString("name");
			if(i==(jsonArray.size()-1)){
				resultNamesAndIds.append("\""+name+"\"]");
			}else{
				resultNamesAndIds.append("\""+name+"\",");
			}
		}
		jsonArray.getJSONObject(0).getString("name");
		logger.debug("jsonArray="+resultNamesAndIds.toString());
		try {
			response.getWriter().write(resultNamesAndIds.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public void search(HttpServletRequest request,HttpServletResponse response) {
		String keyword = request.getParameter("term");
		String result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/business/keywordSearch.ac?keyword="+keyword);
		JSONObject jsonObject =JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data").getJSONObject(0).getJSONArray("result");
		Integer[] idArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			int id =  jsonArray.getJSONObject(i).getInt("id");
			idArray[i]=id;
			logger.debug("user id ="+id);
		}
		
		try {
			List<BusinessEntities> businessEntitieList = securityService.findList(BusinessEntities.class, 0, 10,null,Factor.create("id", C.In, idArray));
			JSONArray businessEntitieListJsonArray = DateJsonValueProcessor.listToJSONArray(businessEntitieList);
				
				logger.debug("communityCenterListJsonArray.toString()=="+businessEntitieListJsonArray.toString());
			response.getWriter().write(businessEntitieListJsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping(value = "getAllBusinessEntity", method = RequestMethod.GET)
	public void getAllBusinessEntity(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			 List<BusinessEntities> businessEntitieList = securityService.findList(BusinessEntities.class, 0, 10, "updatedAt:desc");
			 for (BusinessEntities businessEntitie : businessEntitieList) {
				 //onClick="to_other_page('user/add_user.jhtml')"
				 //user.setName("<a href=\"#\" onclick=\"show_common_user_info(\""+user.getId()+"\");\">"+user.getName()+"</a>");
				 //user.setName("<a href=\"#\" onClick=\"to_other_page(\'user/check_user.jhtml?id="+user.getId()+"\')\">"+user.getName()+"</a>");
				 businessEntitie.setName("<a href=\"#\" onClick=\"to_other_page(\'businessentities/checkBusinessEntities.ac?id="+businessEntitie.getId()+"\')\">"+businessEntitie.getName()+"</a>");
			}
			 JSONArray businessEntitieListJsonArray = DateJsonValueProcessor.listToJSONArray(businessEntitieList);
				
				logger.debug("businessEntitieListJsonArray.toString()=="+businessEntitieListJsonArray.toString());
			response.getWriter().write(businessEntitieListJsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "checkBusinessEntities", method = RequestMethod.GET)
	public ModelAndView checkBusinessEntities(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		BusinessEntities businessEntity=securityService.get(BusinessEntities.class, Integer.parseInt(id));
		if(businessEntity.getBusinessCategoryId()!=null){
			BusinessCategories businessCategory = securityService.get(BusinessCategories.class, businessEntity.getBusinessCategoryId());
			request.setAttribute("businessCategory", businessCategory);
		}
		request.setAttribute("businessEntity", businessEntity);
		return this.getModelAndView(SecurityUrls.BUSINESS_ENTITY_CHECK);
	}
	
	@RequestMapping(value = "compile", method = RequestMethod.GET)
	public ModelAndView compile(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		BusinessEntities businessEntity=securityService.get(BusinessEntities.class, Integer.parseInt(id));
		if(businessEntity.getBusinessCategoryId()!=null){
			BusinessCategories businessCategory = securityService.get(BusinessCategories.class, businessEntity.getBusinessCategoryId());
			request.setAttribute("businessCategory", businessCategory);
		}
		if(businessEntity.getCreatorId()!=null){
			Users user = securityService.get(Users.class,businessEntity.getCreatorId());
			request.setAttribute("user", user);
			
		}
		request.setAttribute("businessEntity", businessEntity);
		return this.getModelAndView(SecurityUrls.BUSINESS_ENTITY_REDACT);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			String id = request.getParameter("id");
			securityService.remove(BusinessEntities.class, Integer.parseInt(id));
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"删除成功\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response) {
			try {
				String name= request.getParameter("name");
				String address= request.getParameter("address");
				String latlng= request.getParameter("latlng");
				String phone= request.getParameter("phone");
				String businessCategoryId= request.getParameter("businessCategoryId");
				String creatorNameAndPhone = request.getParameter("creatorNameAndPhone");
				String intro = request.getParameter("intro");//介绍
				List<BusinessEntities> businessEntitiesList = securityService.findList(BusinessEntities.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, name));
				if(!ValidateUtil.isNullCollection(businessEntitiesList)){//如果不是空集合
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户名称已存在\"}");
					return;
				}
				Users user = null;
				if(ValidateUtil.isNotNull(creatorNameAndPhone)){
					if(ValidateUtil.isEquals(creatorNameAndPhone, "--")){
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户创建者不正确\"}");
						return;
					}else{
						String creatorPhone = creatorNameAndPhone.split("--")[1];
						user = securityService.getUnique(Users.class, Factor.create("phoneNumber", C.Eq, creatorPhone));
						if(user==null){
							response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户创建者不存在\"}");
							return;
						}
					}
				}
				BusinessCategories BusinessCategories = securityService.get(BusinessCategories.class, Integer.parseInt(businessCategoryId));
				BusinessEntities businessEntities = new BusinessEntities();
				businessEntities.setAddress(address);
				businessEntities.setBusinessCategoryId(Integer.parseInt(businessCategoryId));
				businessEntities.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				businessEntities.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				if(user!=null){
					businessEntities.setCreatorId(user.getId());
				}
				businessEntities.setLatitude(Double.parseDouble(latlng.split(",")[0]));
				businessEntities.setLongitude(Double.parseDouble(latlng.split(",")[1]));
				businessEntities.setName(name);
				businessEntities.setPhone(phone);
				businessEntities.setIntro(intro);
				businessEntities.setTag(BusinessCategories.getName());
				securityService.create(businessEntities);
				System.out.println("businessEntities.getId()==="+businessEntities.getId());
				response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+businessEntities.getId()+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(HttpServletRequest request,HttpServletResponse response) {
		try {
			String id= request.getParameter("id");
			String name= request.getParameter("name");
			String address= request.getParameter("address");
			String latlng= request.getParameter("latlng");
			String phone= request.getParameter("phone");
			String businessCategoryId= request.getParameter("businessCategoryId");
			String creatorNameAndPhone = request.getParameter("creatorNameAndPhone");
			String intro = request.getParameter("intro");
			
			List<BusinessEntities> businessEntitiesList = securityService.findList(BusinessEntities.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, name), Factor.create("id", C.Ne, Integer.parseInt(id)));
			if(!ValidateUtil.isNullCollection(businessEntitiesList)){//如果不是空集合
				response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户名称已存在\"}");
				return;
			}
			
			Users user = null;
			if(ValidateUtil.isNotNull(creatorNameAndPhone)){
				if(ValidateUtil.isEquals(creatorNameAndPhone, "--")){
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户创建者不正确\"}");
					return;
				}else{
					String creatorPhone = creatorNameAndPhone.split("--")[1];
					user = securityService.getUnique(Users.class, Factor.create("phoneNumber", C.Eq, creatorPhone));
					if(user==null){
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户创建者不存在\"}");
						return;
					}
				}
			}
			
			BusinessEntities businessEntities = securityService.get(BusinessEntities.class, Integer.parseInt(id));
			BusinessCategories newbusinessCategories = securityService.get(BusinessCategories.class, Integer.parseInt(businessCategoryId));
			if(businessEntities.getBusinessCategoryId()!=null){
				BusinessCategories oldBusinessCategories = securityService.get(BusinessCategories.class,businessEntities.getBusinessCategoryId());
				businessEntities.setTag(businessEntities.getTag().replace(oldBusinessCategories.getName(), newbusinessCategories.getName()));
			}else{
				businessEntities.setTag(businessEntities.getTag()+" "+newbusinessCategories.getName());
			}
			
			businessEntities.setAddress(address);
			businessEntities.setBusinessCategoryId(Integer.parseInt(businessCategoryId));
			businessEntities.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			businessEntities.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			if(user!=null){
				businessEntities.setCreatorId(user.getId());
			}
			businessEntities.setLatitude(Double.parseDouble(latlng.split(",")[0]));
			businessEntities.setLongitude(Double.parseDouble(latlng.split(",")[1]));
			businessEntities.setName(name);
			businessEntities.setPhone(phone);
			businessEntities.setIntro(intro);
			
			securityService.update(businessEntities);
			System.out.println("businessEntities.getId()==="+businessEntities.getId());
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+businessEntities.getId()+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
