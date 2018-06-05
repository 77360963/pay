package com.yunpan.base.file.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunpan.base.file.FileConvertCallBack;
import com.yunpan.base.file.Writer;

public class ZipWriter implements Writer {
    protected Logger            logger   = LoggerFactory.getLogger(ZipWriter.class);
    private static final String encoding = "UTF-8";
    private File[]              files;

    public ZipWriter(File[] files) {
        super();
        this.files = files;
    }

    @Override
    public void write(OutputStream os) {
        ZipArchiveOutputStream out = new ZipArchiveOutputStream(os);
        out.setUseZip64(Zip64Mode.AsNeeded);
        try {
            out.setEncoding(encoding);
            if (files != null) {
                for (File f : files) {
                    writeFileTOZipArchive(f.getName(), f, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private void writeFileTOZipArchive(String basePath, File file, ZipArchiveOutputStream out) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                String filePath = basePath + File.separator + f.getName();
                writeFileTOZipArchive(filePath, f, out);
            }
            basePath = basePath + File.separator + file.getName();
        } else {
            logger.info("==========>压缩的文件:" + basePath);
            ZipArchiveEntry entry = new ZipArchiveEntry(file, basePath);
            FileInputStream fis = null;
            try {
                out.putArchiveEntry(entry);
                fis = new FileInputStream(file);
                IOUtils.copy(fis, out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.closeArchiveEntry();
                } catch (IOException e) {
                    logger.error("", e);
                }
                IOUtils.closeQuietly(fis);
            }
        }

    }

    @Override
    public void write(OutputStream os, FileConvertCallBack cb) {
    }
    
	/**
	 * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
	 * @param sourceFilePath:待压缩的文件路径
	 * @param zipFilePath:压缩后存放路径
	 * @param fileName:压缩后文件的名称
	 * @param override:压缩后文件已存在是否覆盖(true:覆盖;false:不覆盖)
	 * @return
	 */ 
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName, boolean override) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		if (sourceFile.exists() == false) {
			System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
		} else {
			try {
				boolean repeat=true;
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				if (zipFile.exists()) {
					System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
					if(override){
						zipFile.delete();//如果存在则覆盖
						repeat = true;
					}else{
						repeat = false;
					}
				}
				if(repeat) {
					File[] sourceFiles = sourceFile.listFiles();
					if (null == sourceFiles || sourceFiles.length < 1) {
						System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
					} else {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024 * 10];
						for (int i = 0; i < sourceFiles.length; i++) {
							String file_name = sourceFiles[i].getName();
							if(file_name.indexOf(fileName)!=-1 && !file_name.endsWith(".zip")){
								// 创建ZIP实体，并添加进压缩包
								ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
								zos.putNextEntry(zipEntry);
								// 读取待压缩的文件并写进压缩包里
								fis = new FileInputStream(sourceFiles[i]);
								bis = new BufferedInputStream(fis, 1024 * 10);
								int read = 0;
								while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
									zos.write(bufs, 0, read);
								}
							}
						}
						flag = true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();
					if (null != fos)
						fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return flag;
	}
	
	/**
	 * 解压文件到指定目录 解压后的文件名，和之前一致
	 * @param zipFile 待解压的zip文件
	 * @param descDir 指定目录
	 */ 
	//@SuppressWarnings("rawtypes")
	public static void unZipFiles(String zipPath, String descDir) throws IOException {
		File zipFile = new File(zipPath);
		ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));// 解决中文文件夹乱码
		String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));
		File pathFile = new File(descDir + name);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + name + "/" + zipEntryName).replaceAll("\\*", "/");

			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			// System.out.println(outPath);

			FileOutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
		return;
	}

}
