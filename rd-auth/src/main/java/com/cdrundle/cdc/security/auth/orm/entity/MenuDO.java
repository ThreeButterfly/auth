/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
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

import com.cdrundle.cdc.security.auth.common.orm.CodeNameEntity;

/**
 *<P>
 *  菜单表
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午3:21:13
 */
@Entity
@Table(name="t_cdc_mdm_menu")
public class MenuDO extends CodeNameEntity {

	private static final long serialVersionUID = 7181286422627405631L;
	/**
	 * 所属应用系统
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="app_id")
	private AppDO app;
	/**
	 * 父级菜单
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private MenuDO parent;
	/**
	 * 样式
	 */
	@Column(name="icon_cls")
	private String iconCls;
	/**
	 * 是否叶子节点
	 */
	@Column(name="is_leaf",nullable=false)
	private Boolean isLeaf;
//	/**
//	 * 是否显示
//	 */
//	@Column(name="is_display",nullable=false)
//	private Boolean isDisplay;
	/**
	 * 是否启用
	 */
	@Column(name="is_enable")
	private Boolean isEnable;
	/**
	 * 是否是按钮
	 */
	@Column(name="is_button",nullable=false)
	private Boolean isButton;
	
//	/**
//	 * 是否是临时菜单
//	 */
//	@Column(name="is_temporary",nullable=false)
//	private Boolean isTemporary;
	/**
	 * 页面地址/Ext类名
	 */
	@Column(name="page_url")
	private String pageUrl;
	/**
	 * 显示顺序
	 */
	@Column(name="display_order")
	private Integer displayOrder;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	/**
	 * 失效时间
	 */
	@Column(name="expiry_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryTime;

	/**
	 * 关联的用户
	 */
	@OneToMany(mappedBy="menu",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserLinkMenuDO> userLinkMenus;
	
	@ManyToMany(mappedBy="menus")
	private List<RoleDO> roles;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="t_cdc_mdm_menu_service",
	joinColumns={@JoinColumn(name="menu_id",referencedColumnName="id") },
	inverseJoinColumns={@JoinColumn(name="service_id",referencedColumnName="id")}	)
	private List<ServiceDO> services;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="t_cdc_mdm_menu_report",
	joinColumns={@JoinColumn(name="menu_id")},
	inverseJoinColumns={@JoinColumn(name="report_id")}	)
	private List<ReportDO> reports;
	
	/**
	 * @return the app
	 */
	public AppDO getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(AppDO app) {
		this.app = app;
	}

	/**
	 * @return the parent
	 */
	public MenuDO getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(MenuDO parent) {
		this.parent = parent;
	}


	/**
	 * @return the isLeaf
	 */
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}


	/**
	 * @return the isButton
	 */
	public Boolean getIsButton() {
		return isButton;
	}

	/**
	 * @param isButton the isButton to set
	 */
	public void setIsButton(Boolean isButton) {
		this.isButton = isButton;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the expiryTime
	 */
	public Date getExpiryTime() {
		return expiryTime;
	}

	/**
	 * @param expiryTime the expiryTime to set
	 */
	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	/**
	 * @return the userLinkMenus
	 */
	public List<UserLinkMenuDO> getUserLinkMenus() {
		return userLinkMenus;
	}

	/**
	 * @param userLinkMenus the userLinkMenus to set
	 */
	public void setUserLinkMenus(List<UserLinkMenuDO> userLinkMenus) {
		this.userLinkMenus = userLinkMenus;
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
	 * @return the services
	 */
	public List<ServiceDO> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<ServiceDO> services) {
		this.services = services;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<ReportDO> getReports() {
		return reports;
	}

	public void setReports(List<ReportDO> reports) {
		this.reports = reports;
	}
	
}
