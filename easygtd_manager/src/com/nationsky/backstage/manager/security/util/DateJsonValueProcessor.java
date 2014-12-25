/**
 * 
 */
package com.nationsky.backstage.manager.security.util;
/** 
 * @title : 
 * @description : 
 * @projectname : se_vertical_client
 * @classname : sfds
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年4月25日 下午5:48:20 
 */
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.nationsky.backstage.manager.security.bsc.dao.po.Users;
import com.nationsky.backstage.util.ValidateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/*** 将Bean中的Timestamp转换为json中的日期字符串*/
public class DateJsonValueProcessor implements JsonValueProcessor {
	public static final String Default_DATE_PATTERN ="yyyy-MM-dd HH:mm:ss";
	private DateFormat dateFormat ;
	public DateJsonValueProcessor(String datePattern){
		try{
			dateFormat  = new SimpleDateFormat(datePattern);}
		catch(Exception e ){
			dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
		}
	}
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}
	public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
		return process(value);
	}
	private Object process(Object value){
		try {
			return dateFormat.format((Date)value);
		} catch (Exception e) {
			return null;
		}
		 
	}
	
	public static JSONArray listToJSONArray(List userList){
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(DateJsonValueProcessor.Default_DATE_PATTERN));
		config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(DateJsonValueProcessor.Default_DATE_PATTERN));
	    return JSONArray.fromObject((List)userList,config);
	}
}