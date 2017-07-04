/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileUtil.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月23日 上午9:01:54
 *
 *    Revision:
 *
 *    2017年5月23日 上午9:01:54
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdrundle.cdc.file.service.exception.FileParmFailException;
import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * TODO：文件工具类
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月23日 上午9:01:54
 */
public class FileUtil {
	/**
	 * 
	 * @Title: getFileExt
	 * @Description: TODO(获取后缀)
	 * @param @param
	 *            fileName
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月23日
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
			return null;
		} else {
			return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
		}
	}

	/**
	 * 
	 * @Title: getFileDisplaySize
	 * @Description: TODO(文件大小MB ,KB等)
	 * @param @param
	 *            file
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月23日
	 */
	public static String getFileDisplaySize(File file) {
		if (null == file) {
			return "";
		} else {
			return FileUtils.byteCountToDisplaySize(file.length());
		}
	}
	/**
	 * 
	 * @Title: getFileDisplaySize
	 * @Description: TODO(文件大小MB ,KB等)
	 * @param @param
	 *            file
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月23日
	 */
	public static String getFileDisplaySize(FileInputStream fis) {
		int len=0;
		if (null== fis) {
			return "";
		} else {
			try {
				len=fis.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return FileUtils.byteCountToDisplaySize(len);
		}
	}
}
