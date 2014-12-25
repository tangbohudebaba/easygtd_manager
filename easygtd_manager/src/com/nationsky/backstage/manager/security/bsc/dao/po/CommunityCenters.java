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
 * @title : 社区实体类
 * @description : 对应数据表名为community_centers
 * @projectname : commcan_search
 * @classname : CommunityCenters
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年2月10日 下午1:35:17
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "community_centers")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class CommunityCenters extends PO {

	// Fields
	private Integer id;
	private String name;
	private Double longitude;
	private Double latitude;
	private String address;
	private String intro;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer publiclyVisible;

	/** default constructor */
	public CommunityCenters() {
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "longitude")
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "created_at", nullable = false, length = 19)
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", nullable = false, length = 19)
	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "publicly_visible")
	public Integer getPubliclyVisible() {
		return this.publiclyVisible;
	}

	public void setPubliclyVisible(Integer publiclyVisible) {
		this.publiclyVisible = publiclyVisible;
	}

	@Override
	public String toString() {
		return "CommunityCenters [id=" + id + ", name=" + name + ", longitude="
				+ longitude + ", latitude=" + latitude + ", address=" + address
				+ ", intro=" + intro + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", publiclyVisible="
				+ publiclyVisible + "]";
	}
	
	
}