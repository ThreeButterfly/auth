/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileStatus.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月18日 下午10:17:34
 *
 *    Revision:
 *
 *    2017年5月18日 下午10:17:34
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月18日 下午1:17:34
 */
public enum FileStatus {

	/**
	 * 创建
	 */
	CREATE("0", "未确认"),

	/**
	 * 确认
	 */
	CONFIRM("1", "已确认"),
	/**
	 * 未知状态
	 */
	ELSE("", "未知状态");
	public String value;
	public String type;

	FileStatus(String value, String type) {
		this.value = value;
		this.type = type;
	}

	public static FileStatus getEnum(String value) {
		if (value != null)
			for (FileStatus e : values()) {
				if (StringUtils.equalsIgnoreCase(e.value, value)) {
					return e;
				}
			}
		return ELSE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
