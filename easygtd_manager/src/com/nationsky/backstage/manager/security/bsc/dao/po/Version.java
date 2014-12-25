package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "version")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Version extends PO {

	// Fields 
	private Integer id;
	private String versionId;//版本号
	private String facilityType;//设备类型 android ios
	private String versionType;//软件类型(user/staff) 用户端/社代端
	private String content;//更新内容
	private String url;//文件地址
	private Timestamp createTime;//创建时间
	private Timestamp updateTime;//更新时间
	/** default constructor */
	public Version() {
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

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
}