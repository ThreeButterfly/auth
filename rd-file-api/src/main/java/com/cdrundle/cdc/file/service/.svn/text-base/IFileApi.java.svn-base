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

import com.cdrundle.cdc.file.service.bo.FileInfoBO;
import com.cdrundle.common.exception.BaseException;

/**
 * 
 * <P>
 * TODO：说明与描述
 * <P>
 * a
 * 
 * @author Tjee
 * @Date 2017年5月15日 下午1:32:20
 */
public interface IFileApi {
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
	public FileInfoBO uploadFile(FileInfoBO fileInfoBO) throws BaseException;

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
	public FileInfoBO uploadFileByStream(FileInfoBO fileInfoBO)
			throws BaseException;

	/**
	 * 
	 * @Title: uploadConfirm
	 * @Description: TODO(文件确认)
	 * @param @param
	 *            tokenId cdc系统令牌编号
	 * @param @param
	 *            fileId 文件唯一标识
	 * @param @return
	 * @param @throws
	 *            FileUploadFailException 参数
	 * @return FileInfoBO 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月16日
	 */
	public FileInfoBO uploadConfirm(FileInfoBO fileInfoBO) throws BaseException;

	/**
	 * 
	 * @Title: deleteFile
	 * @Description: TODO(文件删除)
	 * @param @param
	 *            tokenId cdc系统令牌编号
	 * @param @param
	 *            fileId 文件唯一标识
	 * @param @return
	 * @param @throws
	 *            FileUploadFailException 参数
	 * @return int 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月16日
	 */
	public int deleteFile(FileInfoBO fileInfoBO) throws BaseException;

	/**
	 * @Title: downloadURL
	 * @Description: TODO(文件下载)
	 * @param @param
	 *            tokenId cdc系统令牌编号
	 * @param @param
	 *            fileId 文件唯一标识
	 * @param @return
	 * @param @throws
	 *            FileDownloadFailException 参数
	 * @return FileInfoBO 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月16日
	 */
	public FileInfoBO downloadURL(FileInfoBO fileInfoBO) throws BaseException;

	/**
	 * 
	 * @Title: downloadStream
	 * @Description: TODO(根据id下载文件返回流)
	 * @param @param
	 *            tokenId
	 * @param @param
	 *            fileId
	 * @param @return
	 * @param @throws
	 *            FileDownloadFailException 参数
	 * @return InputStream 返回流
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月16日
	 */
	public byte[] downloadStream(FileInfoBO fileInfoBO) throws BaseException;

	/**
	 * 
	 * @Title: queryFileById
	 * @Description: TODO(根据ID查询文件)
	 * @param @param
	 *            fileInfoBO
	 * @param @return
	 * @param @throws
	 *            FileUploadFailException 参数
	 * @return FileInfoBO 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月15日
	 */
	public FileInfoBO queryFileById(String fileId,Long userId,String userName,String appkey) throws BaseException;
}
