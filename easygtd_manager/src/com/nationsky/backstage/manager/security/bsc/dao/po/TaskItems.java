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
 * @title : 商户实体类
 * @description : 对应数据表名为task_items
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
@Table(name = "task_items")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class TaskItems extends PO {
	// Fields
	private Integer id;
	//任务内容
	private String content;
	//对应tasks表中ID
	private Integer taskId;
	private Timestamp completedTime;
	private String title;
	private Integer status;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "task_id", nullable = false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	@Column(name = "completedTime")
	public Timestamp getCompletedTime() {
		return completedTime;
	}
	
	public void setCompletedTime(Timestamp completedTime) {
		this.completedTime = completedTime;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}