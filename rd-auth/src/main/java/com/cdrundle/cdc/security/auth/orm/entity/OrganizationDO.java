package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
 * 组织机构表
 * 
 * @author ccl
 *
 */
@Entity
@Table(name = "t_cdc_mdm_organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganizationDO extends CodeNameEntity  {
	
	private static final long serialVersionUID = -3159886327856157989L;

	/**
	 * 全称
	 */
	@Column(name="full_name",length=100)
	private String fullName;

	/**
	 * 联系人
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contacts_id")
	private UserDO contacts;

	/**
	 * 联系电话
	 */
	@Column(length=20)
	private String phone;

	/**
	 * 地址
	 */
	@Column(length=100)
	private String address;

	/**
	 * 是否启用
	 */
	@Column(name = "is_used")
	private Boolean isUsed=true;

	@Column(name = "is_leaf")
	private Boolean leaf=false;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	private List<OrganizationDO> children;

	/**
	 * 父机构
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private OrganizationDO parent;

	/**
	 * 性质
	 */
	@Column(length=50)
	private String nature;

	/**
	 * 显示顺序
	 */
	@Column(name = "display_order")
	private Integer displayOrder;

	/**
	 * 备注
	 */
	@Column(length=500)
	private String remark;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private List<OfficeDO> offices;
	
	@ManyToMany(mappedBy="organizationWatch")
	private List<UserDO> users;

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserDO getContacts() {
		return contacts;
	}

	public void setContacts(UserDO contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public List<OrganizationDO> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationDO> children) {
		this.children = children;
	}

	public OrganizationDO getParent() {
		return parent;
	}

	public void setParent(OrganizationDO parent) {
		this.parent = parent;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OfficeDO> getOffices() {
		return offices;
	}

	public void setOffices(List<OfficeDO> offices) {
		this.offices = offices;
	}

	public List<UserDO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDO> users) {
		this.users = users;
	}
	

}
