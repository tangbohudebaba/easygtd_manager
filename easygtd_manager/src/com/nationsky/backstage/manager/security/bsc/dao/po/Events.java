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
 * @title : 活动实体类
 * @description : 对应数据表名为events
 * @projectname : commcan_search
 * @classname : Events
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年2月24日 下午2:25:20
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "events")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Events extends PO {
	private Integer  id;
	private String  title;//标题
	private Timestamp  startTime;//活动开始时间
	private Timestamp  endTime;//活动结束时间
	private String content;//活动内容
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String intro;
	private Integer status;
	private String address;
	private Integer seat;
	private Integer style;
	private String banner;
	private Integer creatorId;
	private Integer events_type;
	private Integer community_id;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "title", nullable = true)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "start_time", nullable = true)
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "end_time", nullable = true)
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "content", nullable = true)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	@Column(name = "intro", nullable = true)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Column(name = "status", nullable = true)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "address", nullable = true)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "seat", nullable = true)
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	
	@Column(name = "style", nullable = true)
	public Integer getStyle() {
		return style;
	}
	public void setStyle(Integer style) {
		this.style = style;
	}
	
	@Column(name = "banner", nullable = true)
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	@Column(name = "creator_id", nullable = true)
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	
	public Integer getCommunity_id() {
		return community_id;
	}
	
	public void setCommunity_id(Integer community_id) {
		this.community_id = community_id;
	}
	
	public Integer getEvents_type() {
		return events_type;
	}
	
	public void setEvents_type(Integer events_type) {
		this.events_type = events_type;
	}
}