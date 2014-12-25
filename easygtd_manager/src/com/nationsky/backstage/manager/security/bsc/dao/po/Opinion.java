package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.nationsky.backstage.core.PO;
import com.nationsky.backstage.core.bsc.CRUD;
/**
 * 
 * @title : 用户实体类
 * @description : 对应数据表名为users
 * @projectname : commcan_search
 * @classname : Users
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年2月10日 下午2:03:09
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "suggest")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Opinion extends PO {

	// Fields 
	private Integer id;
	private String content;
	private Integer userId;
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());//任务创建时间
	private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());//任务更新时间
	
	private String userName;
	private String userTpyeName;//用户类型名称（页面展示字段，不序列化）
	/** default constructor */
	public Opinion() {
	}

	@GenericGenerator(name = "generator", strategy = "identity")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Transient
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the userTpyeName
	 */
	@Transient
	public String getUserTpyeName() {
		return userTpyeName;
	}
	
	/**
	 * @param userTpyeName the userTpyeName to set
	 */
	public void setUserTpyeName(String userTpyeName) {
		this.userTpyeName = userTpyeName;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}