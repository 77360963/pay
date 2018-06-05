package com.yunpan.base.file;

import com.yunpan.base.file.excel.ExcelReader;
import com.yunpan.base.file.zip.ZipReader;

public class FileConvertFactory {

    public static Reader getExcelReader() {
        return new ExcelReader();
    }

    public static Reader getZipReader(String destPath) {
        return new ZipReader(destPath);
    }

}
