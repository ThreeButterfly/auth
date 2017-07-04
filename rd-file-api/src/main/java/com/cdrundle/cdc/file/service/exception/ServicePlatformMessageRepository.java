/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.service.exception;

import com.cdrundle.common.exception.AbstractMessageRepository;

/**
 * <P>
 * 服务平台通用异常提示信息库
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2016年12月29日 下午2:41:37
 */
public class ServicePlatformMessageRepository extends AbstractMessageRepository {

	private String file = "service_message.properties";

	private ServicePlatformMessageRepository() {
	};

	public static ServicePlatformMessageRepository getInstance() {
		return RepositoryHolder.REPOSITORY;
	}

	private static class RepositoryHolder {
		private static final ServicePlatformMessageRepository REPOSITORY = new ServicePlatformMessageRepository();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cdrundle.common.exception.AbstractMessageRepository#
	 * getMessageFilePath()
	 */
	@Override
	public String getMessageFilePath() {
		return file;
	}

	public static final String NO_TOKEN = "NO_TOKEN";

	public static final String NO_NO_APP_KEY = "NO_APP_KEY";

	public static final String NO_DATA_PRIVILEGE = "NO_DATA_PRIVILEGE";
	
	public static final String DATA_PRIVILEGE_EXCEPTION = "DATA_PRIVILEGE_EXCEPTION";

}
