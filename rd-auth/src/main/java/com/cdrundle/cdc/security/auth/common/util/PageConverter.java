/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.common.util;

import org.springframework.core.convert.converter.Converter;

import com.cdrundle.common.util.OrikaMapperFactory;

/**
 *<P>
 * spring converter
 *<P>
 * @author limaojun
 * @CreateDate 2016年7月4日 下午2:53:50
 */
public class PageConverter<I,O> implements Converter<I, O> {

	private OrikaMapperFactory<I, O> factory;
	
	public PageConverter(Class<I> in,Class<O> out) {
		factory = OrikaMapperFactory.getMapper(in, out);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public O convert(I source) {
		return factory.map(source);
	}
	
	
}
