package com.nationsky.backstage.manager.security.bsc.dao.po;

import java.sql.Date;
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
@Table(name = "users")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@CRUD
public class Users extends PO {

	// Fields 
	private Integer id;
	private String name;
	private String nickname;
	private String gender;
	private Date birthdate;
	private String phoneNumber;//phone_number
	private String bio;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String email;
	private String passwordDigest;//password_digest
	private Integer homepageSwitch;//homepage_switch
	private Integer buzzSwitch;//buzz_switch
	private Integer messageSwitch;//message_switch
	private Integer userTpye;
	private String imgUrl;
	private String imgMiniUrl;
	private String tag;
	private String userTpyeName;//用户类型名称（页面展示字段，不序列化）
	private String communityCenters;
	private String businessEntities;
	/** default constructor */
	public Users() {
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

	@Column(name = "nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "birthdate")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "bio")
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password_digest")
	public String getPasswordDigest() {
		return passwordDigest;
	}

	public void setPasswordDigest(String passwordDigest) {
		this.passwordDigest = passwordDigest;
	}

	@Column(name = "homepage_switch")
	public Integer getHomepageSwitch() {
		return homepageSwitch;
	}

	public void setHomepageSwitch(Integer homepageSwitch) {
		this.homepageSwitch = homepageSwitch;
	}

	@Column(name = "buzz_switch")
	public Integer getBuzzSwitch() {
		return buzzSwitch;
	}

	public void setBuzzSwitch(Integer buzzSwitch) {
		this.buzzSwitch = buzzSwitch;
	}

	@Column(name = "message_switch")
	public Integer getMessageSwitch() {
		return messageSwitch;
	}

	public void setMessageSwitch(Integer messageSwitch) {
		this.messageSwitch = messageSwitch;
	}
	
	@Column(name = "user_type")
	public Integer getUserTpye() {
		return userTpye;
	}
	
	public void setUserTpye(Integer userTpye) {
		this.userTpye = userTpye;
	}
	
	@Column(name = "imgUrl")
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Column(name = "imgMiniUrl")
	public String getImgMiniUrl() {
		return imgMiniUrl;
	}
	
	public void setImgMiniUrl(String imgMiniUrl) {
		this.imgMiniUrl = imgMiniUrl;
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

	@Transient
	public String getCommunityCenters() {
		return communityCenters;
	}

	public void setCommunityCenters(String communityCenters) {
		this.communityCenters = communityCenters;
	}

	@Transient
	public String getBusinessEntities() {
		return businessEntities;
	}

	public void setBusinessEntities(String businessEntities) {
		this.businessEntities = businessEntities;
	}
	
	
}