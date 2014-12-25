package com.nationsky.backstage.manager.security.web.action.front;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.nationsky.backstage.manager.security.ManagerCommonConstants;
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.BusinessEntities;
import com.nationsky.backstage.manager.security.bsc.dao.po.CodeUserType;
import com.nationsky.backstage.manager.security.bsc.dao.po.CommunityCenters;
import com.nationsky.backstage.manager.security.bsc.dao.po.Intro;
import com.nationsky.backstage.manager.security.bsc.dao.po.Opinion;
import com.nationsky.backstage.manager.security.bsc.dao.po.UserInfo;
import com.nationsky.backstage.manager.security.bsc.dao.po.Users;
import com.nationsky.backstage.manager.security.bsc.dao.po.UsersFavorites;
import com.nationsky.backstage.manager.security.util.DateJsonValueProcessor;
import com.nationsky.backstage.manager.security.web.SecurityUrls;
import com.nationsky.backstage.util.HttpUtil;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/user/")
public class UserAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static  Map<Integer,Integer> usersAndUsersFavoritesMap = new HashMap<Integer, Integer>();
	static  Map<Integer,String> userTypeMap = new HashMap<Integer, String>();
	static {
		usersAndUsersFavoritesMap.put(5, 5);
		usersAndUsersFavoritesMap.put(6, 8);
		usersAndUsersFavoritesMap.put(7, 6);
		usersAndUsersFavoritesMap.put(8, 6);
		
		userTypeMap.put(1, "系统管理员");
		userTypeMap.put(2, "普通用户 ");
		userTypeMap.put(3, "国信用户");
		userTypeMap.put(4, "中国人寿用户");
		userTypeMap.put(5, "商户管理员");
		userTypeMap.put(6, "社区代表 ");
		userTypeMap.put(7, "居委会管理员");
		userTypeMap.put(8, "物业管理员");
	}
	
	
	@Autowired
	private ISecurityService securityService;
	
	@RequestMapping(value = "searchAutocomplete", method = RequestMethod.GET)
	public void searchAutocomplete(HttpServletRequest request,HttpServletResponse response) {
		String keyword = request.getParameter("term");
		StringBuffer resultNamesAndIds = new StringBuffer();
		resultNamesAndIds.append("[");
		String result = "";
		String phoneNumberRegex="^(1(3|5|8)[0-9])\\d{8}$";//手机号正则
		if(keyword.matches(phoneNumberRegex)){
			result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/users/keywordSearch.ac?factor=phone_number%20must%20"+keyword);
		}else{
			result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/users/keywordSearch.ac?keyword="+keyword);
		}
		JSONObject jsonObject =JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data").getJSONObject(0).getJSONArray("result");
		for (int i = 0; i < jsonArray.size(); i++) {
			String phoneNumber =  jsonArray.getJSONObject(i).getString("phone_number");
			String name = jsonArray.getJSONObject(i).getString("name");
			if(i==(jsonArray.size()-1)){
				resultNamesAndIds.append("\""+name+"--"+phoneNumber+"\"]");
			}else{
				resultNamesAndIds.append("\""+name+"--"+phoneNumber+"\",");
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
		String keyword = request.getParameter("term");
		String phoneNumberRegex="^(1(3|5|8)[0-9])\\d{8}$";//手机号正则
		List<UserInfo> userList = null;
		if(keyword.matches(phoneNumberRegex)){
			userList = securityService.findList(UserInfo.class, 0, 10, null, Factor.create("phone", C.Eq,  keyword));
		}else{
			userList = securityService.findList(UserInfo.class, 0, 10, null, Factor.create("name", C.Like,  "%"+keyword+"%"));
		}
		try {
			if(ValidateUtil.isNullCollection(userList)){
				response.getWriter().write(ManagerCommonConstants.RESPONSE_WITHOUT_AESULT_MSG);
				return;
			}
			 
//			 for (UserInfo user : userList) {
//				 //onClick="to_other_page('user/add_user.jhtml')"
//				 //user.setName("<a href=\"#\" onclick=\"show_common_user_info(\""+user.getId()+"\");\">"+user.getName()+"</a>");
//				 //user.setName("<a href=\"#\" onClick=\"to_other_page(\'user/check_user.jhtml?id="+user.getId()+"\')\">"+user.getName()+"</a>");
//				 user.setPhone("<a href=\"#\" onClick=\"to_other_page(\'user/checkUser.ac?id="+user.getId()+"\')\">"+user.getPhone()+"</a>");
//			}
			JSONArray userJsonArray = DateJsonValueProcessor.listToJSONArray(userList);
			
			logger.debug("userJsonArray.toString()=="+userJsonArray.toString());
			response.getWriter().write(userJsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "getUserTypeCount", method = RequestMethod.GET)
	public void getUserTypeCount(HttpServletRequest request,HttpServletResponse response) {
			
		 try {
			 StringBuffer userTypeCountSB = new StringBuffer();
			 int a=securityService.getCount(UserInfo.class);
			 userTypeCountSB.append(a);
			response.getWriter().write(userTypeCountSB.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
	public void getAllUsers(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			List<UserInfo> userList = new ArrayList<UserInfo>();
			userList = securityService.findList(UserInfo.class, 0, 10, "updatedAt:desc");
			
//			 for (UserInfo user : userList) {
//				 //onClick="to_other_page('user/add_user.jhtml')"
//				 //user.setName("<a href=\"#\" onclick=\"show_common_user_info(\""+user.getId()+"\");\">"+user.getName()+"</a>");
//				 //user.setName("<a href=\"#\" onClick=\"to_other_page(\'user/check_user.jhtml?id="+user.getId()+"\')\">"+user.getName()+"</a>");
//				 user.setPhone("<a href=\"#\" onClick=\"to_other_page(\'user/checkUser.ac?id="+user.getId()+"\')\">"+user.getPhone()+"</a>");
//			}
			JSONArray userJsonArray = DateJsonValueProcessor.listToJSONArray(userList);
			
			logger.debug("userJsonArray.toString()=="+userJsonArray.toString());
			response.getWriter().write(userJsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "checkUser", method = RequestMethod.GET)
	public ModelAndView checkUser(HttpServletRequest request,HttpServletResponse response) {
		String userId = request.getParameter("id");
		Users user=securityService.get(Users.class, Integer.parseInt(userId));
		request.setAttribute("user", user);
		CodeUserType codeUserType = securityService.get(CodeUserType.class, user.getUserTpye());
		user.setUserTpyeName(codeUserType.getName());
		return this.getModelAndView(SecurityUrls.USER_CHECK);
	}
	
	@RequestMapping(value = "compile", method = RequestMethod.GET)
	public ModelAndView compile(HttpServletRequest request,HttpServletResponse response) {
		String userId = request.getParameter("id");
		Users user=securityService.get(Users.class, Integer.parseInt(userId));
		CodeUserType codeUserType = securityService.get(CodeUserType.class, user.getUserTpye());
		user.setUserTpyeName(codeUserType.getName());
		
		Integer UsersFavoritesType = usersAndUsersFavoritesMap.get(user.getUserTpye());
		
		List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null,Factor.create("userId", C.Eq, user.getId()),Factor.create("type", C.Eq, UsersFavoritesType));
		if(!ValidateUtil.isNullCollection(usersFavoritesList)&&usersFavoritesList.get(0)!=null){
			UsersFavorites usersFavorites= usersFavoritesList.get(0);
			if(user.getUserTpye()==5){//商户管理员
				BusinessEntities businessEntities = securityService.get(BusinessEntities.class, usersFavorites.getObjectId());
				request.setAttribute("businessEntities", businessEntities.getName());
			}else if(user.getUserTpye()==6){//社区代表
				Integer[] CommunityCenterIdArray=new Integer[usersFavoritesList.size()];
				for (int i = 0; i < usersFavoritesList.size(); i++) {
					CommunityCenterIdArray[i]=usersFavoritesList.get(i).getObjectId();
				}
				List<CommunityCenters> communityCenterList = securityService.findList(CommunityCenters.class, 0, Integer.MAX_VALUE, null, Factor.create("id", C.In, CommunityCenterIdArray));
				StringBuffer communityCenterNames=new StringBuffer();
				for (int i = 0; i < communityCenterList.size(); i++) {
					if(ValidateUtil.isNotNull(communityCenterList.get(i).getName())){
						if(i==communityCenterList.size()-1){
							communityCenterNames.append(communityCenterList.get(i).getName());
						}else{
							communityCenterNames.append(communityCenterList.get(i).getName()+"---");
						}
					}
				}
				request.setAttribute("communityCenterNames", communityCenterNames);
			}else if(user.getUserTpye()==7){//居委会管理员
				Intro intro = securityService.get(Intro.class, usersFavorites.getObjectId());
				request.setAttribute("juweihui", intro.getTitle());
			}else if(user.getUserTpye()==8){//物业管理员
				Intro intro = securityService.get(Intro.class, usersFavorites.getObjectId());
				request.setAttribute("wuye", intro.getTitle());
			}
			
		}
		
		request.setAttribute("user", user);
		return this.getModelAndView(SecurityUrls.USER_COMPILE);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			String id = request.getParameter("id");
			securityService.remove(Users.class, Integer.parseInt(id));
			
			List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, Factor.create("type", C.In, new Integer[]{5,6,8}),Factor.create("userId", C.Eq, Integer.parseInt(id)));
			for (UsersFavorites usersFavorites : usersFavoritesList) {
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
				String  name= request.getParameter("realname");
				String  gender= request.getParameter("gender");
				String  nickname= request.getParameter("nickname");
				String  birthdate= request.getParameter("birthdate");
				String  email= request.getParameter("email");
				String  info= request.getParameter("info");
				String  userTpye= request.getParameter("employee_type");
				String  business_entities= request.getParameter("business_entities");
				String  community_centers= request.getParameter("community_centers");
				String  cellphone= request.getParameter("cellphone");
				String  juweihui= request.getParameter("juweihui");
				String  wuye= request.getParameter("wuye");
				
				List<Users> usersList = securityService.findList(Users.class, 0, Integer.MAX_VALUE, null, Factor.create("phoneNumber", C.Eq, cellphone));
				if(!ValidateUtil.isNullCollection(usersList)){
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"手机号已经存在\"}");
					return;
				}
				
				List<BusinessEntities> businessEntitiesList = null;
				List<CommunityCenters> communityCentersList = new ArrayList<CommunityCenters>();
				List<Intro> juweihuiList = null;
				List<Intro> wuyeList = null;
				if(ValidateUtil.isEquals("5", userTpye)){//商户管理员
					businessEntitiesList = securityService.findList(BusinessEntities.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, business_entities));
					if(ValidateUtil.isNullCollection(businessEntitiesList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户不存在\"}");
						return;
					}
				}else if(ValidateUtil.isEquals("6", userTpye)&&ValidateUtil.isNotNull(community_centers)){//社区代表
					String[] community_centerArry = community_centers.split("---");
					for (String communityCenter : community_centerArry) {
						if(ValidateUtil.isNotNull(communityCenter)){
							List<CommunityCenters> communityCenterItemList = securityService.findList(CommunityCenters.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, communityCenter));
							if(ValidateUtil.isNullCollection(communityCenterItemList)){//如果是空集合
								response.getWriter().write("{\"code\":\"1\",\"msg\":\""+communityCenter+"小区不存在\"}");
								return;
							}else{
								communityCentersList.addAll(communityCenterItemList);
							}
						}
					}
					
					
				}else if(ValidateUtil.isEquals("7", userTpye)){//居委会管理员
					juweihuiList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, juweihui), Factor.create("introType", C.Eq, 1));
					if(ValidateUtil.isNullCollection(juweihuiList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"居委会不存在\"}");
						return;
					}
				}else if(ValidateUtil.isEquals("8", userTpye)){//物业管理员
					wuyeList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, wuye), Factor.create("introType", C.Eq, 2));
					if(ValidateUtil.isNullCollection(wuyeList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"物业不存在\"}");
						return;
					}
				}
				
				Users user = new Users();
				user.setPasswordDigest("e10adc3949ba59abbe56e057f20f883e");
				user.setBio(info);
				try {
					user.setBirthdate(new java.sql.Date(dateFormat.parse(birthdate).getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"时间格式错误，保存失败\"}");
				}
				user.setBuzzSwitch(1);
				user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				user.setEmail(email);
				user.setGender(gender);
				user.setHomepageSwitch(1);
				user.setMessageSwitch(1);
				user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				user.setName(name);
				user.setNickname(nickname);
				user.setPhoneNumber(cellphone);
				user.setUserTpye(Integer.parseInt(userTpye));
				user.setTag(userTypeMap.get(Integer.parseInt(userTpye)));
				securityService.create(user);
				System.out.println("user.getId()==="+user.getId());
				if(ValidateUtil.isEquals("5", userTpye)||ValidateUtil.isEquals("6", userTpye)||ValidateUtil.isEquals("7", userTpye)||ValidateUtil.isEquals("8", userTpye)){
//					用户收藏的商户0/
//					用户收藏的小区1/
//					用户关注的用户2/
//					用户关注的活动3/
//					用户报名的活动4/
//					商户管理员管理的商户5/
//					居委会用户管理的居委会6/
//					物业用户管理的物业6/
//					居委会管理的小区7/
//					物业管理的小区7/
//					社区代表管理的小区8/
					
					//intro表 1 居委会;2 物业
					UsersFavorites u = new UsersFavorites();
					u.setCreateTime(new Timestamp(System.currentTimeMillis()));
					if(ValidateUtil.isEquals("5", userTpye)){//商户管理员
						u.setType(5);
						u.setUserId(user.getId());
						u.setObjectId(businessEntitiesList.get(0).getId());
						
						
					}else if(ValidateUtil.isEquals("6", userTpye)){//社区代表 
						if(ValidateUtil.isNull(community_centers)||ValidateUtil.isNullCollection(communityCentersList)){
							response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\"}");
							return;
						}
						u.setType(8);
						u.setUserId(user.getId());
						for (CommunityCenters communityCenter : communityCentersList) {
							u.setObjectId(communityCenter.getId());
							securityService.create(u);
						}
						response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\"}");
						return;
					}else if(ValidateUtil.isEquals("7", userTpye)){//居委会管理员
						//居委会用户管理的居委会6/
						u.setType(6);
						u.setUserId(user.getId());
						u.setObjectId(juweihuiList.get(0).getId());//居委会ID
					}else if(ValidateUtil.isEquals("8", userTpye)){//物业管理员
						//物业用户管理的物业6/
						u.setType(6);
						u.setUserId(user.getId());
						u.setObjectId(wuyeList.get(0).getId());//物业ID
					}
					
					securityService.create(u);
				}
				response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(HttpServletRequest request,HttpServletResponse response) {
			try {
				String  id = request.getParameter("id");
				String  name= request.getParameter("realname");
				String  gender= request.getParameter("gender");
				String  nickname= request.getParameter("nickname");
				String  birthdate= request.getParameter("birthdate");
				String  email= request.getParameter("email");
				String  info= request.getParameter("info");
				String  userTpye= request.getParameter("employee_type");
				String  business_entities= request.getParameter("business_entities");
				String  community_centers= request.getParameter("community_centers");
				String  cellphone= request.getParameter("cellphone");
				String  juweihui= request.getParameter("juweihui");
				String  wuye= request.getParameter("wuye");
				List<Users> usersList = securityService.findList(Users.class, 0, Integer.MAX_VALUE, null, Factor.create("phoneNumber", C.Eq, cellphone),Factor.create("id", C.Ne, Integer.parseInt(id)));
				if(!ValidateUtil.isNullCollection(usersList)){
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"手机号已经存在\"}");
					return;
				}
				
				List<BusinessEntities> businessEntitiesList = null;
				List<CommunityCenters> communityCentersList = new ArrayList<CommunityCenters>();
				List<Intro> juweihuiList = null;
				List<Intro> wuyeList = null;
				if(ValidateUtil.isEquals("5", userTpye)){//商户管理员
					businessEntitiesList = securityService.findList(BusinessEntities.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, business_entities));
					if(ValidateUtil.isNullCollection(businessEntitiesList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"商户不存在\"}");
						return;
					}
				}else if(ValidateUtil.isEquals("6", userTpye)&&ValidateUtil.isNotNull(community_centers)){//社区代表
					String[] community_centerArry = community_centers.split("---");
					for (String communityCenter : community_centerArry) {
						if(ValidateUtil.isNotNull(communityCenter)){
							List<CommunityCenters> communityCenterItemList = securityService.findList(CommunityCenters.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, communityCenter));
							if(ValidateUtil.isNullCollection(communityCenterItemList)){//如果是空集合
								response.getWriter().write("{\"code\":\"1\",\"msg\":\""+communityCenter+"小区不存在\"}");
								return;
							}else{
								communityCentersList.addAll(communityCenterItemList);
							}
						}
					}
					
					
				}else if(ValidateUtil.isEquals("7", userTpye)){//居委会管理员
					juweihuiList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, juweihui), Factor.create("introType", C.Eq, 1));
					if(ValidateUtil.isNullCollection(juweihuiList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"居委会不存在\"}");
						return;
					}
				}else if(ValidateUtil.isEquals("8", userTpye)){//物业管理员
					wuyeList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, wuye), Factor.create("introType", C.Eq, 2));
					if(ValidateUtil.isNullCollection(wuyeList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"物业不存在\"}");
						return;
					}
				}
				
				
				
				Users user = securityService.get(Users.class , Integer.parseInt(id));
				if(ValidateUtil.isNotNull(user.getTag())){
					user.setTag(user.getTag().replace(userTypeMap.get(user.getUserTpye()),userTypeMap.get(Integer.parseInt(userTpye))));
				}else{
					user.setTag(userTypeMap.get(Integer.parseInt(userTpye)));
				}
				//Users user = new Users();
				//user.setPasswordDigest("e10adc3949ba59abbe56e057f20f883e");
				user.setBio(info);
				try {
					user.setBirthdate(new java.sql.Date(dateFormat.parse(birthdate).getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"时间格式错误，保存失败\"}");
				}
				//user.setBuzzSwitch(1);
				//user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				user.setEmail(email);
				user.setGender(gender);
				//user.setHomepageSwitch(1);
				//user.setMessageSwitch(1);
				user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				user.setName(name);
				user.setNickname(nickname);
				user.setPhoneNumber(cellphone);
				user.setUserTpye(Integer.parseInt(userTpye));
				
				securityService.update(user);
				System.out.println("user.getId()==="+user.getId());
				List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, Factor.create("type", C.In, new Integer[]{5,6,8}),Factor.create("userId", C.Eq, user.getId()));
				for (UsersFavorites usersFavorites : usersFavoritesList) {
					securityService.remove(usersFavorites);
				}
				if(ValidateUtil.isEquals("5", userTpye)||ValidateUtil.isEquals("6", userTpye)||ValidateUtil.isEquals("7", userTpye)||ValidateUtil.isEquals("8", userTpye)){
//					用户收藏的商户0/
//					用户收藏的小区1/
//					用户关注的用户2/
//					用户关注的活动3/
//					用户报名的活动4/
//					商户管理员管理的商户5/
//					居委会用户管理的居委会6/
//					物业用户管理的物业6/
//					居委会管理的小区7/
//					物业管理的小区7/
//					社区代表管理的小区8/
					
					//intro表 1 居委会;2 物业
					UsersFavorites u = new UsersFavorites();
					u.setCreateTime(new Timestamp(System.currentTimeMillis()));
					if(ValidateUtil.isEquals("5", userTpye)){//商户管理员
						u.setType(5);
						u.setUserId(user.getId());
						u.setObjectId(businessEntitiesList.get(0).getId());
						
						
					}else if(ValidateUtil.isEquals("6", userTpye)){//社区代表 
						if(ValidateUtil.isNull(community_centers)||ValidateUtil.isNullCollection(communityCentersList)){
							response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\"}");
							return;
						}
						u.setType(8);
						u.setUserId(user.getId());
						for (CommunityCenters communityCenter : communityCentersList) {
							u.setObjectId(communityCenter.getId());
							securityService.create(u);
						}
						response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\"}");
						return;
					}else if(ValidateUtil.isEquals("7", userTpye)){//居委会管理员
						//居委会用户管理的居委会6/
						u.setType(6);
						u.setUserId(user.getId());
						u.setObjectId(juweihuiList.get(0).getId());//居委会ID
					}else if(ValidateUtil.isEquals("8", userTpye)){//物业管理员
						//物业用户管理的物业6/
						u.setType(6);
						u.setUserId(user.getId());
						u.setObjectId(wuyeList.get(0).getId());//物业ID
					}
					securityService.create(u);
				}
				response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	}
	
	@RequestMapping(value = "getAllOpinion", method = RequestMethod.GET)
	public void getAllBusinessEntity(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			 List<Opinion> opinionList = securityService.findList(Opinion.class, 0, Integer.MAX_VALUE, "createdAt:desc");
			 for (Opinion opinion : opinionList) {
				 UserInfo user = securityService.get(UserInfo.class,opinion.getUserId());
				 opinion.setUserName(user.getName());
			}
			 JSONArray opinionListJsonArray = DateJsonValueProcessor.listToJSONArray(opinionList);
				
				logger.debug("opinionListJsonArray.toString()=="+opinionListJsonArray.toString());
			response.getWriter().write(opinionListJsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		String result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/users/keywordSearch.ac?keyword=唐伯虎");
//		System.out.println(result);
		String phoneNumberRegex="^(1(3|5|8)[0-9])\\d{8}$";//手机号正则
		boolean b = "18611866642".matches(phoneNumberRegex);
		System.out.println(b);
	}
	
	
}
