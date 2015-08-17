package zTest;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class POCExcelToJson {

    public static void main(String[] args) {
        POCExcelToJson poc = new POCExcelToJson();
        poc.convert();
    }

    private void convert() {
        Workbook workbook = null;
        WorkbookSettings ws = new WorkbookSettings();
        ws.setEncoding("CP1250");

        File file = new File("excelTestsheet.xls");
        // File file = new File("excelTestsheet.xlsx");
        // The xls format (< Excel 2007) is comprised of binary BIFF data in an OLE container. The xlsx format (>= Excel
        // 2007) is comprised of XML files in a zip container.
        // The Java Excel API only deals with the first format so it throws an exception when it doesn't encounter an OLE
        // container.
        // Apache POI handles both file types in Java: poi.apache.org/spreadsheet/index.html

        try {
            workbook = Workbook.getWorkbook(file, ws);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet[] sheets = workbook.getSheets();

        JSONArray array = null;
        // TODO _ pgl test for empty sheet?
        // TODO _ pgl lat long naar double. empty cells. wijzigingsdatum toevoegen.
        for (Sheet sheet : sheets) {
            Cell[] headers = sheet.getRow(0);
            array = new JSONArray();
            for (int i = 1; i < sheet.getRows(); i++) {
                JSONObject object = new JSONObject();
                Cell[] cells = sheet.getRow(i);
                for (int j = 0; j < headers.length; j++) {
                    Object content = j < cells.length ? cells[j].getContents() : JSONObject.NULL;
                    String header = headers[j].getContents();
                    if (header.contains(".")) {
                        String[] split = header.split("\\.");
                        JSONObject nested = getNewOrNestedObject(object, split[0]);
                        nested.put(split[1], content);
                    } else {
                        object.put(header, content);
                        System.out.println("content = " + content);
                    }
                }
                array.put(object);
            }
        }

        workbook.close();
        System.out.println("output: " + array.toString());
    }

    /**
     * Find nested object by key, else create and add it.
     * 
     * @param object
     * @param key
     * @return
     */
    private JSONObject getNewOrNestedObject(JSONObject object, String key) {
        JSONObject nested = new JSONObject();
        try {
            nested = (JSONObject) object.get(key);
        } catch (JSONException e) {
            // nested not found continue.
            object.put(key, nested);
        }
        return nested;
    }
}
