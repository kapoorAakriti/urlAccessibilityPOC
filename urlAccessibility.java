import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class urlAccessibility {

    public WebDriver webDriver;
    ArrayList<String> arrlist = new ArrayList<String>();
    public HashMap<String, String> Geeks = new HashMap<String, String>();


    public void readXLSXFile(String filePathName) throws IOException, InterruptedException {
        FileInputStream fis = null;
        XSSFWorkbook wb = null;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aakkapoo\\Desktop\\chromedriver.exe");
        webDriver = new ChromeDriver();

        try {
            File src = new File(filePathName);
            //File src= new File("C://Users//aakkapoo//Desktop//URLAccessibilitysheet.xlsx");
            fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responsiveURL responseCheck = new responsiveURL();

       //input Section
        XSSFSheet sh1 = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;


        Iterator rowsIterator = sh1.rowIterator();
        while (rowsIterator.hasNext()) {
            row = (XSSFRow) rowsIterator.next();
            Iterator cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = (XSSFCell) cellIterator.next();
                if (row.getRowNum() > 0) {
                    if (cell.getColumnIndex() == 1) {
                        String value = cell.getStringCellValue();
                        //System.out.println(value);
                        // arrlist.add(cell.getStringCellValue());
                        try {
                            webDriver.navigate().to(value);

                            Thread.sleep(9000);
                            String YesorNo = responseCheck.responcive(value);
                            Geeks.put(value, YesorNo);


                        } catch (Exception e) {
                            Geeks.put(value, "No");
                            //System.out.println(e.getMessage());
                        }

                    }
                }
            }
        }

        //output section
        XSSFRow rowput;
        XSSFCell cellput;
        Iterator rowsIteratorput = sh1.rowIterator();
        while (rowsIteratorput.hasNext()) {
            rowput = (XSSFRow) rowsIteratorput.next();
            Iterator cellIteratorput = rowput.cellIterator();
            while (cellIteratorput.hasNext()) {
                cellput = (XSSFCell) cellIteratorput.next();
                if (rowput.getRowNum() > 0) {
                    if (cellput.getColumnIndex() == 1) {
                        XSSFCell newcell = rowput.createCell(2);
                        if (Geeks.containsKey(cellput.getStringCellValue())) {
                            newcell.setCellValue(Geeks.get(cellput.getStringCellValue()));
                            //System.out.print(Geeks.get(cellput.getStringCellValue()));
                        }

                    }
                }

            }
        }

        fis.close();
        FileOutputStream outputStream = null;
        try {

            File src = new File(filePathName);
            outputStream = new FileOutputStream(src);
            wb.write(outputStream);
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


