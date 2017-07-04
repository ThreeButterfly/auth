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
 *    Create at:   2017年5月15日 下午3:00:13
 *
 *    Revision:
 *
 *    2017年5月15日 下午3:00:13
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @Date 2017年5月15日 下午3:00:13
 */
public class NoFileException extends BaseException {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = -2444815990366063918L;

	public NoFileException() {
		super(FileMessageRepository.getInstance().getCode(FileMessageRepository.NO_FILE),
				FileMessageRepository.getInstance().getMessage(FileMessageRepository.NO_FILE));
	}

}
