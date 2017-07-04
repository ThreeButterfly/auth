/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi;
import com.cdrundle.cdc.security.auth.service.ITokenAuthApi;
import com.cdrundle.cdc.security.auth.service.bo.LoginAuthBO;
import com.cdrundle.cdc.security.auth.service.exception.TokenNotExistException;
import com.cdrundle.cdc.security.auth.util.TokenUtil;
import com.cdrundle.common.bean.Result;
import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * Token服务
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2016年12月19日 下午5:53:08
 */
@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private ITokenAuthApi tokenAuthService;

	@Resource(name = "privilegeAuthApi")
	private IPrivilegeAuthApi privilegeAuthApi;
	/**
	 * 登陆获取token
	 * 
	 * @param userName
	 * @param pwd
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Result getToken(@RequestParam String userName, @RequestParam String pwd,
			@RequestParam String appKey,HttpServletRequest request,HttpServletResponse response) throws BaseException {
		String token =  tokenAuthService.generateToken(new LoginAuthBO(userName, pwd, appKey, TokenUtil.getIP(request)));
		privilegeAuthApi.cacheServiceAndReportPrivilege(token);
		return Result.success("成功",token);
	}

	@RequestMapping("/logout")
	@ResponseBody
	public Result removeToken(HttpServletRequest request) throws BaseException {
		String token=request.getHeader("token");
		if(StringUtils.isNotBlank(token)){
			privilegeAuthApi.removePrivilegeCache(token);
			tokenAuthService.removeToken(token);
		}
		return Result.success("注销成功");
	}

	/*@RequestMapping("/list/user/{id}")
	@ResponseBody
	 public Result listUserServices(@PathVariable("id") Long id){
		 return Result.success(privilegeAuthApi.listUserServices(id));
	 }*/
	@RequestMapping("/check")
	@ResponseBody
	public Result checkToken(HttpServletRequest request,String token) throws BaseException {
		String tokenH=request.getHeader("token");
		String tokenToBeCheck=null;
		if(StringUtils.isNotBlank(tokenH)){
			tokenToBeCheck=tokenH;
		}else{
			tokenToBeCheck=token;
		}
		try {
			if(StringUtils.isBlank(tokenToBeCheck)){
				throw new TokenNotExistException();
			}
			tokenAuthService.checkTokenValid(tokenToBeCheck);
			privilegeAuthApi.cacheServiceAndReportPrivilege(tokenToBeCheck);
			return Result.success("token验证通过");
		} catch (Exception e) {
			return Result.fail("token过期");
		}
	}
	

}
