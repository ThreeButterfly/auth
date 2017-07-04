/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * 缺少token异常
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月10日 上午10:41:16
 */
public class NoTokenException extends BaseException {

	private static final long serialVersionUID = 3370018717086212369L;

	public NoTokenException() {
		super(ServicePlatformMessageRepository.getInstance().getCode(ServicePlatformMessageRepository.NO_TOKEN),
				ServicePlatformMessageRepository.getInstance().getMessage(ServicePlatformMessageRepository.NO_TOKEN));
	}

}
