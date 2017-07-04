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
 *    Create at:   2017年5月15日 下午3:43:59
 *
 *    Revision:
 *
 *    2017年5月15日 下午3:43:59
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 *<P>
 *  TODO：文件下载失败
 *<P>
 * @author Tjee
 * @Date 2017年5月15日 下午3:43:59
 */
public class FileDownloadFailException extends BaseException {

	private static final long serialVersionUID = -6038528861760342306L;

	public FileDownloadFailException() {
		super(FileMessageRepository.getInstance().getCode(FileMessageRepository.FILE_DOWNLOAD_FAIL),
				FileMessageRepository.getInstance().getMessage(FileMessageRepository.FILE_DOWNLOAD_FAIL));
	}

}
