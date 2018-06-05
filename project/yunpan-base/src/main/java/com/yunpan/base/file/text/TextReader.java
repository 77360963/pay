package com.yunpan.base.file.text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.yunpan.base.file.Reader;

public class TextReader implements Reader<List<String>> {

    @Override
    public List<String> read(InputStream is) {
        try {
            return IOUtils.readLines(is, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

}
