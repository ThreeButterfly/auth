/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdrundle.cdc.security.auth.orm.entity.ReportDO;

/**
 *<P>
 *  报表信息Dao
 *<P>
 * @author limaojun
 * @CreateDate 2017年2月28日 下午3:01:23
 */
public interface IReportDao extends JpaRepository<ReportDO, Long> {
	
	@Query("select distinct r from ReportDO r join  r.menus rm join rm.roles ro join ro.users u where u.id = :id and r.isEnable = true")
	List<ReportDO> queryUserReports(@Param(value="id") Long userId);

	List<ReportDO> findByMenus_id(Long id);
	
	
}
