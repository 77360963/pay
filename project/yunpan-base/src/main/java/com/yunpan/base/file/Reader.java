package com.yunpan.base.file;

import java.io.InputStream;

public interface Reader<T> {

    public T read(InputStream is);
}
