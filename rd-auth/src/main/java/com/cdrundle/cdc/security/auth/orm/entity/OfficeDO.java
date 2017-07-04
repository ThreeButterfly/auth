package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cdrundle.cdc.security.auth.common.orm.CodeNameEntity;


/**
 * 科室
 * 
 * @author ccl
 *
 */
@Entity
@Table(name = "t_cdc_mdm_office")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OfficeDO extends CodeNameEntity {

	private static final long serialVersionUID = 7227880498676956305L;

	/**
	 * 全称
	 */
	@Column(name="full_name",length = 100)
	private String fullName;

	/**
	 * 联系电话
	 */
	@Column(length = 20)
	private String phone;

	/**
	 * 是否启用
	 */
	@Column(name = "is_used")
	private Boolean isUsed = false;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;

	@OneToMany(mappedBy = "office")
	private List<UserDO> users;
	
	@ManyToMany(mappedBy = "officeWatch")
	private List<UserDO> watchUsers;

	/**
	 * 负责人
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private UserDO manager;

	/**
	 * 分管领导
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "branch_leader_id")
	private UserDO branchLeader;

	/**
	 * 机构
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "organization_id",nullable=false)
	private OrganizationDO organization;
	
	/**
	 * ID对应md5code
	 */
	@Column(name="md5_code")
	private String md5Code;

	public List<UserDO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDO> users) {
		this.users = users;
	}

	public UserDO getBranchLeader() {
		return branchLeader;
	}

	public void setBranchLeader(UserDO branchLeader) {
		this.branchLeader = branchLeader;
	}

	public OrganizationDO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDO organization) {
		this.organization = organization;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserDO getManager() {
		return manager;
	}

	public void setManager(UserDO manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public List<UserDO> getWatchUsers() {
		return watchUsers;
	}

	public void setWatchUsers(List<UserDO> watchUsers) {
		this.watchUsers = watchUsers;
	}
	
}
