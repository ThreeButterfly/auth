/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cdrundle.cdc.security.auth.common.orm.LongIdEntity;


/**
 *<P>
 *  用户关联菜单(特殊菜单配置)
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午5:23:59
 */
@Entity
@Table(name="t_cdc_mdm_user_menu")
public class UserLinkMenuDO extends LongIdEntity {

	private static final long serialVersionUID = 8621017477130750110L;
	/**
	 * 关联用户
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	 private UserDO user;
	 
	/**
	 * 关联菜单
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menu_id",nullable=false)
	 private MenuDO menu;
	 
	/**
	 * 是否可用
	 */
	@Column(name="is_usable",nullable=false)
	 private Boolean isUsable;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	 private Date createTime;
	
	/**
	 * 失效时间
	 */
	 @Column(name="expiry_time")
	 private Date expiryTime;


	
	/**
	 * @return the user
	 */
	public UserDO getUser() {
		return user;
	}

	/**
	 * @param userDO the user to set
	 */
	public void setUser(UserDO user) {
		this.user = user;
	}

	/**
	 * @return the menu
	 */
	public MenuDO getMenu() {
		return menu;
	}

	/**
	 * @param menuDO the menu to set
	 */
	public void setMenu(MenuDO menu) {
		this.menu = menu;
	}

	/**
	 * @return the isUsable
	 */
	public Boolean getIsUsable() {
		return isUsable;
	}

	/**
	 * @param isUsable the isUsable to set
	 */
	public void setIsUsable(Boolean isUsable) {
		this.isUsable = isUsable;
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
	 
}
