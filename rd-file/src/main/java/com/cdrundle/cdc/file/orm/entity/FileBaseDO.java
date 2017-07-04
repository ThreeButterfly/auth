/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileBaseDO.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月18日 下午10:58:47
 *
 *    Revision:
 *
 *    2017年5月18日 下午10:58:47
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.orm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <P>
 * TODO：文件基类
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月18日 下午01:58:47
 */
@MappedSuperclass
public class FileBaseDO implements Serializable {

	private static final long serialVersionUID = 2443987770309926597L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "file_id", length = 50,nullable = false,unique=true)
	private String fileId;
	
	@Column(name = "file_name", length = 150)
	private String fileName;

	@Column(name = "file_url", length = 150)
	private String fileUrl;

	@Column(name = "thumb_url", length = 150)
	private String thumbUrl;

	@Column(name = "file_size")
	private String fileSize;

	@Column(name = "file_suffix", length = 10)
	private String fileSuffix;

	@Column(name = "file_dfs_id", length = 150)
	private String fileDfsId;

	@Column(name = "status")
	private String status;


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileDfsId() {
		return fileDfsId;
	}

	public void setFileDfsId(String fileDfsId) {
		this.fileDfsId = fileDfsId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

 

 
}
