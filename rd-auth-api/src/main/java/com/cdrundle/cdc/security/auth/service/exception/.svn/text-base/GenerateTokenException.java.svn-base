/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * 生成Token异常
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月4日 上午9:18:53
 */
public class GenerateTokenException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238881951924820765L;

	/**
	 * 
	 */
	public GenerateTokenException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.GENERATE_TOKEN_EXCEPTION),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.GENERATE_TOKEN_EXCEPTION));
	}

	public GenerateTokenException(Throwable t) {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.GENERATE_TOKEN_EXCEPTION),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.GENERATE_TOKEN_EXCEPTION), t);
	}

}
