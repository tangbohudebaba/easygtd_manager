package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.nationsky.backstage.core.PO;
import com.nationsky.backstage.core.bsc.CRUD;

/** 
 * @title : 
 * @description : 
 * @projectname : commcan_search
 * @classname : Preferential
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年5月5日 下午3:56:17 
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "preferential")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Preferential extends PO{
	
	private Integer id;
	private String title;
	private String content;
	private String imgUrl;
	private String imgMiniUrl;
	private Integer createUserId;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer businessId;
	private Integer auditStatus;
	private Integer auditUserId;
	private Timestamp auditTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private Float longitude;
	private Float latitude;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgMiniUrl() {
		return imgMiniUrl;
	}
	public void setImgMiniUrl(String imgMiniUrl) {
		this.imgMiniUrl = imgMiniUrl;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}
	public Timestamp getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	@Transient
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	@Transient
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
}
