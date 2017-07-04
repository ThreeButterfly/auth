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
public class TokenTimeOutException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2014258208337353132L;

	public TokenTimeOutException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.TOKEN_TIMEOUT),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.TOKEN_TIMEOUT));
	}

}
