package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.nationsky.backstage.core.PO;
import com.nationsky.backstage.core.bsc.CRUD;
/**
 * 
 * @title : 商户类型表
 * @description : 对应数据表名为business_categories
 * @projectname : commcan_search
 * @classname : BusinessCategories
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年2月10日 下午1:19:41
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "business_categories")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class BusinessCategories extends PO {

	private Integer id;
	private String name;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer countOfBusinessEntities;
	private Integer countOfPubliclyVisibleBusinessEntities;
	private Integer countOfPubliclyInvisibleBusinessEntities;
	private Integer creatorId;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "created_at", nullable = false)
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "updated_at", nullable = false)
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Column(name = "count_of_business_entities", nullable = true)
	public Integer getCountOfBusinessEntities() {
		return countOfBusinessEntities;
	}
	public void setCountOfBusinessEntities(Integer countOfBusinessEntities) {
		this.countOfBusinessEntities = countOfBusinessEntities;
	}
	
	@Column(name = "count_of_publicly_visible_business_entities", nullable = true)
	public Integer getCountOfPubliclyVisibleBusinessEntities() {
		return countOfPubliclyVisibleBusinessEntities;
	}
	public void setCountOfPubliclyVisibleBusinessEntities(
			Integer countOfPubliclyVisibleBusinessEntities) {
		this.countOfPubliclyVisibleBusinessEntities = countOfPubliclyVisibleBusinessEntities;
	}
	
	@Column(name = "count_of_publicly_invisible_business_entities", nullable = true)
	public Integer getCountOfPubliclyInvisibleBusinessEntities() {
		return countOfPubliclyInvisibleBusinessEntities;
	}
	public void setCountOfPubliclyInvisibleBusinessEntities(
			Integer countOfPubliclyInvisibleBusinessEntities) {
		this.countOfPubliclyInvisibleBusinessEntities = countOfPubliclyInvisibleBusinessEntities;
	}
	
	@Column(name = "creator_id", nullable = true)
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	
	
	
}