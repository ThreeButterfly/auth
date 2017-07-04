/******************************************************************
 * 
 *  Company:   成都市润东实业有限公司 软件开发部
 *
 * Copyright:   Copyright (c) 2001-2014
 *
 *
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月15日 下午1:29:27
 *
 *    Revision:
 *
 *    2017年5月15日 下午1:29:27
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.service;

import java.io.File;
import java.io.InputStream;

import com.cdrundle.cdc.file.service.bo.FileInfoBO;
import com.cdrundle.cdc.file.service.exception.FileDownloadFailException;
import com.cdrundle.cdc.file.service.exception.FileUploadFailException;

/**
 * 
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @Date 2017年5月15日 下午1:32:20
 */
public interface IFileApi2 {
	/**
	 * @Title: uploadFile
	 * @Description: TODO(文件系统上传接口)
	 * @param @param
	 *            tokenId cdc系统令牌编号
	 * @param @param
	 *            fileName 文件名字，包含后缀
	 * @param @param
	 *            file 文件对象
	 * @param @param
	 *            status 状态1：确认 ，为空表示上传没确认状态;
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月15日
	 */
	public FileInfoBO uploadFile(String tokenId,String fileName,File file,String status) throws FileUploadFailException;

	/**
	 * 
	 * @Title: uploadFileByStream
	 * @Description: TODO(流对象上传文件)
	 * @param @param
	 *            tokenId cdc系统令牌编号
	 * @param @param
	 *            fileName 文件名字，包含后缀
	 * @param @param
	 *            fileStream 文件流对象文件对象
	 * @param @return
	 * @param @throws
	 *            FileUploadFailException
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月15日
	 */
	public FileInfoBO uploadFileByStream(String tokenId,String fileName,InputStream is) throws FileUploadFailException;

	/**
	 * 
	* @Title: uploadConfirm
	* @Description: TODO(文件确认)
	* @param @param tokenId cdc系统令牌编号
	* @param @param fileId 文件唯一标识
	* @param @return
	* @param @throws FileUploadFailException    参数
	* @return FileInfoBO    返回类型
	* @throws
	* @author Tjee
	* @date 2017年5月16日
	 */
	public FileInfoBO uploadConfirm(String tokenId,String fileId) throws FileUploadFailException;
	/**
	 * 
	* @Title: deleteFile
	* @Description: TODO(文件删除)
	* @param @param tokenId cdc系统令牌编号
	* @param @param fileId 文件唯一标识
	* @param @return
	* @param @throws FileUploadFailException    参数
	* @return int    返回类型
	* @throws
	* @author Tjee
	* @date 2017年5月16日
	 */
	public int deleteFile(String tokenId,String fileId) throws FileUploadFailException;
	/**
	* @Title: downloadURL
	* @Description: TODO(文件下载)
	* @param @param tokenId cdc系统令牌编号
	* @param @param fileId 文件唯一标识
	* @param @return
	* @param @throws FileDownloadFailException    参数
	* @return FileInfoBO    返回类型
	* @throws
	* @author Tjee
	* @date 2017年5月16日
	 */
	public FileInfoBO downloadURL (String tokenId,String fileId) throws FileDownloadFailException;
	/**
	 * 
	* @Title: downloadStream
	* @Description: TODO(根据id下载文件返回流)
	* @param @param tokenId
	* @param @param fileId
	* @param @return
	* @param @throws FileDownloadFailException    参数
	* @return InputStream    返回流
	* @throws
	* @author Tjee
	* @date 2017年5月16日
	 */
	public InputStream downloadStream (String tokenId,String fileId) throws FileDownloadFailException;
	/**
	 * 
	* @Title: queryFileById
	* @Description: TODO(根据ID查询文件)
	* @param @param fileInfoBO
	* @param @return
	* @param @throws FileUploadFailException    参数
	* @return FileInfoBO    返回类型
	* @throws
	* @author Tjee
	* @date 2017年5月15日
	 */
	public FileInfoBO queryFileById(String fileId) throws FileDownloadFailException;
}
