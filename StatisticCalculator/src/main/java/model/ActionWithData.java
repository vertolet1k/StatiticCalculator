/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.DataController;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author vika
 */
public class ActionWithData implements DataController{
    
    ArrayList<double[]> data = new ArrayList<>();
    
//    @Override
    public ArrayList<double[]> loadData(String file){
        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            int p = 0;
            for (Row row : sheet) {
                double[] mas = new double[sheet.getRow(0).getLastCellNum()];
                data.add(mas);
                int k = 0;
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC){
                        data.get(p - 1)[k] = cell.getNumericCellValue();
                        k++;
                    } 
                } p++;
            }
//            for (double[] i: rowData){
//                for (double j: i){
//                    System.out.println(j);
//                }
//                System.out.println("");
//            }
        } catch (IOException ex) {
            System.out.println("IOExeption");
        }
        return data;
    }
    
//    @Override
    public void exportData(ArrayList<double[]> result){
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");
        Row row = sheet.createRow(0);
        try { 
            FileOutputStream out = new FileOutputStream(new File("gfgcontribute.xlsx")); 
            workbook.write(out); 
            out.close(); 
            System.out.println("gfgcontribute.xlsx written successfully on disk."); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
}
