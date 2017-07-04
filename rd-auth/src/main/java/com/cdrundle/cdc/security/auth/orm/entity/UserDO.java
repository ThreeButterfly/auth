package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cdrundle.cdc.security.auth.common.orm.CodeNameEntity;




/**
 * 用户信息表
 * 
 * @author ccl
 * 
 */
@Entity
@Table(name = "t_cdc_mdm_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserDO extends CodeNameEntity {

	private static final long serialVersionUID = 1651158142679087910L;

	/**
	 * 登录用户名
	 */
	@Column(name="username",length=50, nullable = false,unique=true)
	private String userName;

	/**
	 * 密码
	 */
	@Column(length=50, nullable = false)
	private String password;
	
	/**
	 * 联系电话
	 */
	@Column(length=50,nullable=false)
	private String phone;

	/**
	 * 是否启用
	 */
	@Column(name="is_enable")
	private Boolean isEnable =false;
	
	/**
	 * 是否显示
	 */
	@Column(name="is_hidden")
	private Boolean isHidden = false;
	
	/**
	 * 备注
	 */
	@Column(length=500)
	private String remark;
	

	/**
	 * 出生时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	/**
	 * 用户所属科室
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="office_id")
	private OfficeDO office;
	
	/**
	 * 用户菜单配置 
	 */
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserLinkMenuDO> userLinkMenus;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_cdc_mdm_user_officewatch",
	joinColumns = { @JoinColumn(name = "user_id") },
	inverseJoinColumns = { @JoinColumn(name = "office_id") })
	private List<OfficeDO> officeWatch;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_cdc_mdm_user_organizationwatch",
	joinColumns = { @JoinColumn(name = "user_id",referencedColumnName="id") },
	inverseJoinColumns = { @JoinColumn(name = "organization_id",referencedColumnName="id") })
	private List<OrganizationDO> organizationWatch;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_cdc_mdm_user_role", 
	joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<RoleDO> roles;
	
	
	
	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserDO() {
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public List<UserLinkMenuDO> getUserLinkMenus() {
		return userLinkMenus;
	}

	public void setUserLinkMenus(List<UserLinkMenuDO> userLinkMenus) {
		this.userLinkMenus = userLinkMenus;
	}

	public OfficeDO getOffice() {
		return office;
	}

	public void setOffice(OfficeDO office) {
		this.office = office;
	}

	/**
	 * @return the roles
	 */
	public List<RoleDO> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleDO> roles) {
		this.roles = roles;
	}

	/**
	 * @return the officeWatch
	 */
	public List<OfficeDO> getOfficeWatch() {
		return officeWatch;
	}
	
	/**
	 * @param officeWatch the officeWatch to set
	 */
	public void setOfficeWatch(List<OfficeDO> officeWatch) {
		this.officeWatch = officeWatch;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<OrganizationDO> getOrganizationWatch() {
		return organizationWatch;
	}

	public void setOrganizationWatch(List<OrganizationDO> organizationWatch) {
		this.organizationWatch = organizationWatch;
	}
	
}
