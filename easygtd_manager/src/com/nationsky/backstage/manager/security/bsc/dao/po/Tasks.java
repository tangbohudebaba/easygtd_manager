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
 * @title : 任务实体类
 * @description : 对应数据表名为tasks
 * @projectname : commcan_search
 * @classname : Business
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年2月24日 下午2:25:20
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "tasks")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Tasks extends PO {
	// Fields
	private Integer id;
	//任务主题
	private String subject;
	//任务指派ID
	private Integer assignerId;
	private Timestamp createTime;
	private Timestamp distributionTime;
	//任务是否完成
	private Integer status;
	//完成时间
	private Timestamp completedTime;
	private Timestamp closeTime;
	private Integer createUserId;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "subject", nullable = false)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "assigner_id", nullable = false)
	public Integer getAssignerId() {
		return assignerId;
	}
	public void setAssignerId(Integer assignerId) {
		this.assignerId = assignerId;
	}
	
	@Column(name = "createTime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "distributionTime")
	public Timestamp getDistributionTime() {
		return distributionTime;
	}
	
	public void setDistributionTime(Timestamp distributionTime) {
		this.distributionTime = distributionTime;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "completedTime")
	public Timestamp getCompletedTime() {
		return completedTime;
	}
	
	public void setCompletedTime(Timestamp completedTime) {
		this.completedTime = completedTime;
	}
	
	@Column(name = "closeTime")
	public Timestamp getCloseTime() {
		return closeTime;
	}
	
	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}
	
	@Column(name = "createUserId")
	public Integer getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
}