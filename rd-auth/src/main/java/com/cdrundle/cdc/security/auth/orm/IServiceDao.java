/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdrundle.cdc.security.auth.orm.entity.ServiceDO;

/**
 * <P>
 * 服务信息Dao
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2017年2月28日 上午11:09:54
 */
public interface IServiceDao extends JpaRepository<ServiceDO, Long> {

	@Query("select distinct s from ServiceDO s join s.menus m join m.roles r join r.users u where u.id = :id and s.isEnable = true")
	List<ServiceDO> queryUserServices(@Param(value = "id") Long userId);

	List<ServiceDO> findByMenus_id(Long id);

	List<ServiceDO> findByIsPublic(boolean b);
	
	

}
