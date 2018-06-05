package com.yunpan.base.file;

import java.io.OutputStream;

public interface Writer {

    public void write(OutputStream os);
    
    public void write(OutputStream os,FileConvertCallBack cb);
}
