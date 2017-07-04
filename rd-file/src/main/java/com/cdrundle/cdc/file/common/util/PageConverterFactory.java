/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 *<P>
 *  spring Converter 工厂
 *<P>
 * @author limaojun
 * @CreateDate 2016年7月4日 上午11:19:49
 */
public class PageConverterFactory {

	private Map<Class<?>, Map<Class<?>,PageConverter<?, ?>>> converterMapper;
	
	private PageConverterFactory(){
		converterMapper = new HashMap<>();
	}
	public static PageConverterFactory getInstance(){
		return  PageConverterFactoryHolder.factory;
	}
	
	private static class PageConverterFactoryHolder{
		private static final PageConverterFactory factory = new PageConverterFactory();
	}
	
	@SuppressWarnings("unchecked")
	public synchronized <I,O>PageConverter<I, O> getConverter(Class<I> in,Class<O> out){
		
		Map<Class<?>,PageConverter<?, ?>> pmap = converterMapper.get(in);
		if (pmap == null) {
			pmap = new HashMap<>();
			converterMapper.put(in, pmap);
		}
		
		PageConverter<?, ?> converter = pmap.get(out);
		if (converter == null) {
			converter = new PageConverter<>(in, out);
			pmap.put(out, converter);
		}
		return (PageConverter<I, O>) converter;
	}
	
}
