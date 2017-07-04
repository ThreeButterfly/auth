/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * 会话失效
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月10日 下午2:26:36
 */
public class TokenInvalidException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1208248402150066008L;

	public TokenInvalidException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.TOKEN_INVALID),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.TOKEN_INVALID));
	}
}
