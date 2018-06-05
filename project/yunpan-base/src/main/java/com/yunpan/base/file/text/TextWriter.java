package com.yunpan.base.file.text;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.yunpan.base.file.FileConvertCallBack;
import com.yunpan.base.file.FileConvertException;
import com.yunpan.base.file.Writer;

public class TextWriter implements Writer {
    private List<String> recordList;

    public TextWriter(List<String> recordList) {
        super();
        this.recordList = recordList;
    }

    @Override
    public void write(OutputStream os) {
        try {
            IOUtils.writeLines(recordList, null, os, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileConvertException("textConvert write outputstream failed");
        }
    }

    @Override
    public void write(OutputStream os, FileConvertCallBack cb) {
    }

}
