/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.BaseException;

/**
 *<P>
 * 用户没有权限访问相关资源(服务、报表、图片等)
 *<P>
 * @author limaojun
 * @CreateDate 2017年2月28日 下午2:11:31
 */
public class NoPrivilegeException extends BaseException {
	
	private static final long serialVersionUID = -1952722422951870327L;

	public NoPrivilegeException() {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.NO_PRIVILEGE),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.NO_PRIVILEGE));
	}
	public NoPrivilegeException(String message) {
		super(AuthMessageRepository.getInstance().getCode(AuthMessageRepository.NO_PRIVILEGE),
				AuthMessageRepository.getInstance().getMessage(AuthMessageRepository.NO_PRIVILEGE)+message);
	}
}
