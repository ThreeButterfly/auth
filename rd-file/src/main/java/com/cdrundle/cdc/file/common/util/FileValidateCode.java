/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileValidateCode.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月22日 下午1:04:22
 *
 *    Revision:
 *
 *    2017年5月22日 下午1:04:22
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.common.util;

/**
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月22日 下午1:04:22
 */
public enum FileValidateCode {
	DEFAULT("00000", "DEFAULT"),

	UPLOADFILE("00001", "upload"),

	UPLOADCONFIRM("00002", "uploadConfirm"),

	DELETEFILE("00003", "deleteFile"),

	QUERYFILE("00004", "queryFileById");

	/** 接口编码 */
	private String validateCode;

	/** 接口名称 */
	private String validateName;

	private FileValidateCode(String validateCode, String validateName) {
		this.validateCode = validateCode;
		this.validateName = validateName;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getValidateName() {
		return validateName;
	}

	public void setValidateName(String validateName) {
		this.validateName = validateName;
	}

}
