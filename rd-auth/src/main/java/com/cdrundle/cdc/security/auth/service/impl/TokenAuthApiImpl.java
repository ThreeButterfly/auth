/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdrundle.cdc.security.auth.orm.IAppDao;
import com.cdrundle.cdc.security.auth.orm.ITokenDao;
import com.cdrundle.cdc.security.auth.orm.IUserDao;
import com.cdrundle.cdc.security.auth.orm.entity.AppDO;
import com.cdrundle.cdc.security.auth.orm.entity.OfficeDO;
import com.cdrundle.cdc.security.auth.orm.entity.RoleDO;
import com.cdrundle.cdc.security.auth.orm.entity.TokenDO;
import com.cdrundle.cdc.security.auth.orm.entity.UserDO;
import com.cdrundle.cdc.security.auth.service.ITokenAuthApi;
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
import com.cdrundle.cdc.security.auth.util.TokenUtil;
import com.cdrundle.common.bean.IdCodeName;
import com.cdrundle.common.exception.BaseException;
import com.cdrundle.common.exception.FieldCheckException;
import com.cdrundle.common.exception.ObjectIsNullException;
import com.cdrundle.common.util.ObjectFieldCheckUtil;
import com.cdrundle.common.util.OrikaMapperFactory;

/**
 * <P>
 * tokenAuth服务实现
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月4日 上午9:29:43
 */
@Service("tokenAuthApi")
public class TokenAuthApiImpl implements ITokenAuthApi {


	@Autowired
	private ITokenDao tokenDao;

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IAppDao  appDao;

	/**
	 * 一个用户在所有APP只允许一个登陆
	 */
	// private boolean onlyOneLogin = false;

	/**
	 * 一个用户在一个app只允许一个登陆
	 */
	// private boolean onlyAppOneLogin = false;

	/**
	 * 是否允许重复登陆
	 */
	private boolean allowRepeatLogin = true;

	/**
	 * 覆盖登陆
	 */
	private boolean coverLogin = true;

	private OrikaMapperFactory<OfficeDO, IdCodeName> officeMapper =  OrikaMapperFactory.getMapper(OfficeDO.class, IdCodeName.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cdrundle.cdc.security.auth.service.TokenAuthService#tokenAuth(com.
	 * cdrundle.cdc.security.auth.service.bo.Auth)
	 */
	@Override
	@Transactional
	public AuthUserInfoBO tokenAuth(AuthInfoBO authInfoBO)
			throws IpIllegalException, TokenNotExistException, TokenExpiredException, TokenAuthException,
			ObjectIsNullException, TokenInvalidException, FieldCheckException {

		ObjectFieldCheckUtil.checkField(authInfoBO);
		TokenDO tokenDO = tokenDao.findByTokenCode(authInfoBO.getToken());
		// 验证token是否合法
		checkTokenValid(tokenDO, authInfoBO);
		// 更新token失效时间
		tokenDO.setExpiredTime(TokenUtil.generateExpiredTime());
		tokenDao.saveAndFlush(tokenDO);
		// 返回用户信息
		return userDOConverterUserInfo(userDao.findByUserName(tokenDO.getUserName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cdrundle.cdc.security.auth.service.TokenAuthService#generateToken(com
	 * .cdrundle.cdc.security.auth.service.bo.LoginAuth)
	 */
	@Override
	@Transactional(rollbackFor = BaseException.class)
	public String generateToken(LoginAuthBO loginAuthBO) throws UserOrPasswordWrongException, UserStatusException,
			GenerateTokenException, ObjectIsNullException, FieldCheckException, OnlyOneLoginException, WrongAppKeyException {

		ObjectFieldCheckUtil.checkField(loginAuthBO);
		
		//验证登陆平台
		authAppKey(loginAuthBO.getAppKey());
		
		// 查询用户
		UserDO user = userDao.findByUserName(loginAuthBO.getUserName());
		if (user == null || !user.getPassword().equals(loginAuthBO.getPassword())) {
			throw new UserOrPasswordWrongException();
		}
		
		// 判断是否不允许重复登陆
		if (!allowRepeatLogin) {
			if (isRepeatLogin(String.valueOf(user.getId()))) {
				// 判断是否可以覆盖登陆
				if (!coverLogin) {
					throw new OnlyOneLoginException();
				}
				// 修改覆盖其他有效会话状态为失效
				tokenDao.updateUserActiveTokensToInvied(String.valueOf(user.getId()));
			}
		}

		// 保存会话信息，并返回用户信息
		return tokenDao.saveAndFlush(newToken(user, loginAuthBO)).getTokenCode();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cdrundle.cdc.security.auth.service.TokenAuthService#removeToken(java.
	 * lang.String)
	 */
	@Override
	@Transactional(rollbackFor = RemoveTokenException.class)
	public void removeToken(String tokenCode) {
		TokenDO tokenDO = tokenDao.findByTokenCode(tokenCode);
		if (tokenDO != null) {
			tokenDao.delete(tokenDO);
		}
	}

	private AuthUserInfoBO userDOConverterUserInfo(UserDO user) {
		AuthUserInfoBO authUserInfoBO = new AuthUserInfoBO();
		authUserInfoBO.setId(user.getId());
		authUserInfoBO.setName(user.getName());
		authUserInfoBO.setOffice(officeMapper.map(user.getOffice()));
		authUserInfoBO.setUserName(user.getUserName());
		
		List<OfficeDO> officeDOs = user.getOfficeWatch();
		authUserInfoBO.setWatchOffices(officeMapper.collect(officeDOs));
		List<Long> idList = new ArrayList<>();
		if (user.getRoles() != null) {
			for (RoleDO roleDO : user.getRoles()) {
				idList.add(roleDO.getId());
			}
		}
		authUserInfoBO.setRoles(idList);
		return authUserInfoBO;
	}

	/**
	 * 判断用户是否已经存在激活的token
	 * 
	 * @param id
	 */
	private boolean isRepeatLogin(String id) {
		List<TokenDO> tokenDOs = tokenDao.findUserActiveTokens(id);
		return tokenDOs != null && tokenDOs.size() > 0 ? true : false;
	}

	/**
	 * 验证appKey对应的app是否存在
	 * @param appKey
	 * @throws WrongAppKeyException
	 */
	private void authAppKey(String appKey) throws WrongAppKeyException{
		
		AppDO app = appDao.findByAppKey(appKey);
		//判断appkey对应的app是否存在
		if (app == null || app.getId() == null) {
			throw new WrongAppKeyException();
		}
		
	}
	
	/**
	 * 组装token
	 * 
	 * @param userBo
	 * @param auth
	 * @return
	 */
	private TokenDO newToken(UserDO user, LoginAuthBO loginAuthBO) {
		TokenDO tokenDO = new TokenDO();
		tokenDO.setLoginIp(loginAuthBO.getIp());
		tokenDO.setLoginTime(new Date());
		tokenDO.setTokenCode(
				TokenUtil.generateTokenByUUID(loginAuthBO.getAppKey() + loginAuthBO.getIp() + user.getUserName()));
		tokenDO.setUserId(String.valueOf(user.getId()));
		tokenDO.setUserName(user.getUserName());
		tokenDO.setExpiredTime(TokenUtil.generateExpiredTime());
		tokenDO.setActive(true);
		return tokenDO;
	}

	/**
	 * 验证token是否合法
	 * 
	 * @param tokenDO
	 * @param authInfoBO
	 * @throws IpIllegalException
	 * @throws TokenNotExistException
	 * @throws TokenExpiredException
	 * @throws TokenAuthException
	 * @throws TokenInvalidException
	 */
	private void checkTokenValid(TokenDO tokenDO, AuthInfoBO authInfoBO) throws IpIllegalException,
			TokenNotExistException, TokenExpiredException, TokenAuthException, TokenInvalidException {
		// 判断token存在与否
		if (tokenDO == null) {
			throw new TokenNotExistException();
		}
		// 判断token是否可用
		if (!tokenDO.isActive()) {
			throw new TokenInvalidException();
		}
		// 判断是否过期
		if (TokenUtil.tokenIsExpired(tokenDO.getExpiredTime())) {
			throw new TokenExpiredException();
		}
		
		if (allowRepeatLogin) {
			
		}
		
		// 判断请求IP是否与登陆时IP一致
		/*if (!authInfoBO.getIp().equals(tokenDO.getLoginIp())) {
			throw new IpIllegalException();
		}*/

	}
	/**
	 * 验证token是否合法
	 * 
	 * @param tokenDO
	 * @param authInfoBO
	 * @throws IpIllegalException
	 * @throws TokenNotExistException
	 * @throws TokenExpiredException
	 * @throws TokenAuthException
	 * @throws TokenInvalidException
	 */
	@Override
	public void checkTokenValid(String token) throws IpIllegalException,
			TokenNotExistException, TokenExpiredException, TokenAuthException, TokenInvalidException {
		TokenDO tokenDO = tokenDao.findByTokenCode(token);
		// 判断token存在与否
		if (tokenDO == null) {
			throw new TokenNotExistException();
		}
		// 判断token是否可用
		if (!tokenDO.isActive()) {
			throw new TokenInvalidException();
		}
		// 判断是否过期
		if (TokenUtil.tokenIsExpired(tokenDO.getExpiredTime())) {
			throw new TokenExpiredException();
		}
		
		if (allowRepeatLogin) {
			
		}
		
		// 判断请求IP是否与登陆时IP一致
		/*if (!authInfoBO.getIp().equals(tokenDO.getLoginIp())) {
			throw new IpIllegalException();
		}*/

	}

}
