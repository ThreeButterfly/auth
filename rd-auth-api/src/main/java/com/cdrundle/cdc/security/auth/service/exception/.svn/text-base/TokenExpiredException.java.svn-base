/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * 会话过期
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月3日 下午5:20:25
 */
public class TokenExpiredException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6016881627638036248L;

	public TokenExpiredException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.TOKEN_EXPIRED),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.TOKEN_EXPIRED));
	}

}
