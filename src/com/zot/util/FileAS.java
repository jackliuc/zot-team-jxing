package com.zot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileAS 
{
	private static Logger log = Logger.getLogger(FileAS.class);
	
	public static final String OFFICE_EXCEL_XLS_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_XLSX_POSTFIX = "xlsx";
	public static final String FIELD_SEP = "\t";
	private static final short MD_DATE_FMT = 58;

    public static List<String> readExcel(File file, int index)
    {  
    	List<String> lines = Collections.emptyList();
    	
    	String postfix = getPostfix(file);
    	if (OFFICE_EXCEL_XLS_POSTFIX.equalsIgnoreCase(postfix))
    	{
    		lines = readXls(file, index);
    	}
    	else if (OFFICE_EXCEL_XLSX_POSTFIX.equalsIgnoreCase(postfix))
    	{
    		lines = readXlsx(file, index);
    	}
    	else
    	{
    		log.error("The file is not excel file, file:" + file.getAbsolutePath());
    	}
    	
    	return lines;
    } 
    
    private static List<String> readXls(File file, int index)
    {
    	List<String> lines = new ArrayList<String>();
    	Workbook wb = null;
    	try 
    	{  
            wb = new HSSFWorkbook(new FileInputStream(file));  
            Sheet sheet = wb.getSheetAt(index);     //获得第一个表单  
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
            while (rows.hasNext()) 
            {  
                Row row = rows.next();  //获得行数据  
                String line = getLine(row);
                //System.out.println(line);
                lines.add(line);
            }  
        } 
    	catch (IOException ex) 
    	{  
            ex.printStackTrace();  
        }
    	finally
    	{
    		if (wb != null)
    		{
    			try 
    			{
					wb.close();
				} 
    			catch (IOException e) 
    			{
					e.printStackTrace();
				}
    		}
    	}
    	
    	return lines;
    }
    
    private static List<String> readXlsx(File file, int index)
    {
    	//TODO
    	return Collections.emptyList();
    }
    
    private static String getLine(Row row)
    {
    	StringBuffer buf = new StringBuffer();
    	
    	Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
        while (cells.hasNext()) 
        {  
            Cell cell = cells.next();
            String cellValue = null;
            switch (cell.getCellType()) 
            {   //根据cell中的类型来输出数据  
	            case HSSFCell.CELL_TYPE_NUMERIC: 
	            	if (MD_DATE_FMT == cell.getCellStyle().getDataFormat())//自定义日期格式
	            	{
	            		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            		double value = cell.getNumericCellValue();  
	            	    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
	            	    cellValue = sdf.format(date);  
	            	}
	            	else
	            	{
	            		cellValue = String.valueOf((long)cell.getNumericCellValue());
	            	}
	            	buf.append(cellValue).append(FIELD_SEP);
	                break;  
	            case HSSFCell.CELL_TYPE_STRING:  
	                buf.append(cell.getStringCellValue()).append(FIELD_SEP);
	                break;  
	            case HSSFCell.CELL_TYPE_BOOLEAN:  
	                buf.append(cell.getBooleanCellValue()).append(FIELD_SEP);
	                break;  
	            case HSSFCell.CELL_TYPE_FORMULA:  
	                buf.append(cell.getCellFormula()).append(FIELD_SEP);
	                break;  
	            default: 
	            	buf.append(FIELD_SEP);
	                break;  
            }  
        }
        
        return buf.toString();
    }
    
    private static String getPostfix(File file)
    {
    	//TODO:xls,xlsx
    	return "xls";
    }
    
	public static void main(String[] args) {  
		FileAS.readExcel(new File("D:/壹号车/5月消费明细.xls"), 4);
    } 
}  
