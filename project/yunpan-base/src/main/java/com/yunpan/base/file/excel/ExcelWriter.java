package com.yunpan.base.file.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.yunpan.base.file.FileConvertCallBack;
import com.yunpan.base.file.Writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelWriter implements Writer {

    private Map<String, Object> param;
    private String[]            title;
    private List<String[]>      records;

    public ExcelWriter(String[] title, List<String[]> records) {
        super();
        this.title = title;
        this.records = records;
    }

    public ExcelWriter(Map<String, Object> param) {
        super();
        this.param = param;
    }

    public void write(OutputStream os, FileConvertCallBack cb) {

        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        try {
            cb.action(param, wb);
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.dispose();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void write(OutputStream os) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        try {
            // keep 100 rows in memory, exceeding rows will be flushed to disk
            int rowOffset = 2;
            int colOffset = 2;
            Sheet sh = wb.createSheet();
            for (int rownum = 0; rownum < records.size() + 1; rownum++) {
                Row row = sh.createRow(rownum + rowOffset);
                for (int cellnum = 0; cellnum < title.length; cellnum++) {
                    Cell cell = row.createCell(cellnum + colOffset);
                    // String address = new CellReference(cell).formatAsString();
                    if (rownum == 0) {
                        cell.setCellValue(title[cellnum]);
                    } else {
                        cell.setCellValue(records.get(rownum - 1)[cellnum]);
                    }

                }
            }
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.dispose();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
