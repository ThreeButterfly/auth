/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cdrundle.common.bean.Result;
import com.cdrundle.common.exception.BaseException;


/**
 *<P>
 *  统一异常处理
 *<P>
 * @author limaojun
 * @CreateDate 2016年12月29日 上午11:05:37
 */
@ControllerAdvice
public class ControllerExceptionHander {

	
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHander.class);
	
	
	@ExceptionHandler(BaseException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.OK)
	public Result baseExceptionHandler(BaseException e){
		logger.error("",e);
		return Result.fail(e.getMessage());
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.OK)
	public Result exceptionHandler(Throwable e){
		logger.error(this.getClass().getName()+"异常", e);
		return Result.fail("异常"+e.getStackTrace()[0].toString()+e.getMessage());
	}
	
}
