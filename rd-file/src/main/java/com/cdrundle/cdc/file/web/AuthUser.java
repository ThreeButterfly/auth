/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *<P>
 *  在controll方法中加入AuthUserInfoBO 类，并在参数上加入此注解，spring框架将会自动解析
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月10日 上午10:11:33
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthUser {

}
