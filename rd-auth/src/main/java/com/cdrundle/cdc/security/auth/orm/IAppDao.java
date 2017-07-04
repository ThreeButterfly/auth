/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.cdrundle.cdc.security.auth.orm.entity.AppDO;

/**
 *<P>
 *  应用表Dao
 *<P>
 * @author limaojun
 * @CreateDate 2017年2月24日 下午4:40:56
 */
public interface IAppDao extends JpaRepository<AppDO, String> {

	@QueryHints(@QueryHint(name="org.hibernate.cacheable",value="true"))
	public AppDO findByAppKey(String key);
	
	
}
