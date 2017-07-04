/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FastDFSClient.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月17日 下午2:52:37
 *
 *    Revision:
 *
 *    2017年5月17日 下午2:52:37
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.cdrundle.cdc.file.service.exception.DeleteFileException;
import com.cdrundle.cdc.file.service.exception.FileDownloadFailException;
import com.cdrundle.cdc.file.service.exception.FileGetFailException;
import com.cdrundle.cdc.file.service.exception.FileUploadFailException;
import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * TODO：文件系统客户端工具类
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月17日 下午2:52:37
 */
public class FastDFSClient {

	private static final String CONF_FILENAME = System.getProperty("file.root") + File.separator + "WEB-INF"
			+ File.separator + "classes" + File.separator + "fdfs_client.conf";
	//linux 具体目录 建议放在文件系统安装根目录
	//private static final String CONF_FILENAME ="/fastdfs/fdfs_client.conf";
	// private static final InputStream inStream =
	// FastDFSClient.class.getClassLoader().getResourceAsStream("fastdfs-client.properties");
	private static StorageClient1 storageClient = null;
	private static InetSocketAddress inetSockAddr = null;

	/**
	 * 只加载一次.
	 */
	static {
		try {
			// Properties props = new Properties();
			// dprops.load(inStream);
			// ClientGlobal.initByProperties(props);
			// ClientGlobal.configInfo();
			ClientGlobal.init(CONF_FILENAME);
			TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
			TrackerServer trackerServer = trackerClient.getConnection();
			inetSockAddr = trackerServer.getInetSocketAddress();
			StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
			storageClient = new StorageClient1(trackerServer, storageServer);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名
	 * @return 返回Null则为失败
	 */
	public static String uploadFile(File file, String fileSuffix) throws BaseException {
		FileInputStream fis = null;
		try {
			NameValuePair[] meta_list = null; // new NameValuePair[0];
			fis = new FileInputStream(file);
			byte[] file_buff = null;
			if (fis != null) {
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}

			String fileid = storageClient.upload_file1(file_buff, fileSuffix, meta_list);
			return fileid;
		} catch (Exception ex) {
			throw new FileUploadFailException();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new FileUploadFailException();
				}
			}
		}
	}

	/**
	 * 
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名
	 * @return 返回Null则为失败
	 */
	public static String uploadFileByStream(FileInputStream fis, String fileSuffix) throws FileUploadFailException {
		try {
			NameValuePair[] meta_list = null; // new NameValuePair[0];
			byte[] file_buff = null;
			if (fis != null) {
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}

			String fileid = storageClient.upload_file1(file_buff, fileSuffix, meta_list);
			return fileid;
		} catch (Exception ex) {
			throw new FileUploadFailException();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new FileUploadFailException();
				}
			}
		}
	}

	/**
	 * 根据组名和远程文件名来删除一个文件
	 * 
	 * @param groupName
	 *            例如 "group1" 如果不指定该值，默认为group1
	 * @param fileName
	 *            例如"M00/00/00/wKgxgk5HbLvfP86RAAAAChd9X1Y736.jpg"
	 * @return 0为成功，非0为失败，具体为错误代码
	 */
	public static int deleteFile(String groupName, String fileName) throws BaseException {
		try {
			int result = storageClient.delete_file(groupName == null ? "group1" : groupName, fileName);
			return result;
		} catch (Exception ex) {
			throw new DeleteFileException();
		}
	}

	/**
	 * 根据fileId来删除一个文件（我们现在用的就是这样的方式，上传文件时直接将fileId保存在了数据库中）
	 * 
	 * @param fileId
	 *            file_id源码中的解释file_id the file id(including group name and
	 *            filename);例如
	 *            group1/M00/00/00/wKgJIVkj0e-AMT2fAABi38q98ac327.jpg
	 * @return 0为成功，非0为失败，具体为错误代码
	 * @throws BaseException
	 */
	public static int deleteFile(String fileId) throws BaseException {
		try {
			int result = storageClient.delete_file1(fileId);
			return result;
		} catch (Exception ex) {
			throw new DeleteFileException();
		}
	}

	/**
	 * 修改一个已经存在的文件
	 * 
	 * @param oldFileId
	 *            原来旧文件的fileId, file_id源码中的解释file_id the file id(including group
	 *            name and filename);例如
	 *            group1/M00/00/00/ooYBAFM6MpmAHM91AAAEgdpiRC0012.xml
	 * @param file
	 *            新文件
	 * @param filePath
	 *            新文件路径
	 * @return 返回空则为失败
	 */
	public static String modifyFile(String oldFileId, File file, String filePath) throws BaseException {
		String fileid = null;
		try {
			// 先上传
			fileid = uploadFile(file, filePath);
			if (fileid == null) {
				return null;
			}
			// 再删除
			int delResult = deleteFile(oldFileId);
			if (delResult != 0) {
				return null;
			}
		} catch (Exception ex) {
			throw new DeleteFileException();
		}
		return fileid;
	}

	/**
	 * 文件下载
	 * 
	 * @param fileId
	 * @return 返回一个流
	 */
	public static InputStream downloadFile(String fileId) throws BaseException {
		try {
			byte[] bytes = storageClient.download_file1(fileId);
			InputStream inputStream = new ByteArrayInputStream(bytes);
			return inputStream;
		} catch (Exception ex) {
			throw new FileDownloadFailException();
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param fileId
	 * @return 返回一个二进制流
	 */
	public static byte[] downloadBytes(String fileId) throws BaseException {
		try {
			byte[] bytes = storageClient.download_file1(fileId);
			return bytes;
		} catch (Exception ex) {
			throw new FileDownloadFailException();
		}
	}

	/**
	 * 
	 * @Title: getUrl
	 * @Description: TODO(根据抓取文件系统的路径，转换为可访问URL)
	 * @param @param
	 *            fileId
	 * @param @return
	 * @param @throws
	 *            UnsupportedEncodingException
	 * @param @throws
	 *            NoSuchAlgorithmException
	 * @param @throws
	 *            MyException 参数
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年5月17日
	 */
	public static String getUrl(String fileId) throws BaseException {
		if (StringUtils.isBlank(fileId)) {
			return "";
		}
		int ts = 0;
		String token = null;
		// 不带组名
		String id = fileId.substring(fileId.indexOf("/") + 1);
		String fileUrl = "http://" + inetSockAddr.getHostName();
		if (ClientGlobal.g_tracker_http_port != 80) {
			fileUrl += ":" + ClientGlobal.g_tracker_http_port;
		}
		fileUrl += "/" + fileId;
		if (ClientGlobal.g_anti_steal_token) {
			ts = (int) (System.currentTimeMillis() / 1000);
			try {
				token = ProtoCommon.getToken(id, ts, ClientGlobal.g_secret_key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new FileGetFailException();
			}
			fileUrl += "?token=" + token + "&ts=" + ts;
		}
		return fileUrl;
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws MyException
	 */
	public static void main(String[] args) throws Exception {
		String filePath = "G:\\1.jpg";
		File file = new File(filePath);
		String file_id = FastDFSClient.uploadFile(file, "1.jpg");
		System.err.println(getUrl(file_id));

	}
}
