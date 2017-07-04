/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.bo;

import java.io.Serializable;
import java.util.List;

import com.cdrundle.common.bean.IdCodeName;

/**
 * <P>
 * 用户信息
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月3日 下午4:52:34
 */
public class AuthUserInfoBO implements Serializable {

	private static final long serialVersionUID = -1478397468088959454L;

	private Long id;

	private String name;

	private String userName;

	private IdCodeName office;

	private IdCodeName area;

	private IdCodeName org;

	private List<IdCodeName> watchOffices;

	private List<IdCodeName> watchUsers;

	private List<IdCodeName> watchOrgs;

	private List<Long> roles;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the roles
	 */
	public List<Long> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}

	/**
	 * @return the office
	 */
	public IdCodeName getOffice() {
		return office;
	}

	/**
	 * @param office
	 *            the office to set
	 */
	public void setOffice(IdCodeName office) {
		this.office = office;
	}

	/**
	 * @return the area
	 */
	public IdCodeName getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(IdCodeName area) {
		this.area = area;
	}

	/**
	 * @return the org
	 */
	public IdCodeName getOrg() {
		return org;
	}

	/**
	 * @param org
	 *            the org to set
	 */
	public void setOrg(IdCodeName org) {
		this.org = org;
	}

	/**
	 * @return the watchOffices
	 */
	public List<IdCodeName> getWatchOffices() {
		return watchOffices;
	}

	/**
	 * @param watchOffices
	 *            the watchOffices to set
	 */
	public void setWatchOffices(List<IdCodeName> watchOffices) {
		this.watchOffices = watchOffices;
	}

	/**
	 * @return the watchUsers
	 */
	public List<IdCodeName> getWatchUsers() {
		return watchUsers;
	}

	/**
	 * @param watchUsers
	 *            the watchUsers to set
	 */
	public void setWatchUsers(List<IdCodeName> watchUsers) {
		this.watchUsers = watchUsers;
	}

	/**
	 * @return the watchOrgs
	 */
	public List<IdCodeName> getWatchOrgs() {
		return watchOrgs;
	}

	/**
	 * @param watchOrgs
	 *            the watchOrgs to set
	 */
	public void setWatchOrgs(List<IdCodeName> watchOrgs) {
		this.watchOrgs = watchOrgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthUserInfoBO [id=").append(id).append(", name=").append(name).append(", userName=")
				.append(userName).append(", office=").append(office.toString())
				.append(", area=").append(area).append(", org=").append(org).append(", watchOffices=")
				.append(watchOffices.toString()).append(", watchcUsers=").append(watchUsers.toString()).append(", watchOrgs=")
				.append(watchOrgs.toString()).append(", roles=").append(roles).append("]");
		return builder.toString();
	}

}
