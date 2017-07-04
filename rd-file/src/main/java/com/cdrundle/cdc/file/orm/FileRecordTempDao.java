/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileRecordDao.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月16日 下午12:01:27
 *
 *    Revision:
 *
 *    2017年5月16日 下午12:01:27
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.orm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdrundle.cdc.file.orm.entity.FileRecordTempDO;

/**
 * <P>
 * TODO：文件记录表
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月16日 下午12:01:27
 */
public interface FileRecordTempDao extends JpaRepository<FileRecordTempDO, Integer> {
	/**
	 * 保存文件系统记录信息
	 */
	public FileRecordTempDO save(FileRecordTempDO file);
	public void deleteByFileId(String fileId);
	public FileRecordTempDO findByFileId(String fileId);
}
