package com.nationsky.backstage.test.common;

import com.nationsky.backstage.Configuration;
import com.nationsky.backstage.util.FileUtil;


public class CommonTest {
	//public static ISecurityService securityService=ServiceLocator.getService(ISecurityService.class);
	public static void main(String[] args) {
		boolean b = FileUtil.delete(Configuration.get("client.user.homeimg")+"fff22f3d12f7494198769ffa2c2eb649.jpg");
		System.out.println(b);
		
//		System.out.println(Configuration.get("client.user.homeimg"));
//		System.out.println("ROOT:"+Configuration.ROOT);
//		System.out.println("CONFIG_PATH:"+Configuration.CONFIG_PATH);
//		System.out.println("CURRENT_PATH:"+Configuration.CURRENT_PATH);
//		System.out.println("LINE_SEPARATOR"+Configuration.LINE_SEPARATOR);
//		System.out.println("LOG_PATH:"+Configuration.LOG_PATH);
//		System.out.println("PATH_SEPARATOR:"+Configuration.PATH_SEPARATOR);
//		System.out.println("TEMPLATE_PATH:"+Configuration.TEMPLATE_PATH);
//		System.out.println("TEMPLATE_URI:"+Configuration.TEMPLATE_URI);
//		System.out.println("TEMPLATE_WEB_PATH:"+Configuration.TEMPLATE_WEB_PATH);
//		System.out.println("TEMPORARY_PATH:"+Configuration.TEMPORARY_PATH);
//		System.out.println("getContentType():"+Configuration.getContentType());
//		System.out.println("getSecurityKey():"+Configuration.getSecurityKey());
//		String result = HttpUtil.getResult("http://218.247.15.110/commcan_search/v1/business/keywordSearch.ac?keyword="+"烤鸭店");
//		System.out.println(result);
//		String filePath="C:\\shedaiphone.xls";
//		boolean b = readExcel(filePath);
//		System.out.println(b);
//		String a = Configuration.get("ios.app_path");
//		System.out.println(a);
		//System.out.println(updateUsersFavorites());
//		String a=I18n.getMessage("system.startup.message",new Date(System.currentTimeMillis()));
//		System.out.println(a);
	}
	
//	public static boolean updateUsersFavorites(){
//		for (int j = 153; j <=2499; j++) {
//			UsersFavorites u = new UsersFavorites();
//			u.setCreateTime(new Timestamp(System.currentTimeMillis()));
//			u.setObjectId(182810);
//			u.setType(1);
//			u.setUserId(j);
//			securityService.create(u);
//		}
//		
//		return true;
//	}
//	
	/**
	 * 读取本地excel文件
	 * 
	 * @param args
	 */
//	public static boolean readExcel(String filePath){
//			String a="";
//		
//			File file = new File(filePath);
//			try {
//				Sheet sheet = Workbook.getWorkbook(file).getSheet(0);
//				for (int i = 1; i < sheet.getRows(); i++) {
//					Users user = new Users();
//					user.setUserTpye(6);
//					user.setPasswordDigest("e10adc3949ba59abbe56e057f20f883e");
//					user.setBirthdate(new Date(System.currentTimeMillis()));
//					user.setBuzzSwitch(1);
//					user.setHomepageSwitch(1);
//					user.setMessageSwitch(1);
//					user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//					user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//					for (int j = 0; j < 3; j++) {
//						String content = sheet.getCell(j, i).getContents().trim();
//						if(j==0){
//							user.setTag("社区代表 "+content);
//							//System.out.print("a:"+content);
//						}else if(j==1){
//							user.setName(content);
//							user.setNickname(content);
//							//System.out.print("b:"+content);
//						}else if(j==2){
//							user.setPhoneNumber(content);
//							//System.out.print("c:"+content);
//						}
//					}
//					
//					List<Users> usersList = securityService.findList(Users.class, 0, Integer.MAX_VALUE, null, Factor.create("phoneNumber", C.Eq, user.getPhoneNumber()));
//					if(usersList.size()>0){
//						System.out.println(user.getPhoneNumber());
//						continue;
//					}
//					securityService.create(user);
//					System.out.println("");
//				}
//			} catch (BiffException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			System.out.println(a);
//		return true;
//		
//	}
}
