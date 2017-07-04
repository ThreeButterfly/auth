/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * TODO:类的描述
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月4日 上午9:22:11
 */
public class RemoveTokenException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 738184444424523380L;

	public RemoveTokenException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.REMOVE_TOKEN_EXCEPTION),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.REMOVE_TOKEN_EXCEPTION));
	}

	public RemoveTokenException(Throwable t) {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.REMOVE_TOKEN_EXCEPTION),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.REMOVE_TOKEN_EXCEPTION), t);
	}
}
