/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * 不允许重复登陆
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月10日 下午2:17:11
 */
public class OnlyOneLoginException extends BaseException {

	private static final long serialVersionUID = 1807878977794950470L;

	/**
	 * 
	 */
	public OnlyOneLoginException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.ONLY_ONE_LOGIN),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.ONLY_ONE_LOGIN));
	}

}
