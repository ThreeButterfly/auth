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
 * 报表实体
 *<P>
 * @author limaojun
 * @CreateDate 2017年2月25日 下午2:38:06
 */
@Entity
@Table(name="t_cdc_mdm_report")
public class ReportDO extends CodeNameEntity {

	private static final long serialVersionUID = 7009554265934846191L;
	/**
	 * 报表模板路径
	 */
	@Column(name="template_path",nullable=false,unique=true)
	private String templatePath;
	
	/**
	 * 是否启用
	 */
	@Column(name="is_enable")
	private Boolean isEnable;
	/**
	 * 是否是临时报表
	 */
	@Column(name="is_temporary")
	private Boolean isTemporary;
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
	 * 是否是自定义报表
	 */
	@Column(name="is_custom")
	private Boolean isCustom;
	@ManyToMany(mappedBy="reports")
	private List<MenuDO> menus;
	

	/**
	 * @return the templatePath
	 */
	public String getTemplatePath() {
		
		if (templatePath != null) {
			templatePath =  StringUtils.trim(templatePath);
		}
		return templatePath;
	}

	/**
	 * @param templatePath the templatePath to set
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
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
	 * @return the isTemporary
	 */
	public Boolean getIsTemporary() {
		return isTemporary;
	}

	/**
	 * @param isTemporary the isTemporary to set
	 */
	public void setIsTemporary(Boolean isTemporary) {
		this.isTemporary = isTemporary;
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
	 * @return the isCustom
	 */
	public Boolean getIsCustom() {
		return isCustom;
	}

	/**
	 * @param isCustom the isCustom to set
	 */
	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}

	public List<MenuDO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuDO> menus) {
		this.menus = menus;
	}

	
}
