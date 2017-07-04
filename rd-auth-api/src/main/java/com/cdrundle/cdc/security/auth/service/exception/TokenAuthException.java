/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * token认证异常
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月5日 下午5:24:53
 */
public class TokenAuthException extends BaseException {

	private static final long serialVersionUID = 7531010223219623220L;

	public TokenAuthException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.TOKEN_CHECKED_EXCEPTION),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.TOKEN_CHECKED_EXCEPTION));
	}

	public TokenAuthException(Throwable t) {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.TOKEN_CHECKED_EXCEPTION),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.TOKEN_CHECKED_EXCEPTION), t);
	}

}
