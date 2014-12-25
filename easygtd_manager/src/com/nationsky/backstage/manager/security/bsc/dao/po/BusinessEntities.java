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
 * @title : 商户实体类
 * @description : 对应数据表名为business_entities
 * @projectname : commcan_search
 * @classname : Business
 * @version 1.0
 * @company : nationsky
 * @email : liuchang@nationsky.com
 * @author : liuchang
 * @createtime : 2014年2月10日 下午1:19:41
 */
@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,dynamicUpdate=true)
@Table(name = "business_entities")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class BusinessEntities extends PO {

	// Fields

	private Integer id;
	private String name;
	private Double longitude;
	private Double latitude;
	private String address;
	private String phone;
	private String intro;
	private Integer businessCategoryId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer publiclyVisible;
	private String  source;
	private String  sourceId;
	private String  uuid;
	private Integer creatorId;
	private String tag;
	private String commend;
	private Integer managerId;
	private String imgUrl;
	private String imgMiniUrl;
	
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

	@Column(name = "name")
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

	@Column(name = "phone", length = 65535)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "business_category_id")
	public Integer getBusinessCategoryId() {
		return this.businessCategoryId;
	}

	public void setBusinessCategoryId(Integer businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	@Column(name = "created_at")
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at")
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

	@Column(name = "creator_id")
	public Integer getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "manager_id")
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Column(name = "commend")
	public String getCommend() {
		return commend;
	}
	public void setCommend(String commend) {
		this.commend = commend;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "source_id")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "imgUrl")
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Column(name = "imgMiniUrl")
	public String getImgMiniUrl() {
		return imgMiniUrl;
	}
	
	public void setImgMiniUrl(String imgMiniUrl) {
		this.imgMiniUrl = imgMiniUrl;
	}

	@Override
	public String toString() {
		return "BusinessEntities [id=" + id + ", name=" + name + ", longitude="
				+ longitude + ", latitude=" + latitude + ", address=" + address
				+ ", phone=" + phone + ", intro=" + intro
				+ ", businessCategoryId=" + businessCategoryId + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", publiclyVisible="
				+ publiclyVisible + ", source=" + source + ", sourceId="
				+ sourceId + ", uuid=" + uuid + ", creatorId=" + creatorId
				+ ", tag=" + tag + ", commend=" + commend + ", managerId="
				+ managerId + ", imgUrl=" + imgUrl + ", imgMiniUrl="
				+ imgMiniUrl + "]";
	}
	
	
}