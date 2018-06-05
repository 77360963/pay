package com.yunpan.base.file.excel;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yunpan.base.file.Reader;

public class ExcelReader implements Reader<Map<String, List<String[]>>> {
    private int minCols;

    @Override
    public Map<String, List<String[]>> read(InputStream is) {
        try {
            ExcelParse reader = new ExcelParse(is, minCols == 0 ? 40 : minCols);
            reader.process();
            return reader.result();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, List<String[]>>();
    }

}
