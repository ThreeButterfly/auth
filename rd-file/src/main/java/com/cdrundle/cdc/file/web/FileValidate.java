/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileValidate.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月22日 下午12:54:16
 *
 *    Revision:
 *
 *    2017年5月22日 下午12:54:16
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cdrundle.cdc.file.common.util.FileValidateCode;

/**
 * <P>
 * TODO：此注解，spring框架将会自动解析
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月22日 下午12:54:16
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface FileValidate {
	FileValidateCode value() default FileValidateCode.DEFAULT;
}
