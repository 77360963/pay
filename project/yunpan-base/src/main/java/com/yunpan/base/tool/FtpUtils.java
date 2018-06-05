package com.yunpan.base.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ftp文件上传下载工具类
 * @author hiiso
 * 2017-12-27
 */
public class FtpUtils {
	

	private static Logger logger = LoggerFactory.getLogger(FtpUtils.class);
	private static FTPClient ftpClient;
	private  String ip;		//FTP服务器(host name 或ip地址)  
	private  int port;		//FTP服务器端口  
	private  String userName;	//FTP服务器基础目录
	private  String userPwd;	//FTP登录密码 
	private  String path;		//指定ftp文件路径

	public FtpUtils() {
	}

	/**
	 * init ftp servere
	 */
	public FtpUtils(String ip, int port, String userName, String userPwd, String path) {
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.userPwd = userPwd;
		this.path = path;
	}

	public void reSet() {
		// // 以当前系统时间拼接文件名
		// fileName = "1-106290060510001-20150126-00599.txt";
		// strencoding = "GBK";
		// this.connectServer(ip, port, userName, userPwd, path);
	}

	/**
	 * 连接ftp服务器
	 * @throws Exception
	 */
	public void connectServer() throws Exception {
		ftpClient = new FTPClient();
		// 连接
		if (port > -1) {
			ftpClient.connect(ip, port);
		} else {
			ftpClient.connect(ip);
		}
		// 登录
		ftpClient.enterLocalActiveMode();//外网连接时用主动模式
		ftpClient.login(userName, userPwd);
		if (path != null && path.length() > 0) {
			// 跳转到指定目录
			ftpClient.changeWorkingDirectory(path);
		}

	}

	/**
	 * 关闭连接
	 */
	public void closeServer() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
	}
	/**  
	 * Description: 向FTP服务器上传文件  
	 * @param basePath FTP服务器基础目录 
	 * @param filePath FTP服务器文件存放路径。(文件的路径为basePath+filePath) 
	 * @param filename 上传到FTP服务器上的文件名  
	 * @param input 输入流  
	 * @return 成功返回true，否则返回false  
	 */
	public boolean uploadFile(String basePath,String filePath, String filename, InputStream input) {
		boolean result = false;
		try {
			int reply;
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				logger.error("ftp连接失败！");
				return result;
			}
			// 切换到上传目录
			if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftpClient.changeWorkingDirectory(tempPath)) {
						tempPath = new String(tempPath.toString().getBytes("GBK"),"iso-8859-1"); 
						if (!ftpClient.makeDirectory(tempPath)) {
							logger.error("ftp资料上传失败:创建目录失败！");
							return result;
						} else {
							ftpClient.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件
			//ftpClient.enterLocalPassiveMode();
			//InputStream input1 = new FileInputStream(new File("C:\\app\\temp\\data\\2018-01\\2018-01-22\\a.jpg"));
			boolean isUploaded = ftpClient.storeFile(filename, input);
			logger.info("#文件是否被上传到ftp：{}",isUploaded+"文件名："+filename);
			if (!isUploaded) {
				logger.error("ftp资料上传失败！");
				return result;
			}
			input.close();
			ftpClient.logout();
			result = true;
			logger.warn("#ftp资料上传成功,文件路径："+filePath+";文件名称："+filename);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ftp资料上传失败：{}", e.getMessage());
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	
	/**
	 * Description: 从FTP服务器下载指定路径下的所有文件
	 * @param remotePath  FTP服务器上的相对路径
	 * @param localPath  下载后保存到本地的路径
	 * @return
	 */
	public boolean downloadFile( String remotePath, String localPath) {
		boolean result = false;
		try {
			File file = new File(localPath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			int reply;
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return result;
			}
			ftpClient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftpClient.listFiles();
			List<String> ftpFNList = new ArrayList<String>();
			int count = 0;
			for (FTPFile ff : fs) {
				File localFile = new File(localPath + "/" + ff.getName());
				OutputStream is = new FileOutputStream(localFile);
				ftpClient.retrieveFile(ff.getName(), is);
				is.close();
				ftpFNList.add(ff.getName());
				count++;
			}
			logger.info("##ftp文件下载["+count+"]个；文件名："+ftpFNList);
			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 */
	public void uploadFile(String filename, File file) {
		try {
			InputStream input = new ByteArrayInputStream(readTxtFile(file).getBytes("GBK"));
			ftpClient.storeFile(filename, input);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}

	public String readTxtFile(File file) {
		StringBuffer fileContent = new StringBuffer();
		try {
			String encoding = "GBK";
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					fileContent.append(lineTxt);
					fileContent.append("\r\n");
				}
				read.close();
			} else {
				logger.info("文件不存在");
			}
			return fileContent.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	/**
	 * @param path
	 * @return function:读取指定目录下的文件名
	 * @throws IOException
	 */
	public List<String> getFileList(String path) {
	
		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			if (file.isFile()) {
				fileLists.add(file.getName());
			}
		}
		return fileLists;
	}

	/**
	 * 读取ftp txt文件第一行
	 * 
	 * @param fileName
	 * @return function:从服务器上读取指定的文件
	 * @throws ParseException
	 * @throws IOException
	 */
	public String readFile(String fileName, String strencoding) throws Exception {
		InputStream ins = null;
		// 从服务器上读取指定的文件
		ins = ftpClient.retrieveFileStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins, strencoding));
		String line = reader.readLine();
		reader.close();
		if (ins != null) {
			ins.close();
		}
		// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
		ftpClient.getReply();
		return line;

	}

	/**
	 * 从ftp读取文件
	 * @param fileName	读取指定文件
	 * @return 字节流
	 */
	public InputStream getInputStream(String fileName) {
		InputStream ins = null;
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(fileName);
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
			return ins;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 读取ftp txt文件全部内容
	 * 
	 * @param fileName
	 * @return function:从服务器上读取指定的文件
	 * @throws ParseException
	 * @throws IOException
	 */
	public String readFileALL(String fileName, String strencoding, String filed) throws ParseException {
		InputStream ins = null;
		StringBuilder builder = null;
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, strencoding));
			builder = new StringBuilder(150);
			String line = "";
			String[] fileds = filed.split(",");
			StringBuffer jsonStr = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				String[] lines = line.split("\\|");
				int i = 0;
				StringBuffer jsonbuf = new StringBuffer();
				for (String str : lines) {
					if (str == null || "".equals(str)) {
						continue;
					}
					jsonbuf.append(",\"");
					jsonbuf.append(fileds[i]);
					jsonbuf.append("\"");
					jsonbuf.append(":");
					jsonbuf.append("\"");
					jsonbuf.append(str);
					jsonbuf.append("\"");
					i++;
				}
				String jsonH = jsonbuf.toString();
				jsonH = ",{" + jsonH.substring(1) + "}";
				jsonStr.append(jsonH);
			}
			String strList = jsonStr.toString();
			strList = strList.substring(1);
			reader.close();
			if (ins != null) {
				ins.close();
			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
			return "[" + strList + "]";
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	public List<Map<String, String>> readFileMap(String fileName, String strencoding, String[] fields) throws Exception {
		InputStream ins = null;
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(fileName);
			if (ins == null) {
				return null;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, strencoding));
			String line = "";

			while ((line = reader.readLine()) != null) {
				String[] lines = line.split("@@");
				Map<String, String> map = new HashMap<String, String>();
				int i = 0;
				for (String str : lines) {
					map.put(fields[i], str);
					i++;
				}
				mapList.add(map);
			}
			reader.close();
			if (ins != null) {
				ins.close();
			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
			return mapList;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	/**
	 * @param fileName
	 * function:删除文件
	 */
	public void deleteFile(String fileName) {
		try {
			ftpClient.deleteFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}

	public static boolean uploadFileByApacheByBinary(StringBuffer fileContent, String server, String userName, String userPassword, String path, String fileName) {
		FTPClient ftpClient = new FTPClient();
		try {
			InputStream is = null;
			is = new ByteArrayInputStream(fileContent.toString().getBytes());
			ftpClient.connect(server);
			ftpClient.login(userName, userPassword);
			ftpClient.changeWorkingDirectory(path);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1"), is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 创建并写内容到ftp
	 * 
	 * @param fileContent
	 * @param fileName
	 */
	public void writeFile(String fileContent, String fileName) {

		// // 要写入的文件内容
		// String fileContent = "hello world，你好世界\nhello world";
		// // ftp登录用户名
		// String userName = "hyuser";
		// // ftp登录密码
		// String userPassword = "hyuser";
		// // ftp地址
		// String server = "192.168.20.12";//直接ip地址
		// // 创建的文件
		// String fileName = "ftp.txt";
		// // 指定写入的目录
		// String path = "upload";

		try {
			InputStream is = null;
			// 1.输入流
			is = new ByteArrayInputStream(fileContent.getBytes("GBK"));

			// 5.写操作
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.storeFile(new String(fileName.getBytes("utf-8"), "GBK"), is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
		}
	}

	public void contentToTxt(String filePath, String content) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File f = new File(filePath);
			if (f.exists()) {
			} else {
				f.createNewFile();// 不存在则创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\n";
			}
			input.close();
			s1 += content;
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 读TXT文件内容
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Map<String, String>> readTxtFile(String pathname, String[] fields) throws Exception {
		File file = new File(pathname);
		InputStreamReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		try {
			fileReader = new InputStreamReader(new FileInputStream(file), "GBK");
			bufferedReader = new BufferedReader(fileReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					String[] lines = read.split("@@");
					Map<String, String> map = new HashMap<String, String>();
					int i = 0;
					for (String str : lines) {
						map.put(fields[i], str);
						i++;
					}
					mapList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		return mapList;
	}

	public String readLine(InputStream in, String strencoding) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, strencoding));
		String line = reader.readLine();
		reader.close();
		if (in != null) {
			in.close();
		}
		return line;
	}

}
