package com.nationsky.backstage.manager.security.web.action.front;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.nationsky.backstage.manager.security.bsc.ISecurityService;
import com.nationsky.backstage.manager.security.bsc.dao.po.CommunityCenters;
import com.nationsky.backstage.manager.security.bsc.dao.po.Intro;
import com.nationsky.backstage.manager.security.bsc.dao.po.UsersFavorites;
import com.nationsky.backstage.manager.security.util.DateJsonValueProcessor;
import com.nationsky.backstage.manager.security.web.SecurityUrls;
import com.nationsky.backstage.util.HttpUtil;
import com.nationsky.backstage.util.ValidateUtil;

@Controller
@RequestMapping(value = "/manager/community_center/")
public class CommunityCenterAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(CommunityCenterAction.class);
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static  Map<Integer,Integer> usersAndUsersFavoritesMap = new HashMap<Integer, Integer>();
	static  Map<Integer,String> userTypeMap = new HashMap<Integer, String>();
	
	
	@Autowired
	private ISecurityService securityService;
	
	@RequestMapping(value = "searchAutocomplete", method = RequestMethod.GET)
	public void searchAutocomplete(HttpServletRequest request,HttpServletResponse response) {
		String keyword = request.getParameter("term");
		StringBuffer resultNamesAndIds = new StringBuffer();
		resultNamesAndIds.append("[");
		String result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/community_centers/keywordSearch.ac?keyword="+keyword);
		JSONObject jsonObject =JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data").getJSONObject(0).getJSONArray("result");
		for (int i = 0; i < jsonArray.size(); i++) {
			String id =  jsonArray.getJSONObject(i).getString("id");
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
		String result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/community_centers/keywordSearch.ac?keyword="+keyword);
		JSONObject jsonObject =JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data").getJSONObject(0).getJSONArray("result");
		Integer[] idArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			int id =  jsonArray.getJSONObject(i).getInt("id");
			idArray[i]=id;
			logger.debug("user id ="+id);
		}
		
		try {
			 List<CommunityCenters> communityCenterList = securityService.findList(CommunityCenters.class, 0, 10,null,Factor.create("id", C.In, idArray));
			 for (CommunityCenters communityCenter : communityCenterList) {
				 //onClick="to_other_page('user/add_user.jhtml')"
				 //user.setName("<a href=\"#\" onclick=\"show_common_user_info(\""+user.getId()+"\");\">"+user.getName()+"</a>");
				 //user.setName("<a href=\"#\" onClick=\"to_other_page(\'user/check_user.jhtml?id="+user.getId()+"\')\">"+user.getName()+"</a>");
				 communityCenter.setName("<a href=\"#\" onClick=\"to_other_page(\'community_center/checkCommunityCenter.ac?id="+communityCenter.getId()+"\')\">"+communityCenter.getName()+"</a>");
			}
				JSONArray communityCenterListJsonArray = DateJsonValueProcessor.listToJSONArray(communityCenterList);
				
				logger.debug("communityCenterListJsonArray.toString()=="+communityCenterListJsonArray.toString());
			response.getWriter().write(communityCenterListJsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "getAllCommunityCenter", method = RequestMethod.GET)
	public void getAllCommunityCenter(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			 List<CommunityCenters> communityCenterList = securityService.findList(CommunityCenters.class, 0, 10, "updatedAt:desc");
			 for (CommunityCenters communityCenter : communityCenterList) {
				 //onClick="to_other_page('user/add_user.jhtml')"
				 //user.setName("<a href=\"#\" onclick=\"show_common_user_info(\""+user.getId()+"\");\">"+user.getName()+"</a>");
				 //user.setName("<a href=\"#\" onClick=\"to_other_page(\'user/check_user.jhtml?id="+user.getId()+"\')\">"+user.getName()+"</a>");
				 communityCenter.setName("<a href=\"#\" onClick=\"to_other_page(\'community_center/checkCommunityCenter.ac?id="+communityCenter.getId()+"\')\">"+communityCenter.getName()+"</a>");
			}
			 JSONArray communityCenterListJsonArray = DateJsonValueProcessor.listToJSONArray(communityCenterList);
				
				logger.debug("communityCenterListJsonArray.toString()=="+communityCenterListJsonArray.toString());
			response.getWriter().write(communityCenterListJsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "checkCommunityCenter", method = RequestMethod.GET)
	public ModelAndView checkCommunityCenter(HttpServletRequest request,HttpServletResponse response) {
		String communityCenterId = request.getParameter("id");
		CommunityCenters communityCenter=securityService.get(CommunityCenters.class, Integer.parseInt(communityCenterId));
		request.setAttribute("communityCenter", communityCenter);
		List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, Factor.create("type", C.Eq,7),Factor.create("objectId", C.Eq, Integer.parseInt(communityCenterId)));
		if(!ValidateUtil.isNullCollection(usersFavoritesList)&&usersFavoritesList.get(0)!=null){
			for (UsersFavorites usersFavorite : usersFavoritesList) {
				Intro intro = securityService.get(Intro.class, usersFavorite.getUserId());
				if(intro.getIntroType()==1){//居委会
					request.setAttribute("juweihui", intro.getTitle());
				}else if(intro.getIntroType()==2){//物业
					request.setAttribute("wuye", intro.getTitle());
				}
			}
		}
		return this.getModelAndView(SecurityUrls.COMMUNITY_CENTER_INFO);
	}
	
	@RequestMapping(value = "compile", method = RequestMethod.GET)
	public ModelAndView compile(HttpServletRequest request,HttpServletResponse response) {
		String communityCenterId = request.getParameter("id");
		CommunityCenters communityCenter=securityService.get(CommunityCenters.class, Integer.parseInt(communityCenterId));
		request.setAttribute("communityCenter", communityCenter);
		List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, Factor.create("type", C.Eq,7),Factor.create("objectId", C.Eq, Integer.parseInt(communityCenterId)));
		if(!ValidateUtil.isNullCollection(usersFavoritesList)&&usersFavoritesList.get(0)!=null){
			for (UsersFavorites usersFavorite : usersFavoritesList) {
				Intro intro = securityService.get(Intro.class, usersFavorite.getUserId());
				if(intro.getIntroType()==1){//居委会
					request.setAttribute("juweihui", intro.getTitle());
				}else if(intro.getIntroType()==2){//物业
					request.setAttribute("wuye", intro.getTitle());
				}
			}
		}
		return this.getModelAndView(SecurityUrls.COMMUNITY_CENTER_REDACT);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			securityService.remove(CommunityCenters.class, Integer.parseInt(id));
			List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, Factor.create("type", C.Eq,7),Factor.create("objectId", C.Eq, Integer.parseInt(id)));
			for (UsersFavorites usersFavorites : usersFavoritesList) {
					securityService.remove(usersFavorites);
			}
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"删除成功\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response) {
			try {
				String  name= request.getParameter("name");
				String  address= request.getParameter("address");
				String  latlng= request.getParameter("latlng");
				String  wuye= request.getParameter("wuye");
				String  juweihui= request.getParameter("juweihui");

				List<CommunityCenters> communityCenterList = securityService.findList(CommunityCenters.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, name));
				if(!ValidateUtil.isNullCollection(communityCenterList)){//如果不是空集合
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"小区名称已存在\"}");
					return;
				}
				
				List<Intro> wuyeList = null;
				List<Intro> juweihuiList = null;
				if(ValidateUtil.isNotNull(wuye)){
					wuyeList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, wuye), Factor.create("introType", C.Eq, 2));
					if(ValidateUtil.isNullCollection(wuyeList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"物业不存在\"}");
						return;
					}
				}
			
				if(ValidateUtil.isNotNull(juweihui)){
					juweihuiList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, juweihui), Factor.create("introType", C.Eq, 1));
					if(ValidateUtil.isNullCollection(juweihuiList)){//如果是空集合
						response.getWriter().write("{\"code\":\"1\",\"msg\":\"居委会不存在\"}");
						return;
					}
				}
				
				
				CommunityCenters communityCenter = new CommunityCenters();
				communityCenter.setAddress(address);
				communityCenter.setName(name);
				communityCenter.setLatitude(Double.parseDouble(latlng.split(",")[0]));
				communityCenter.setLongitude(Double.parseDouble(latlng.split(",")[1]));
				communityCenter.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				communityCenter.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				securityService.create(communityCenter);
				System.out.println("communityCenter.getId()==="+communityCenter.getId());
				
//				用户收藏的商户0/
//				用户收藏的小区1/
//				用户关注的用户2/
//				用户关注的活动3/
//				用户报名的活动4/
//				商户管理员管理的商户5/
//				居委会用户管理的居委会6/
//				物业用户管理的物业6/
//				居委会管理的小区7/
//				物业管理的小区7/
//				社区代表管理的小区8/
				
				//intro表 1 居委会;2 物业
				if(ValidateUtil.isNotNull(wuye)){
					UsersFavorites u = new UsersFavorites();
					u.setCreateTime(new Timestamp(System.currentTimeMillis()));
					//物业管理的小区7/
					u.setType(7);
					u.setUserId(wuyeList.get(0).getId());//物业ID
					u.setObjectId(communityCenter.getId());//小区ID
					securityService.create(u);
				}
				
				if(ValidateUtil.isNotNull(juweihui)){
					UsersFavorites u = new UsersFavorites();
					u.setCreateTime(new Timestamp(System.currentTimeMillis()));
					//居委会管理的小区7/
					u.setType(7);
					u.setUserId(juweihuiList.get(0).getId());//居委会ID
					u.setObjectId(communityCenter.getId());//小区ID
					securityService.create(u);
				}
				
				response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+communityCenter.getId()+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(HttpServletRequest request,HttpServletResponse response) {
		try {
			String  id= request.getParameter("id");
			String  name= request.getParameter("name");
			String  address= request.getParameter("address");
			String  latlng= request.getParameter("latlng");
			String  wuye= request.getParameter("wuye");
			String  juweihui= request.getParameter("juweihui");
			
			List<CommunityCenters> communityCenterList = securityService.findList(CommunityCenters.class, 0, Integer.MAX_VALUE, null, Factor.create("name", C.Eq, name), Factor.create("id", C.Ne, Integer.parseInt(id)));
			if(!ValidateUtil.isNullCollection(communityCenterList)){//如果不是空集合
				response.getWriter().write("{\"code\":\"1\",\"msg\":\"小区名称已存在\"}");
				return;
			}
			
			List<Intro> wuyeList = null;
			List<Intro> juweihuiList = null;
			if(ValidateUtil.isNotNull(wuye)){
				wuyeList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, wuye), Factor.create("introType", C.Eq, 2));
				if(ValidateUtil.isNullCollection(wuyeList)){//如果是空集合
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"物业不存在\"}");
					return;
				}
			}
		
			if(ValidateUtil.isNotNull(juweihui)){
				juweihuiList = securityService.findList(Intro.class, 0, Integer.MAX_VALUE, null, Factor.create("title", C.Eq, juweihui), Factor.create("introType", C.Eq, 1));
				if(ValidateUtil.isNullCollection(juweihuiList)){//如果是空集合
					response.getWriter().write("{\"code\":\"1\",\"msg\":\"居委会不存在\"}");
					return;
				}
			}
			
			CommunityCenters communityCenter= securityService.get(CommunityCenters.class, Integer.parseInt(id));
			communityCenter.setAddress(address);
			communityCenter.setName(name);
			communityCenter.setLatitude(Double.parseDouble(latlng.split(",")[0]));
			communityCenter.setLongitude(Double.parseDouble(latlng.split(",")[1]));
			communityCenter.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			communityCenter.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			securityService.update(communityCenter);
			System.out.println("communityCenter.getId()==="+communityCenter.getId());
			List<UsersFavorites> usersFavoritesList = securityService.findList(UsersFavorites.class, 0, Integer.MAX_VALUE, null, Factor.create("type", C.Eq,7),Factor.create("objectId", C.Eq, communityCenter.getId()));
			for (UsersFavorites usersFavorites : usersFavoritesList) {
				securityService.remove(usersFavorites);
			}
			
			if(ValidateUtil.isNotNull(wuye)){
				UsersFavorites u = new UsersFavorites();
				u.setCreateTime(new Timestamp(System.currentTimeMillis()));
				//物业管理的小区7/
				u.setType(7);
				u.setUserId(wuyeList.get(0).getId());//物业ID
				u.setObjectId(communityCenter.getId());//小区ID
				securityService.create(u);
			}
			
			if(ValidateUtil.isNotNull(juweihui)){
				UsersFavorites u = new UsersFavorites();
				u.setCreateTime(new Timestamp(System.currentTimeMillis()));
				//居委会管理的小区7/
				u.setType(7);
				u.setUserId(juweihuiList.get(0).getId());//居委会ID
				u.setObjectId(communityCenter.getId());//小区ID
				securityService.create(u);
			}
			response.getWriter().write("{\"code\":\"0\",\"msg\":\"保存成功\",\"id\":\""+communityCenter.getId()+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
