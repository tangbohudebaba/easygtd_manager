/**
 * 
 */
package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Date;

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
@Table(name="actionaccount")
@CRUD
public class ActionAccount extends PO {

	@Id
	@GeneratedValue(generator="generator")
	@GenericGenerator(name="generator",strategy="identity")//不用setID值，Hibernate会自动生成32位uuid
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String facilityType;//设备类型 ios android
	private String clientType; //客户端类型 Commcan_for_user Commcan_for_staff
	private Integer count;//动作量 
	private String actionType; //动作类型：  下载量 download
	private Date createdAt;//生成时间
	private Date updatedAt;//更新时间
	
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
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
