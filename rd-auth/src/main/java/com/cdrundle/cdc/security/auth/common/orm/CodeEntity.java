/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.common.orm;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *<P>
 * 需要有自增id、code、version的实体可继承此类<br>
 * code不允许重复
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午4:06:08
 */
@MappedSuperclass
public class CodeEntity extends LongIdEntity {
	
	private static final long serialVersionUID = 3777792215868076999L;

	
	@Column(length=200,nullable=false,unique=true)
	private String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
