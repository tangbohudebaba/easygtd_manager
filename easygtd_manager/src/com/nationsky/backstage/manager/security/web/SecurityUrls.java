/**
 * 
 */
package com.nationsky.backstage.manager.security.web;

import com.nationsky.backstage.core.web.Urls;

/**
 * 功能：
 * @author yubaojian0616@163.com
 *
 * mobile enterprise application platform
 * Version 0.1
 */
public interface SecurityUrls extends Urls {
	
	String INDEX = "/manager/index";
	
	String SECURITY_LOGIN = "/manager/login";
	
	String USER_CHECK = "/manager/user/check_user";
	String USER_COMPILE = "/manager/user/compile_user";
	
	String COMMUNITY_CENTER_INFO = "/manager/community_center/community_center_info";
	String COMMUNITY_CENTER_REDACT = "/manager/community_center/community_center_redact";
	
	String BUSINESS_ENTITY_CHECK = "/manager/business_entity/business_entity_check";
	String BUSINESS_ENTITY_REDACT = "/manager/business_entity/business_entity_redact";
	
	String WUYE_CHECK = "/manager/wuye/check_wuye";
	String WUYE_COMPILE = "/manager/wuye/compile_wuye";
	
	String JUWEIHUI_CHECK = "/manager/juweihui/check_juweihui";
	String JUWEIHUI_COMPILE = "/manager/juweihui/compile_juweihui";
	
	String VERSION_CHECK = "/manager/version/check_version";
	String VERSION_COMPILE = "/manager/version/compile_version";
	
	String CLIENT_USER_HOMEIMG_CHECK = "/manager/client_user_homeimg/check_client_user_homeimg";
	String CLIENT_USER_HOMEIMG_COMPILE = "/manager/client_user_homeimg/compile_client_user_homeimg";
}
