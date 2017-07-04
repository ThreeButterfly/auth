/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.cdrundle.cdc.security.auth.orm.entity.UserDO;

/**
 *<P>
 *  用户DAO
 *<P>
 * @author limaojun
 * @CreateDate 2017年2月27日 上午11:27:21
 */
public interface IUserDao extends JpaRepository<UserDO, Long> {

	@QueryHints(@QueryHint(name="org.hibernate.cacheable",value="true"))
	UserDO findByUserName(String username);

	
}
