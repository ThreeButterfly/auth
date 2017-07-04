/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.common.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *<P>
 *  为具有code的实体提供根据code进行的相关操作
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月16日 下午4:29:22
 */
@NoRepositoryBean
public interface CodeDao<T extends CodeEntity> extends JpaRepository<T, Long> {

	public T findByCode(String  code);
	
}
