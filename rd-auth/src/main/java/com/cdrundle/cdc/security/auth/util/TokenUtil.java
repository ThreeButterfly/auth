/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdrundle.cdc.security.auth.service.bo.AuthInfoBO;

/**
 * <P>
 * token工具
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年1月5日 下午4:24:12
 */
public class TokenUtil {

	public static int TOKEN_EXPIRED_TIME = 30;
	
	
	private static String ABC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String generateTokenByUUID(String key) {

		key =  key + RandomStringUtils.random(5, ABC);
		StringBuffer token = new StringBuffer();
		token.append(UUID.randomUUID().toString().replaceAll("-", ""));
		token.append(UUID.nameUUIDFromBytes(key.getBytes()).toString().replaceAll("-", ""));

		return token.toString();

	}

	public static Date generateExpiredTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, TOKEN_EXPIRED_TIME);

		return calendar.getTime();
	}

	/**
	 * 判断当前会话是否失效
	 * 
	 * @param expiredTime
	 * @return true 失效，false 有效
	 */
	public static boolean tokenIsExpired(Date expiredTime) {
		return expiredTime.compareTo(new Date()) == 1 ? false : true;

	}

	public static String getIP(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return (ip.indexOf(",")>0) ? StringUtils.split(ip,"\\,")[0]:ip;
	}

	
	public static void main(String[] args) {

		AuthInfoBO authInfoBO = new AuthInfoBO();
		authInfoBO.setAppKey("111");
		System.out.println(RandomStringUtils.randomNumeric(5));
		System.out.println(RandomStringUtils.random(5, ABC));
		Random random = new Random();
		System.out.println(random.nextInt(100));
		for (int i = 0; i < 10; i++) {
			System.out.println(TokenUtil.generateTokenByUUID("123"));
		}
		
		/*Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.add(Calendar.MINUTE, 30);
		calendar.add(Calendar.MINUTE, -TOKEN_EXPIRED_TIME);
		Date oDate = calendar.getTime();

		System.out.println(TokenUtil.tokenIsExpired(oDate));

		int i = date.compareTo(oDate);
		int j = oDate.compareTo(date);
		int k = date.compareTo(date);

		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(TokenUtil.generateTokenByUUID("123"));
		System.out.println(TokenUtil.generateTokenByUUID("kkkk"));
		System.out.println(TokenUtil.generateTokenByUUID("kkkk"));*/
	}
}
