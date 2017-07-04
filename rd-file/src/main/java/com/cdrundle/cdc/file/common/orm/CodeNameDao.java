/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.common.orm;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;



/**
 *<P>
 *  为具有code、name的实体提供根据code、name进行的相关操作
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午4:20:06
 */
@NoRepositoryBean
public interface CodeNameDao<T extends CodeNameEntity> extends CodeDao<T> {
	
	public T findByName(String name);
	
	public List<T> findByNameLike(String name);

	public List<T> findByCodeOrName(String code,String name);
	
}
