/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.bo;

import java.util.Date;
import java.util.List;

import com.cdrundle.common.bean.IdCodeName;

/**
 *<P>
 *  菜单表Bo
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午3:21:13
 */
public class MenuBo extends IdCodeName {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1904090162500492233L;
	/**
	 * 所属应用系统
	 */
//	private App app;
	/**
	 * 父级菜单
	 */
	private IdCodeName parent;
	/**
	 * 样式
	 */
	private String iconCls;

	/**
	 * 是否叶子节点
	 */
	private Boolean isLeaf;
//	/**
//	 * 是否显示
//	 */
//	private Boolean isDisplay;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 是否是按钮
	 */
	private Boolean isButton=false;
	
//	/**
//	 * 是否是临时菜单
//	 */
//	private Boolean isTemporary;
	/**
	 * 页面地址/Ext类名
	 */
	private String pageUrl;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
	
	/**
	 * 创建时间
	 */
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	/**
	 * 失效时间
	 */
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiryTime;

	/**
	 * 关联的用户
	 */
//	private List<UserLinkMenu> userLinkMenus;
//	
//	private List<Role> roles;
//	
//	private List<Service> services;
//	
//	private List<Report> reports;
	/**
	 * 显示树形用
	 */
	private List<MenuBo> childList;
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

	public IdCodeName getParent() {
		return parent;
	}

	public void setParent(IdCodeName parent) {
		this.parent = parent;
	}

	public List<MenuBo> getChildList() {
		return childList;
	}

	public void setChildList(List<MenuBo> childList) {
		this.childList = childList;
	}

	
	
}
