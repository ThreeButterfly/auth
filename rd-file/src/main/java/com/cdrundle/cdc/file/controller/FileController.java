package com.cdrundle.cdc.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.dubbo.common.json.JSON;
import com.cdrundle.cdc.file.service.IFileApi;
import com.cdrundle.cdc.file.service.bo.FileInfoBO;
import com.cdrundle.cdc.file.service.exception.NoFileException;
import com.cdrundle.cdc.file.web.AuthUser;
import com.cdrundle.cdc.security.auth.service.bo.AuthUserInfoBO;
import com.cdrundle.common.bean.Result;
import com.cdrundle.common.exception.BaseException;

@RestController
@RequestMapping("/file")
@Scope("prototype")
public class FileController {

	@Autowired
	IFileApi fileApi;

	@RequestMapping("/uploadFile")
	@ResponseBody
	public Result uploadFile(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo, HttpServletRequest request)
			throws BaseException, IOException {
		fileInfo.setUserId(user.getId());
		fileInfo.setUserName(user.getName());
		getFile(fileInfo, request);
		FileInfoBO rtn = fileApi.uploadFile(fileInfo);
		return Result.success("成功", rtn);
	}

	@RequestMapping("/uploadConfirm")
	@ResponseBody
	public Result uploadConfirm(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		fileInfo.setUserId(user.getId());
		fileInfo.setUserName(user.getName());
		FileInfoBO fileInfoBO = fileApi.uploadConfirm(fileInfo);
		return Result.success("成功", fileInfoBO);
	}

	@RequestMapping("/deleteFile")
	@ResponseBody
	public Result deleteFile(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		fileInfo.setUserId(user.getId());
		fileInfo.setUserName(user.getName());
		int code = fileApi.deleteFile(fileInfo);
		return Result.success("成功", code);
	}

	@RequestMapping("/downloadURL")
	@ResponseBody
	public Result downloadURL(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		fileInfo.setUserId(user.getId());
		fileInfo.setUserName(user.getName());
		FileInfoBO fileInfoBO = fileApi.downloadURL(fileInfo);
		return Result.success("成功", fileInfoBO);
	}

	@RequestMapping("/downloadStream")
	@ResponseBody
	public byte[] downloadStream(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		fileInfo.setUserId(user.getId());
		fileInfo.setUserName(user.getName());
		byte[] bytes = fileApi.downloadStream(fileInfo);
		return bytes;
	}

	@RequestMapping("/downloadBase64")
	@ResponseBody
	public String downloadBase64(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		fileInfo.setUserId(user.getId());
		fileInfo.setUserName(user.getName());
		byte[] bytes = fileApi.downloadStream(fileInfo);
		String base64 = Base64.getEncoder().encodeToString(bytes);
		return base64;
	}

	@RequestMapping("/queryFileById")
	@ResponseBody
	public Result queryFileById(@AuthUser AuthUserInfoBO user, FileInfoBO fileInfo) throws BaseException {
		FileInfoBO fileInfoBO = fileApi.queryFileById(fileInfo.getFileId(), user.getId(), user.getUserName(),
				fileInfo.getAppKey());
		return Result.success("成功", fileInfoBO);
	}

	private FileInfoBO getFile(FileInfoBO fileInfoBO, HttpServletRequest request) throws BaseException {
		List<DiskFileItem> diskFileItemLi = getDiskFileItem(request);
		if (null != diskFileItemLi && diskFileItemLi.size() > 0) {
			// 默认只取第一个文件流
			DiskFileItem diskFileItem = diskFileItemLi.get(0);
			File file = diskFileItem.getStoreLocation();
			String fileName = diskFileItem != null ? diskFileItem.getName() : "";
			fileInfoBO.setFileName(fileName);
			fileInfoBO.setFile(file);
		} else {
			// exception
			throw new NoFileException();
		}
		return fileInfoBO;
	}

	// （支持多文件上传，为以后扩展）
	private List<DiskFileItem> getDiskFileItem(HttpServletRequest request) {
		List<DiskFileItem> diskFileItemLi = new ArrayList<DiskFileItem>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断request是否有文件上传
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				if (file != null && file.getSize() > 0) {
					CommonsMultipartFile cf = (CommonsMultipartFile) file;
					DiskFileItem diskFileItem = (DiskFileItem) cf.getFileItem();
					diskFileItemLi.add(diskFileItem);
				}
			}
		}
		return diskFileItemLi;
	}
}
