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

import com.cdrundle.cdc.file.orm.entity.FileRecordDO;


/**
 *<P>
 *  TODO：文件记录表
 *<P>
 * @author Tjee
 * @CreateDate 2017年5月16日 下午12:01:27
 */
public interface FileRecordDao  extends JpaRepository<FileRecordDO, String> {
	public FileRecordDO save(FileRecordDO file);
	public void deleteByFileId(String fileId);
	public FileRecordDO findByFileId(String fileId);
	 
}
