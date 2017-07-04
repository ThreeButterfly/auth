/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.cdrundle.cdc.security.auth.common.orm.CodeNameEntity;

/**
 *<P>
 *  服务实体
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 上午11:26:27
 */
@Entity
@Table(name="t_cdc_mdm_service")
public class ServiceDO extends CodeNameEntity{
	
	private static final long serialVersionUID = -5407446576880194247L;
	
	/**
	 * 服务调用url
	 */
	@Column(name="url",nullable= false,unique=true)
	private String url;
	/**
	 * 服务描述
	 */
	@Column
	private String description;
	/**
	 * 是否启用
	 */
	@Column(name="is_enable",nullable = false)
	private Boolean isEnable;
	/**
	 * 是否是公用服务(对外开放)
	 */
	@Column(name="is_public",nullable = false)
	private Boolean isPublic;
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable =false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	/**
	 * 失效时间
	 */
	@Column(name="expiry_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryTime;
	
	/**
	 * 关联的菜单
	 */
	@ManyToMany(mappedBy="services")
	private List<MenuDO> menus;
	

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		if (url != null) {
			url = StringUtils.trim(url);
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the isEnable
	 */
	public Boolean getIsEnable() {
		return isEnable;
	}
	/**
	 * @param isEnable the isEnable to set
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * @return the isPublic
	 */
	public Boolean getIsPublic() {
		return isPublic;
	}
	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
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
	 * @return the menus
	 */
	public List<MenuDO> getMenus() {
		return menus;
	}
	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<MenuDO> menus) {
		this.menus = menus;
	}
	
}
