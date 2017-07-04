/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdrundle.cdc.security.auth.common.util.TransFormUtil;
import com.cdrundle.cdc.security.auth.orm.IReportDao;
import com.cdrundle.cdc.security.auth.orm.IServiceDao;
import com.cdrundle.cdc.security.auth.orm.ITokenDao;
import com.cdrundle.cdc.security.auth.orm.IUserDao;
import com.cdrundle.cdc.security.auth.orm.entity.MenuDO;
import com.cdrundle.cdc.security.auth.orm.entity.ReportDO;
import com.cdrundle.cdc.security.auth.orm.entity.RoleDO;
import com.cdrundle.cdc.security.auth.orm.entity.ServiceDO;
import com.cdrundle.cdc.security.auth.orm.entity.TokenDO;
import com.cdrundle.cdc.security.auth.orm.entity.UserDO;
import com.cdrundle.cdc.security.auth.orm.entity.UserLinkMenuDO;
import com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi;
import com.cdrundle.cdc.security.auth.service.bo.MenuBo;
import com.cdrundle.cdc.security.auth.service.exception.NoPrivilegeException;
import com.cdrundle.common.util.OrikaMapperFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * <P>
 * 权限服务实现类
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年2月27日 下午3:22:42
 */
@Service("privilegeAuthApi")
@Transactional(readOnly=true)
public class PrivilegeAuthApiImpl implements IPrivilegeAuthApi {


	@Autowired
	private IServiceDao serviceDao;
	
	@Autowired
	private IReportDao reportDao;
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ITokenDao tokenDao;
	
	@Resource(name="ehCache")
	private Cache  ehCache;  
//	@Autowired
//	private IMenuService menuService;
    
	private OrikaMapperFactory<MenuDO, MenuBo> boundMapper =  
				OrikaMapperFactory.getMapper(MenuDO.class, MenuBo.class);
	private static final String SERVICE_SUFFIX="_service";
	private static final String REPORT_SUFFIX="_report";
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi#listUserServices
	 * (java.lang.Long)
	 */
	@Override
	@Transactional
	public List<String> listUserServices(Long userId) {
		//List<ServiceDO> list = serviceDao.queryUserServices(userId);
		return TransFormUtil.idsCollectToString(serviceDao.queryUserServices(userId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi#
	 * listUserAllowOffices(java.lang.Long)
	 */
	@Override
	public List<String> listUserAllowOffices(Long userId) {
		UserDO  userDO = userDao.findOne(userId);
		if (userDO != null && userDO.getOfficeWatch() != null) {
			return TransFormUtil.idsCollectToString(userDO.getOfficeWatch());
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi#listUserReports(java.lang.Long)
	 */
	@Override
	public List<String> listUserReports(Long userId) {
		//List<ReportDO> list = reportDao.queryUserReports(userId);
		return TransFormUtil.idsCollectToString(reportDao.queryUserReports(userId));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi#serviceAuth(java
	 * .lang.Long, java.lang.String)
	 */
	@Override
	public void serviceAuth(Long userId, String servicePath) throws NoPrivilegeException {
//		List<String> list = listUserServices(userId);
//		if (list == null || list.size() < 1 || !list.contains(servicePath)) {
//			throw new NoPrivilegeException();
//		}
		Element pri=ehCache.get(userId+SERVICE_SUFFIX);
		if(pri!=null){
			List<String> urls=(List<String>) pri.getObjectValue();
			if(CollectionUtils.isEmpty(urls)){
				throw new NoPrivilegeException(servicePath);
			}else{
				if(!urls.contains(servicePath)){
					throw new NoPrivilegeException(servicePath);
				}
			}
		}else{
			throw new NoPrivilegeException(servicePath);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cdrundle.cdc.security.auth.service.IPrivilegeAuthApi#reportAuth(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public void reportAuth(Long userId, String reportUrl) throws NoPrivilegeException {
//		List<String> list = listUserReports(userId);
//		if (list == null || list.size() < 1 || !list.contains(reportUrl)) {
//			throw new NoPrivilegeException();
//		}
		
		Element pri=ehCache.get(userId+REPORT_SUFFIX);
		if(pri!=null){
			List<String> urls=(List<String>) pri.getObjectValue();
			if(CollectionUtils.isEmpty(urls)){
				throw new NoPrivilegeException(reportUrl);
			}else{
				if(!urls.contains(reportUrl)){
					throw new NoPrivilegeException(reportUrl);
				}
			}
		}else{
			throw new NoPrivilegeException(reportUrl);
		}
	}
	public Map<Long,MenuBo> getLeafMenuByToken(String token) {
		TokenDO tkDo=tokenDao.findByTokenCode(token);
		Long userId=Long.parseLong(tkDo.getUserId());
		UserDO user=userDao.findOne(userId);
		Map<Long,MenuBo> menuMap=new HashMap<>();
		//用户直接关联的菜单
		List<UserLinkMenuDO> linkMenus=user.getUserLinkMenus();
		if(CollectionUtils.isNotEmpty(linkMenus)){
			for (UserLinkMenuDO userLinkMenuDO : linkMenus) {
				MenuDO me=userLinkMenuDO.getMenu();
				if(me.getIsLeaf()&&me.getIsEnable()){
					menuMap.put(me.getId(), boundMapper.map(me));
				}
			}
		}
		//用户角色关联的菜单
		List<RoleDO> roles=user.getRoles();
		if(CollectionUtils.isNotEmpty(roles)){
			for (RoleDO roleDO : roles) {
				List<MenuDO> menus=roleDO.getMenus();
				if(CollectionUtils.isNotEmpty(menus)){
					for (MenuDO menuDO : menus) {
						if(menuDO.getIsLeaf()&&menuDO.getIsEnable()){
							menuMap.put(menuDO.getId(), boundMapper.map(menuDO));
						}
					}
				}
			}
		}
		return menuMap;
	}
	@Override
	public void cacheServiceAndReportPrivilege(String token) {
		TokenDO tokenDO = tokenDao.findByTokenCode(token);
		Long userId=Long.parseLong(tokenDO.getUserId());
		
		List<String> serviceUrl=new ArrayList<>();
		List<String> reportUrl=new ArrayList<>();
		Map<Long, MenuBo>  mb=getLeafMenuByToken(token);
		if(mb!=null&&mb.values()!=null){
			List<MenuBo> mlist=new ArrayList<>(mb.values());
			for (MenuBo bo : mlist) {
				List<ServiceDO> slist=serviceDao.findByMenus_id(bo.getId());
				if(CollectionUtils.isNotEmpty(slist)){
					for (ServiceDO sdo : slist) {
						if(sdo.getIsEnable()){
							serviceUrl.add(sdo.getUrl());
						}
					}
				}
	
				List<ReportDO> rlist=reportDao.findByMenus_id(bo.getId());
				if(CollectionUtils.isNotEmpty(rlist)){
					for (ReportDO rdo : rlist) {
						if(rdo.getIsEnable()){
							reportUrl.add(rdo.getTemplatePath());
						}
					}
				}
			}
			List<ServiceDO> pbslist=serviceDao.findByIsPublic(true);
			if(CollectionUtils.isNotEmpty(pbslist)){
				for (ServiceDO serviceDO : pbslist) {
					serviceUrl.add(serviceDO.getUrl());
				}
			}
		}
		String serviceKey=userId+SERVICE_SUFFIX;
		String reportKey=userId+REPORT_SUFFIX;
		Element servicePri=ehCache.get(serviceKey);
		if(servicePri!=null){
			ehCache.remove(serviceKey);
		}
		servicePri=new Element(serviceKey,serviceUrl);
		ehCache.put(servicePri);
		
		Element reportPri=ehCache.get(reportKey);
		if(reportPri!=null){
			ehCache.remove(reportKey);
		}
		reportPri=new Element(reportKey,reportUrl);
		ehCache.put(reportPri);
	}

	@Override
	public void removePrivilegeCache(String token) {
		TokenDO tokenDO = tokenDao.findByTokenCode(token);
		if(tokenDO!=null){
			String userId=tokenDO.getUserId();
			String serviceKey=userId+SERVICE_SUFFIX;
			String reportKey=userId+REPORT_SUFFIX;
			Element servicePri=ehCache.get(serviceKey);
			if(servicePri!=null){
				ehCache.remove(serviceKey);
			}
			Element reportPri=ehCache.get(reportKey);
			if(reportPri!=null){
				ehCache.remove(reportKey);
			}
		}
	}


}
