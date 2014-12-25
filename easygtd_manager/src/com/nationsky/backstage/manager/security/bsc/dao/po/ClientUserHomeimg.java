/**
 * 
 */
package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.nationsky.backstage.core.PO;
import com.nationsky.backstage.core.bsc.CRUD;

/**
 * 功能：
 * @author yubaojian0616@163.com
 *
 * mobile enterprise application platform
 * Version 0.1
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name="client_user_homeimg")
@CRUD
public class ClientUserHomeimg extends PO {

	@Id
	@GeneratedValue(generator="generator")
	@GenericGenerator(name="generator",strategy="identity")//不用setID值，Hibernate会自动生成32位uuid
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;//唯一主键
	private String imgName;//图片名称
	private Timestamp createdAt;//生成时间
	private Timestamp updatedAt;//更新时间
	private String content;//点击图片进入详情页的内容
	private String type;//图片动作类型：detail跳转到详情页面   wuye跳转到物业  juweihui跳转到居委会  url跳转到第三方页面
	private String title;//详情页文字标题
	private String theme;//详情页眉主题文字
	private String urlStr;//需要跳转到第三方页面的url地址
	private Integer sort;//图片排序序号(数值越小越排前面)
	private String imgUuid;//图片32位uuid的图片名
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getImgName() {
		return imgName;
	}
	
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getImgUuid() {
		return imgUuid;
	}
	public void setImgUuid(String imgUuid) {
		this.imgUuid = imgUuid;
	}
	
	
}
