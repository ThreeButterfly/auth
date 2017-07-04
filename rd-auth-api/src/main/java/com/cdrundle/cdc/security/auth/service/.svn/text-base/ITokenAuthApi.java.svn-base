/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service;

import com.cdrundle.cdc.security.auth.service.bo.AuthInfoBO;
import com.cdrundle.cdc.security.auth.service.bo.LoginAuthBO;
import com.cdrundle.cdc.security.auth.service.bo.AuthUserInfoBO;
import com.cdrundle.cdc.security.auth.service.exception.GenerateTokenException;
import com.cdrundle.cdc.security.auth.service.exception.IpIllegalException;
import com.cdrundle.cdc.security.auth.service.exception.OnlyOneLoginException;
import com.cdrundle.cdc.security.auth.service.exception.RemoveTokenException;
import com.cdrundle.cdc.security.auth.service.exception.TokenAuthException;
import com.cdrundle.cdc.security.auth.service.exception.TokenExpiredException;
import com.cdrundle.cdc.security.auth.service.exception.TokenInvalidException;
import com.cdrundle.cdc.security.auth.service.exception.TokenNotExistException;
import com.cdrundle.cdc.security.auth.service.exception.UserOrPasswordWrongException;
import com.cdrundle.cdc.security.auth.service.exception.UserStatusException;
import com.cdrundle.cdc.security.auth.service.exception.WrongAppKeyException;
import com.cdrundle.common.exception.FieldCheckException;
import com.cdrundle.common.exception.ObjectIsNullException;

/**
 * <P>
 * token验证服务
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月3日 下午4:52:12
 */
public interface ITokenAuthApi {

	/**
	 * token验证
	 * 
	 * @param authInfoBO
	 * @return UserInfo
	 * @throws IpIllegalException
	 * @throws TokenNotExistException
	 * @throws TokenExpiredException
	 * @throws TokenAuthException
	 * @throws ObjectIsNullException
	 * @throws TokenInvalidException 
	 * @throws FieldCheckException 
	 */
	public AuthUserInfoBO tokenAuth(AuthInfoBO authInfoBO) throws IpIllegalException, TokenNotExistException, TokenExpiredException,
			TokenAuthException, ObjectIsNullException, TokenInvalidException, FieldCheckException;

	/**
	 * 登陆认证并获取token
	 * 
	 * @param auth
	 * @return String
	 * @throws UserOrPasswordWrongException
	 * @throws UserStatusException
	 * @throws GenerateTokenException
	 * @throws ObjectIsNullException
	 * @throws FieldCheckException 
	 * @throws OnlyOneLoginException 
	 * @throws WrongAppKeyException 
	 */
	public String generateToken(LoginAuthBO auth) throws UserOrPasswordWrongException, UserStatusException,
			GenerateTokenException, ObjectIsNullException, FieldCheckException, OnlyOneLoginException, WrongAppKeyException;

	/**
	 * 删除token
	 * 
	 * @param tokenCode
	 * @return
	 * @throws RemoveTokenException
	 */
	public void removeToken(String tokenCode);

	void checkTokenValid(String token) throws IpIllegalException, TokenNotExistException, TokenExpiredException,
			TokenAuthException, TokenInvalidException;

}
