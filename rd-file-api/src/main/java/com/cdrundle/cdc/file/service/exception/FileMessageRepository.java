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
 *    Create at:   2017年5月15日 下午2:18:03
 *
 *    Revision:
 *
 *    2017年5月15日 下午2:18:03
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.service.exception;

import com.cdrundle.common.exception.AbstractMessageRepository;

/**
 *<P>
 *  TODO：文件系统异常
 *<P>
 * @author Tjee
 * @Date 2017年5月15日 下午2:18:03
 */
public class FileMessageRepository extends AbstractMessageRepository {

	private FileMessageRepository() {
	}

	public static  FileMessageRepository getInstance(){
		return RepositoryHolder.REPOSITORY;
	}
	
	private static class RepositoryHolder{
		private static final FileMessageRepository REPOSITORY = new FileMessageRepository();
	}
	
	/**
	 * 文件上传失败
	 */
	public static final String FILE_UPLOAD_FAIL = "FILE_UPLOAD_FAIL";
	
	/**
	 * 文件下载失败
	 */
	public static final String FILE_DOWNLOAD_FAIL = "FILE_DOWNLOAD_FAIL";
	
	/**
	 * 拉取文件异常
	 */
	public static final String FILE_GET_FAIL = "FILE_GET_FAIL";
	
	/**
	 * 文件参数异常
	 */
	public static final String FILE_PARM_FAIL = "FILE_PARM_FAIL";
	
	
	/**
	 * 无文件
	 */
	public static final String NO_FILE = "NO_FILE";
	
	
	/**
	 * 无文件ID
	 */
	public static final String NO_FILE_ID = "NO_FILE_ID";
	
	/**
	 * 删除文件
	 */
	public static final String DELETE_FILE_FAIL="DELETE_FILE_FAIL";
	
	private String file =  "file_message.properties";
	/* (非 Javadoc)
	 * Description:
	 * @see com.cdrundle.common.exception.AbstractMessageRepository#getMessageFilePath()
	 */
	@Override
	protected String getMessageFilePath() {
		// TODO Auto-generated method stub
		return file;
	}

}
