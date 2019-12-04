package main.util;







import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

import static org.apache.xmlbeans.impl.piccolo.xml.Piccolo.STRING;

public class ExcelUtil {
    public static <T> List<T> parseFromExcel(String path,Class<T> aim) {
        return parseFromExcel(path,0,aim);
    }
    public static <T> List<T>  parseFromExcel(String path,int firstIndex,Class<T> aim) {
        List<T> result=new ArrayList<T>();
        try {
            FileInputStream fis=new FileInputStream(path);
            XSSFWorkbook workbook= new XSSFWorkbook(fis);
            XSSFSheet sheet=workbook.getSheetAt(0);
            int lastRow=sheet.getLastRowNum();
            for (int i = 2; i <=lastRow ; i++) {
                XSSFRow row = sheet.getRow(i);
                T parseObject = aim.newInstance();
                Field[] fields = aim.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    //第j列
                    XSSFCell cell = row.getCell(j);
                    if (cell == null)
                        continue;
                    //很重要的一行代码,如果不加,像12345这样的数字是不会给你转成String的,只会给你转成double,而且会导致cell.getStringCellValue()报错
                    String cellContent="";
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            cellContent=cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell)) {
                                cellContent= DateFormat.getDateInstance().format(cell.getDateCellValue())+"";
                            }
                            else  {
                            cellContent=cell.getNumericCellValue()+"";
                            }
                            break;
                         default:
                             cellContent=cell.getStringCellValue();
                             break;

                    }
                    //cellContent = "".equals(cellContent) ? "0" : cellContent;
                    if (type.equals(String.class)) {
                        field.set(parseObject, cellContent);
                    } else if (type.equals(char.class) || type.equals(Character.class)) {
                        field.set(parseObject, cellContent.charAt(0));
                    } else if (type.equals(int.class) || type.equals(Integer.class)) {
                        field.set(parseObject, Integer.parseInt(cellContent));
                    } else if (type.equals(long.class) || type.equals(Long.class)) {
                        field.set(parseObject, Long.parseLong(cellContent));
                    } else if (type.equals(float.class) || type.equals(Float.class)) {
                        field.set(parseObject, Float.parseFloat(cellContent));
                    } else if (type.equals(double.class) || type.equals(Double.class)) {
                        field.set(parseObject, Double.parseDouble(cellContent));
                    } else if (type.equals(short.class) || type.equals(Short.class)) {
                        field.set(parseObject, Short.parseShort(cellContent));
                    } else if (type.equals(byte.class) || type.equals(Byte.class)) {
                        field.set(parseObject, Byte.parseByte(cellContent));
                    } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
                        field.set(parseObject, Boolean.parseBoolean(cellContent));
                    }


                }
                result.add(parseObject);
            }
            fis.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
