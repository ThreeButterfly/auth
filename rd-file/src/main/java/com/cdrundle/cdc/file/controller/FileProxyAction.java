/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileProxyAction.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月24日 下午12:30:36
 *
 *    Revision:
 *
 *    2017年5月24日 下午12:30:36
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdrundle.cdc.file.common.util.FastDFSClient;
import com.cdrundle.cdc.file.common.util.FileValidateCode;
import com.cdrundle.cdc.file.service.IFileApi;
import com.cdrundle.cdc.file.service.bo.FileInfoBO;
import com.cdrundle.cdc.file.web.AuthUser;
import com.cdrundle.cdc.file.web.FileValidate;
import com.cdrundle.cdc.security.auth.service.bo.AuthUserInfoBO;
import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * TODO：文件中转器
 * <P>
 * /usr/local/nginx/sbin/nginx -s reload
 * 
 * @author Tjee
 * @CreateDate 2017年5月24日 下午12:30:36
 */

@Controller
@RequestMapping("/fileProxy")
@Scope("prototype")
public class FileProxyAction {
	@Autowired
	IFileApi fileApi;

	@RequestMapping("/url")
	public String uploadFile(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		FileInfoBO fileInfoBO = fileApi.queryFileById(fileInfo.getFileId(), user.getId(), user.getUserName(),
				fileInfo.getAppKey());
		String fileUrl = fileInfoBO.getFileUrl();
		return "redirect:" + fileUrl;
	}

	// 测试演示用
	@RequestMapping("/stream")
	public String stream() throws Exception {
		String filePath = "G:\\1.jpg";
		File file = new File(filePath);
		String s = "";//fileApi.uploadFileByStream("", "333", filePath, new FileInputStream(file));

		return s;
	}
}
