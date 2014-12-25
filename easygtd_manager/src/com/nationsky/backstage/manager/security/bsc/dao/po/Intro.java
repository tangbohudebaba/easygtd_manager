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
@Table(name = "intro")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Intro extends PO {

	// Fields 
	private Integer id;
	private String title;
	private String phoneNumber;
	private String content;
	private String address;
	private Timestamp createTime;
	private Integer introType;//1 居委会;2 物业
	private Double longitude;//经度
	private Double latitude;//纬度

	/** default constructor */
	public Intro() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIntroType() {
		return introType;
	}

	public void setIntroType(Integer introType) {
		this.introType = introType;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	
}