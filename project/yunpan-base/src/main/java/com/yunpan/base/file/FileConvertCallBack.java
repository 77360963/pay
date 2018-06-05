package com.yunpan.base.file;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface FileConvertCallBack<T> {

    public <K> void action(Map<String, Object> param, K t);

}
