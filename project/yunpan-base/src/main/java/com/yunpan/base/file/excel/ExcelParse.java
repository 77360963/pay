package com.yunpan.base.file.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ExcelParse {

    private Logger                      logger               = LoggerFactory
                                                                 .getLogger(ExcelParse.class);

    //最终返回结果
    private Map<String, List<String[]>> resultParseSheetRows = new LinkedHashMap<String, List<String[]>>();

    /** 
     * Uses the XSSF Event SAX helpers to do most of the work 
     * of parsing the Sheet XML, and outputs the contents 
     * as a (basic) CSV. 
     */
    private class SheetToCSV implements SheetContentsHandler {
        private boolean        firstCellOfRow = false;
        private int            currentRow     = -1;
        private int            currentCol     = -1;
        private List<String[]> sheetRows      = new ArrayList<String[]>();
        private String[]       row;

        private void outputMissingRows(int number) {
            /*     for (int i = 0; i < number; i++) {  
                     for (int j = 0; j < minColumns; j++) {  
                         output.append(',');  
                     }  
                     output.append('\n');  
                 }  */
        }

        @Override
        public void startRow(int rowNum) {
            // If there were gaps, output the missing rows  
            outputMissingRows(rowNum - currentRow - 1);
            // Prepare for this row  
            firstCellOfRow = true;
            currentRow = rowNum;
            currentCol = -1;
            row = new String[minColumns];
        }

        @Override
        public void endRow(int rowNum) {
            // Ensure the minimum number of columns  
            /*   for (int i = currentCol; i < minColumns; i++) {  
                   output.append(',');  
               }  
               output.append('\n');  */
            sheetRows.add(row);

        }

        @Override
        public void cell(String cellReference, String formattedValue, XSSFComment comment) {

            if (firstCellOfRow) {
                firstCellOfRow = false;
            } else {
                // output.append(',');  
            }

            // gracefully handle missing CellRef here in a similar way as XSSFCell does  
            if (cellReference == null) {
                cellReference = new CellAddress(currentRow, currentCol).formatAsString();
            }

            // Did we miss any cells?  
            CellReference cf = new CellReference(cellReference);
           // System.out.println("================>[" + cf.getRow() + "," + cf.getCol() + "]");
            int thisCol = cf.getCol();
            /*  int missedCols = thisCol - currentCol - 1;  
              for (int i = 0; i < missedCols; i++) {  
                  output.append(',');  
              }  */
            currentCol = thisCol;
            row[currentCol] = formattedValue;
            // Number or string?  
            /*  try {  
                  Double.parseDouble(formattedValue);  
                  output.append(formattedValue);  
              } catch (NumberFormatException e) {  
                  output.append('"');  
                  output.append(formattedValue);  
                  output.append('"');  
              }  */
        }

        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {
            // Skip, no headers or footers in CSV  
        }

        public List<String[]> getSheetRows() {
            return sheetRows;
        }

    }

    ///////////////////////////////////////  

    private final OPCPackage xlsxPackage;

    /** 
     * Number of columns to read starting with leftmost 
     */
    private final int        minColumns;

    /** 
     * Destination for data 
     */
    //private final PrintStream output;  
    /** 
            * Creates a new XLSX -> CSV converter 
            * 
            * @param pkg        The XLSX package to process 
            * @param output     The PrintStream to output the CSV to 
            * @param minColumns The minimum number of columns to output, or -1 for no minimum 
            */
    public ExcelParse(String filePath,  int minColumns) {
        OPCPackage p = null;
        try {
            p = OPCPackage.open(filePath, PackageAccess.READ);
        } catch (InvalidOperationException e) {
            logger.error(e.getMessage(), e);
        } catch (InvalidFormatException e) {
            logger.error("", e);
        }
        this.xlsxPackage = p;
        //  this.output = output;
        this.minColumns = minColumns;
    }

    public ExcelParse(InputStream is,  int minColumns) {
        OPCPackage p = null;
        try {
            p = OPCPackage.open(is);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        this.xlsxPackage = p;
        //  this.output = output;
        this.minColumns = minColumns;
    }

    public ExcelParse(OPCPackage pkg, PrintStream output, int minColumns) {
        this.xlsxPackage = pkg;
        //  this.output = output;
        this.minColumns = minColumns;
    }

    /** 
     * Parses and shows the content of one sheet 
     * using the specified styles and shared-strings tables. 
     * 
     * @param styles 
     * @param strings 
     * @param sheetInputStream 
     */
    public List<String[]> processSheet(StylesTable styles, ReadOnlySharedStringsTable strings,
                                       SheetContentsHandler sheetHandler,
                                       InputStream sheetInputStream) throws IOException,
                                                                    ParserConfigurationException,
                                                                    SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(styles, null, strings, sheetHandler,
                formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
            return ((SheetToCSV) sheetHandler).getSheetRows();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    /** 
     * Initiates the processing of the XLS workbook file to CSV. 
     * 
     * @throws IOException 
     * @throws OpenXML4JException 
     * @throws ParserConfigurationException 
     * @throws SAXException 
     */
    public void process() throws IOException, OpenXML4JException, ParserConfigurationException,
                         SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            String sheetName = iter.getSheetName();
            //  this.output.println();
            //  this.output.println(sheetName + " [index=" + index + "]:");
            List<String[]> rowList = processSheet(styles, strings, new SheetToCSV(), stream);
            resultParseSheetRows.put(sheetName, rowList);
            stream.close();
            ++index;
        }
        xlsxPackage.close();
    }

    public Map<String, List<String[]>> result() {

        return resultParseSheetRows;

    }

    public static void main(String[] args) throws Exception {
        /*  if (args.length < 1) { 
              System.err.println("Use:"); 
              System.err.println("  XLSX2CSV <xlsx file> [min columns]"); 
              return; 
          }*/

       /* File xlsxFile = new File("D:\\tmp\\a.xlsx");
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return;
        }
*/
        int minColumns = 10;

        ExcelParse xlsx2csv = new ExcelParse(new FileInputStream("D:\\tmp\\a.xlsx"), minColumns);
        xlsx2csv.process();
        for (Map.Entry<String, List<String[]>> en : xlsx2csv.result().entrySet()) {
            System.out.println("sheet:" + en.getKey());
            for (String[] str : en.getValue()) {
                System.out.println(Arrays.toString(str));
            }
        }
    }
}