/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.common.orm;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *<P>
 * 需要有自增id、code、name、version的实体可继承此类<br>
 * code不允许重复、name不允许重复
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午3:55:51
 */
@MappedSuperclass
public class CodeNameEntity extends CodeEntity {

	private static final long serialVersionUID = 2651416752126677261L;
	
	@Column(length=200,nullable=false,unique=true)
	private String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
