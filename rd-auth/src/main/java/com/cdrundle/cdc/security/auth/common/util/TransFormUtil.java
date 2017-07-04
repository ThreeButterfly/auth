/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import com.cdrundle.cdc.security.auth.common.orm.LongIdEntity;

/**
 *<P>
 *  转换工具
 *<P>
 * @author limaojun
 * @CreateDate 2017年3月6日 上午10:25:45
 */
public class TransFormUtil {

	/**
	 * 获取DO中的ID列表，结果为Long字段集合
	 * @param input
	 * @return
	 */
	public static List<Long> idsCollectToLong(Iterable<? extends LongIdEntity> input){
		
		if (input != null) {
			return CollectionUtils.collect(input, new Transformer<LongIdEntity, Long>() {
				@Override
				public Long transform(LongIdEntity input) {
					return input.getId();
				}
			},new ArrayList<Long>());
		}
		return null;
	}

	 /**
	 * 获取DO中的ID列表，结果为String字段集合
	 * @param input
	 * @return 
	 */
	public static List<String> idsCollectToString(Iterable<? extends LongIdEntity> input){
		
		if (input != null) {
			return CollectionUtils.collect(input, new Transformer<LongIdEntity, String>() {
				@Override
				public String transform(LongIdEntity input) {
					return String.valueOf(input.getId());
				}
			},new ArrayList<String>());
		}
		return null;
	}
	
	
}
