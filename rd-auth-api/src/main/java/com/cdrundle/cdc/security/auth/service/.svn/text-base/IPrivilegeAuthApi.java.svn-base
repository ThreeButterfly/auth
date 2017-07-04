/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service;

import java.util.List;

import com.cdrundle.cdc.security.auth.service.exception.NoPrivilegeException;

/**
 *<P>
 *  权限验证服务
 *<P>
 * @author limaojun
 * @CreateDate 2017年2月27日 下午12:11:17
 */
public interface IPrivilegeAuthApi {

	/**
	 * 验证用户能否访问所请求的服务
	 * @param userId
	 * @param servicePath
	 * @return
	 */
	void serviceAuth(Long userId,String servicePath) throws NoPrivilegeException;
	
	/**
	 * 验证用户能否访问所请求的报表地址
	 * @param userId
	 * @param reportUrl
	 * @return
	 */
	void reportAuth(Long userId,String reportUrl) throws NoPrivilegeException;
	
	/**
	 * 获取用户可以访问的服务列表
	 * @param userId
	 * @return
	 */
	List<String> listUserServices(Long userId);
	
	/**
	 * 获取用户可以访问数据的科室列表
	 * @param userId
	 * @return
	 */
	List<String> listUserAllowOffices(Long userId);
	
	/**
	 * 获取用户可以访问的报表列表
	 * @param userId
	 * @return
	 */
	List<String> listUserReports(Long userId);
	/**
	 * 缓存用户权限
	 * @param token
	 */
	void cacheServiceAndReportPrivilege(String token);
	/**
	 * 删除缓存的权限
	 * @param token
	 */
	void removePrivilegeCache(String token);


	
	
}
