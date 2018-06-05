package com.yunpan.base.file.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunpan.base.file.FileConvertException;
import com.yunpan.base.file.Reader;

public class ZipReader implements Reader<List<String>> {
    protected Logger            logger   = LoggerFactory.getLogger(ZipReader.class);
    private static final String encoding = "UTF-8";
    private String              destPathDir;

    public ZipReader(String destPathDir) {
        super();
        this.destPathDir = destPathDir;
    }

    @Override
    public List<String> read(InputStream ips) {
        List<String> result = new ArrayList<String>();
        ZipArchiveInputStream zais = null;
        if (StringUtils.isEmpty(destPathDir)) {
            throw new FileConvertException("解压存放路径为空");
        }
        ZipArchiveInputStream is = null;
        try {
            is = new ZipArchiveInputStream(ips);
            ZipArchiveEntry entry = null;
            while ((entry = is.getNextZipEntry()) != null) {
                if (entry.isDirectory()) {
                    String dirPath = destPathDir + File.separator + entry.getName();
                    File directory = new File(dirPath);
                    logger.info("============>创建目录" + directory);
                    directory.mkdirs();
                } else {
                    OutputStream os = null;
                    try {
                        String dirPath = FilenameUtils.getPathNoEndSeparator(entry.getName());
                        File temp = new File(destPathDir + File.separator + dirPath);
                        if (!temp.exists()) {
                            logger.info("============>创建目录" + dirPath);
                            temp.mkdirs();
                        }
                        String destFile = destPathDir + File.separator + entry.getName();
                        logger.info("============>解压文件" + destFile);
                        os = new FileOutputStream(new File(destFile));
                        IOUtils.copy(is, os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
        return result;
    }

}
