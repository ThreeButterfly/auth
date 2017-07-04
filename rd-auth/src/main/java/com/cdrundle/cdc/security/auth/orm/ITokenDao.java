/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdrundle.cdc.security.auth.orm.entity.TokenDO;



/**
 *<P>
 *  Token数据库操作
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月4日 上午9:31:17
 */
public interface ITokenDao extends JpaRepository<TokenDO, String> {

	public TokenDO findByTokenCode(String tokenCode);
	
	@Query("select t from TokenDO t where t.userId=:id and t.isActive=true")
	public List<TokenDO> findUserActiveTokens(@Param(value = "id") String Id);
	
	@Modifying
	@Query("update TokenDO set isActive=false where userId=:id and isActive=true ")
	public void updateUserActiveTokensToInvied(@Param(value="id") String Id);
	
	
}
