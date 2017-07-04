/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileApiImpl.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月17日 上午10:30:20
 *
 *    Revision:
 *
 *    2017年5月17日 上午10:30:20
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdrundle.cdc.file.common.util.FastDFSClient;
import com.cdrundle.cdc.file.common.util.FileStatus;
import com.cdrundle.cdc.file.common.util.FileUtil;
import com.cdrundle.cdc.file.common.util.SnowFlakeId;
import com.cdrundle.cdc.file.orm.FileRecordDao;
import com.cdrundle.cdc.file.orm.FileRecordTempDao;
import com.cdrundle.cdc.file.orm.entity.FileRecordDO;
import com.cdrundle.cdc.file.orm.entity.FileRecordTempDO;
import com.cdrundle.cdc.file.service.IFileApi;
import com.cdrundle.cdc.file.service.bo.FileInfoBO;
import com.cdrundle.cdc.file.service.exception.FileParmFailException;
import com.cdrundle.cdc.file.service.exception.NoFileException;
import com.cdrundle.cdc.file.service.exception.NoFileIDException;
import com.cdrundle.cdc.file.service.exception.NoTokenException;
import com.cdrundle.common.exception.BaseException;
import com.cdrundle.common.util.OrikaMapperFactory;

/**
 * <P>
 * TODO：文件系统实现类
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月17日 上午10:30:20
 */
@Service("fileApi")
@Transactional
public class FileApiImpl implements IFileApi {

	private OrikaMapperFactory<FileRecordTempDO, FileInfoBO> FileTempMapperFactory = OrikaMapperFactory
			.getMapper(FileRecordTempDO.class, FileInfoBO.class);
	private OrikaMapperFactory<FileRecordDO, FileInfoBO> FileMapperFactory = OrikaMapperFactory
			.getMapper(FileRecordDO.class, FileInfoBO.class);
	@Autowired
	FileRecordDao fileRecordDao;
	@Autowired
	FileRecordTempDao fileRecordTempDao;

	/*
	 * (非 Javadoc) Description:
	 * 
	 * @see com.cdrundle.cdc.file.service.IFileApi#uploadFile(java.lang.String,
	 * java.lang.String, java.io.File, java.lang.String)
	 */
	@Transactional
	@Override
	public FileInfoBO uploadFile(FileInfoBO fileInfo) throws BaseException {
		// 验证权限
		authAccess(fileInfo);
		// 验证文件信息
		uploadFileValidate(fileInfo);
		// 大小
		File file = fileInfo.getFile();
		String fileSize = FileUtil.getFileDisplaySize(file);
		fileInfo.setFileSize(fileSize);
		// 后缀
		String fileSuffix = FileUtil.getFileSuffix(fileInfo.getFileName());
		fileInfo.setFileSuffix(fileSuffix);

		// 上传到DFS
		String fileDfsId = FastDFSClient.uploadFile(file, fileSuffix);
		fileInfo.setFileUrl(fileDfsId);
		fileInfo.setFileDfsId(fileDfsId);
		FileInfoBO fileInfoBO = null;
		if (FileStatus.CONFIRM.value.equals(fileInfo.getStatus())) {
			FileRecordDO fileRecordDO = getFileDO(fileInfo);
			fileRecordDO = fileRecordDao.save(fileRecordDO);
			fileInfoBO = FileMapperFactory.map(fileRecordDO);
		} else {
			FileRecordTempDO fileRecordTempDO = getFileTempDO(fileInfo);
			fileRecordTempDO = fileRecordTempDao.save(fileRecordTempDO);
			fileInfoBO = FileTempMapperFactory.map(fileRecordTempDO);
		}
		fileInfoBO.setFileDfsId(null);
		// 返回客户端带IP地址
		String fileUrl = FastDFSClient.getUrl(fileDfsId);
		fileInfoBO.setFileUrl(fileUrl);
		return fileInfoBO;
	}

	@Transactional
	@Override
	public FileInfoBO uploadFileByStream(FileInfoBO fileInfo) throws BaseException {
		// 验证权限
		authAccess(fileInfo);
		// 验证文件流信息
		uploadStreamValidate(fileInfo);
		// 大小
		FileInputStream fis = fileInfo.getFis();
		String fileSize = FileUtil.getFileDisplaySize(fis);
		fileInfo.setFileSize(fileSize);
		// 后缀
		String fileSuffix = FileUtil.getFileSuffix(fileInfo.getFileName());
		fileInfo.setFileSuffix(fileSuffix);
		// 根据文件流上传
		String fileDfsId = FastDFSClient.uploadFileByStream(fis, fileSuffix);
		FileRecordDO fileRecordDO = getFileDO(fileInfo);
		fileRecordDO.setFileUrl(fileDfsId);
		fileRecordDO.setFileDfsId(fileDfsId);
		// 保存
		FileRecordDO fileRecord = fileRecordDao.save(fileRecordDO);
		FileInfoBO fileInfoBO = FileMapperFactory.map(fileRecord);
		fileInfoBO.setFileUrl(FastDFSClient.getUrl(fileDfsId));
		fileInfoBO.setFileDfsId(null);
		return fileInfoBO;
	}

	@Transactional
	@Override
	public FileInfoBO uploadConfirm(FileInfoBO fileInfoBO) throws BaseException {
		// 验证权限
		authAccess(fileInfoBO);
		// 验证
		checkFileId(fileInfoBO);
		// 查询文件临时表DO 抓换为正式表信息
		FileRecordTempDO fileRecordTempDO = fileRecordTempDao.findByFileId(fileInfoBO.getFileId());
		FileRecordDO fileRecordDO = new FileRecordDO();
		if(fileRecordTempDO==null){
			fileRecordDO=fileRecordDao.findByFileId(fileInfoBO.getFileId());
		}else{
			fileRecordDO = convertFile(fileRecordDO, fileRecordTempDO);
			// 转移正式表
			fileRecordDO = fileRecordDao.save(fileRecordDO);
			// 删除临时表记录
			fileRecordTempDao.deleteByFileId(fileInfoBO.getFileId());
		}
		// 转化
		FileInfoBO rtnInfoBo = FileMapperFactory.map(fileRecordDO);
		rtnInfoBo.setFileUrl(FastDFSClient.getUrl(fileRecordDO.getFileDfsId()));
		rtnInfoBo.setFileDfsId(null);
		return rtnInfoBo;
	}

	@Transactional
	@Override
	public int deleteFile(FileInfoBO fileInfoBO) throws BaseException {
		// 验证权限
		authAccess(fileInfoBO);
		// 验证
		checkFileId(fileInfoBO);
		// 查询文件fdsid用于文件系统删除文件
		FileRecordDO fileDO = fileRecordDao.findByFileId(fileInfoBO.getFileId());
		FileInfoBO fileInfoRtn = new FileInfoBO();
		fileInfoRtn.setFileDfsId(fileDO.getFileDfsId());
		fileRecordDao.deleteByFileId(fileInfoBO.getFileId());
		//0 代表成功  其他失败
		int rtn=FastDFSClient.deleteFile(fileInfoRtn.getFileDfsId());
		return rtn;
	}

	@Override
	public FileInfoBO downloadURL(FileInfoBO fileInfoBO) throws BaseException {
		// 验证权限
		authAccess(fileInfoBO);
		// 验证
		checkFileId(fileInfoBO);
		FileRecordDO fileRecordDO = fileRecordDao.findByFileId(fileInfoBO.getFileId());
		FileInfoBO fileInfo = FileMapperFactory.map(fileRecordDO);
		fileInfo.setFileUrl(FastDFSClient.getUrl(fileRecordDO.getFileDfsId()));
		fileInfo.setFileDfsId(null);
		return fileInfo;
	}

	@Override
	public byte[] downloadStream(FileInfoBO fileInfoBO) throws BaseException {
		// 验证权限
		authAccess(fileInfoBO);
		// 验证
		checkFileId(fileInfoBO);
		FileRecordDO fileRecordDO = fileRecordDao.findByFileId(fileInfoBO.getFileId());
		byte[] bytes = FastDFSClient.downloadBytes(fileRecordDO.getFileDfsId());
		return bytes;
	}

	@Override
	public FileInfoBO queryFileById(String fileId, Long userId, String userName, String appkey)
			throws BaseException {
		// 验证权限
		authAccess(userId, userName, appkey);
		// 验证
		checkFileId(fileId);
		FileRecordDO fileRecordDO = fileRecordDao.findByFileId(fileId);
		FileInfoBO fileInfoBO=null;
		if(fileRecordDO==null){
			FileRecordTempDO fileTmp=fileRecordTempDao.findByFileId(fileId);
			fileInfoBO=FileTempMapperFactory.map(fileTmp);
		}else{
			fileInfoBO =FileMapperFactory.map(fileRecordDO);
		}
		fileInfoBO.setFileUrl(FastDFSClient.getUrl(fileInfoBO.getFileDfsId()));
		fileInfoBO.setFileDfsId(null);
		return fileInfoBO;
	}

	/**
	 * @Title: checkUserInfo
	 * @Description: TODO(验证用户信息)
	 * @param @param
	 *            fileInfo 参数
	 * @return void 返回类型
	 * @throws NoTokenException
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月26日
	 */
	private void authAccess(FileInfoBO fileInfo) throws NoTokenException {
		if (StringUtils.isBlank(fileInfo.getAppKey()) || null == fileInfo.getUserId()
				|| StringUtils.isBlank(fileInfo.getUserName())) {
			throw new NoTokenException();
		}

	}

	/**
	 * @Title: checkUserInfo
	 * @Description: TODO(验证用户信息)
	 * @param @param
	 *            fileInfo 参数
	 * @return void 返回类型
	 * @throws NoTokenException
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月26日
	 */
	private void authAccess(Long userId, String userName, String appkey) throws NoTokenException {
		if (StringUtils.isBlank(appkey) || null == userId || StringUtils.isBlank(userName)) {
			throw new NoTokenException();
		}

	}

	/**
	 * @Title: fileValidate
	 * @Description: TODO(验证文件信息)
	 * @param @param
	 *            fileInfo 参数
	 * @return void 返回类型
	 * @throws BaseException
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月26日
	 */
	private void uploadFileValidate(FileInfoBO fileInfo) throws BaseException {
		checkFileName(fileInfo);
		checkFile(fileInfo);
	}

	private void uploadStreamValidate(FileInfoBO fileInfo) throws BaseException {
		checkFileName(fileInfo);
		checkStream(fileInfo);
	}

	// 检查上传是否有文件
	private void checkFile(FileInfoBO fileInfoBO) throws BaseException {
		if (null == fileInfoBO.getFile()) {
			throw new NoFileException();
		}
	}

	// 检查上传是否有文件流
	private void checkStream(FileInfoBO fileInfoBO) throws BaseException {
		if (null == fileInfoBO.getFis()) {
			throw new NoFileException();
		}
	}

	// 检查文件名
	private void checkFileName(FileInfoBO fileInfo) throws BaseException {
		String fileName = fileInfo.getFileName();
		if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
			throw new FileParmFailException();
		}
	}

	// 检查文件ID
	private void checkFileId(FileInfoBO fileInfo) throws BaseException {
		if (StringUtils.isBlank(fileInfo.getFileId())) {
			throw new NoFileIDException();
		}
	}

	// 检查文件ID
	private void checkFileId(String fileId) throws BaseException {
		if (StringUtils.isBlank(fileId)) {
			throw new NoFileIDException();
		}
	}

	/**
	 * @Title: getFileDO
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            fileInfo 参数
	 * @return void 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月19日
	 */
	private FileRecordDO getFileDO(FileInfoBO fileInfo) {
		FileRecordDO fileRecordDO = new FileRecordDO();
		Date date = new Date();
		fileRecordDO.setFileId(SnowFlakeId.getId());
		fileRecordDO.setFileName(fileInfo.getFileName());
		fileRecordDO.setFileSuffix(fileInfo.getFileSuffix());
		fileRecordDO.setFileSize(fileInfo.getFileSize());
		fileRecordDO.setFileUrl(fileInfo.getFileUrl());
		fileRecordDO.setAppKey(fileInfo.getAppKey());
		fileRecordDO.setStatus(fileInfo.getStatus());
		fileRecordDO.setUserId(fileInfo.getUserId());
		fileRecordDO.setUserName(fileInfo.getUserName());
		fileRecordDO.setFileDfsId(fileInfo.getFileDfsId());
		fileRecordDO.setStatus(FileStatus.CONFIRM.value);
		fileRecordDO.setCreateTime(date);
		fileRecordDO.setUpdateTime(date);
		return fileRecordDO;
	}

	/**
	 * @Title: setFileTempDO
	 * @Description: TODO(设置file临时表po)
	 * @param @param
	 *            fileInfo
	 * @param @return
	 *            参数
	 * @return FileRecordTempDO 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月18日
	 */
	private FileRecordTempDO getFileTempDO(FileInfoBO fileInfo) {
		FileRecordTempDO fileRecordTempDO = new FileRecordTempDO();
		Date date = new Date();
		fileRecordTempDO.setFileId(SnowFlakeId.getId());
		fileRecordTempDO.setFileName(fileInfo.getFileName());
		fileRecordTempDO.setFileSuffix(fileInfo.getFileSuffix());
		fileRecordTempDO.setFileSize(fileInfo.getFileSize());
		fileRecordTempDO.setFileUrl(fileInfo.getFileUrl());
		fileRecordTempDO.setAppKey(fileInfo.getAppKey());
		fileRecordTempDO.setStatus(fileInfo.getStatus());
		fileRecordTempDO.setUserId(fileInfo.getUserId());
		fileRecordTempDO.setUserName(fileInfo.getUserName());
		fileRecordTempDO.setFileDfsId(fileInfo.getFileDfsId());
		fileRecordTempDO.setStatus(FileStatus.CREATE.value);
		fileRecordTempDO.setCreateTime(date);
		fileRecordTempDO.setUpdateTime(date);
		return fileRecordTempDO;
	}

	/**
	 * @Title: convertFile
	 * @Description: TODO(临时表信息转换为历史表)
	 * @param @param
	 *            fileRecordTempDO
	 * @param @return
	 *            参数
	 * @return FileRecordDO 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月23日
	 */
	private FileRecordDO convertFile(FileRecordDO fileRecordDO, FileRecordTempDO fileRecordTempDO) {
		Date date = new Date();
		fileRecordDO.setFileId(fileRecordTempDO.getFileId());
		fileRecordDO.setFileName(fileRecordTempDO.getFileName());
		fileRecordDO.setFileSuffix(fileRecordTempDO.getFileSuffix());
		fileRecordDO.setFileSize(fileRecordTempDO.getFileSize());
		fileRecordDO.setFileUrl(fileRecordTempDO.getFileUrl());
		fileRecordDO.setAppKey(fileRecordTempDO.getAppKey());
		fileRecordDO.setStatus(FileStatus.CONFIRM.value);
		fileRecordDO.setFileDfsId(fileRecordTempDO.getFileDfsId());
		fileRecordDO.setUserId(fileRecordTempDO.getUserId());
		fileRecordDO.setUserName(fileRecordTempDO.getUserName());
		fileRecordDO.setUpdateTime(date);
		fileRecordDO.setCreateTime(fileRecordTempDO.getCreateTime());
		return fileRecordDO;
	}
}
