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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author vika
 */
public class ActionWithData implements DataController{
    
    private ArrayList<double[]> data = new ArrayList<>();
    private String[] variables;
    File file;

    public ArrayList<double[]> loadData(){
        
        JFileChooser fileopen = new JFileChooser();
        int l = fileopen.showDialog(null, "Открыть файл");                
        if (l == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();
        }
        
        try (FileInputStream fis = new FileInputStream(file)) {
            
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
            variables = new String[sheet.getRow(0).getLastCellNum()];
            for (int o = 0; o <= sheet.getRow(0).getLastCellNum() - 1; o++){
                double[] mas = new double[sheet.getLastRowNum()];
                data.add(mas);
            }
            
            int p = 0;
            for (Row row : sheet) {
                int k = 0;
                int h = 0;
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC){    
                        data.get(k)[p - 1] = cell.getNumericCellValue();
                        k++;
                    } else {
                        if (cell.getCellType() == CellType.STRING){
                            variables[h] = cell.getStringCellValue();
                            h++;}
                        else {
                            cell.removeFormula();
                            data.get(k)[p - 1] = (double) cell.getNumericCellValue();
                            k++;
                        }
                    }
                } p++;
            }
        } catch (IOException ex) {
            System.out.println("IOExeption");
        }
        
        return data;
    }
    
    public void exportData(ArrayList<Double> result, ArrayList<double[]> resultOfConInterval, ArrayList<Double> resultOfCorelation) {
         
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");
        Row row = sheet.createRow(0);
        
        statistics(row, result, sheet);
        
        CI(row, resultOfConInterval, sheet);
        
        corelation(row, resultOfCorelation, sheet);
        
        variables = null;
        data.clear();
        
        
        try {
            JFileChooser fileopen = new JFileChooser();
            int l = fileopen.showDialog(null, "Сохранить файл");
            if (l == JFileChooser.APPROVE_OPTION) {
                file = fileopen.getSelectedFile();
                try {
                    FileOutputStream out = new FileOutputStream(file); 
                    workbook.write(out);
                    out.close();
                } catch (IOException ex) {
                    System.out.println("ОШИБКА");
                }
        }}catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
     
     public void statistics(Row row, ArrayList<Double> result, Sheet sheet) {
        String[] statistics = {"", "среднее геометрическое", "среднее арифметическое", "стандартное отклонение", 
            "размах", " количество элементов в выборке", "коэффициент вариации", "дисперсия", "максимум", "минимум"};
        for (int i = 0; i < statistics.length; i++){
            row.createCell(i).setCellValue(statistics[i]);
        }
        int k = 0;
        row = sheet.createRow(1);
        row.createCell(0).setCellValue(variables[0]);
         System.out.println(result.size());
        for (int i = 1; i < result.size() + 1; i++) {
            if (((i - 1) % (statistics.length - 1) == 0) && ((i - 1) != 0)) {
                row = sheet.createRow(k + 2);
                row.createCell(0).setCellValue(variables[k + 1]);
                k++;
            }
            row.createCell(i - k * (statistics.length - 1)).setCellValue(result.get(i - 1));
        }
     }
     
     public void CI(Row row, ArrayList<double[]> resultOfConInterval, Sheet sheet) {
        row = sheet.createRow(sheet.getLastRowNum() + 2);
        row.createCell(0).setCellValue("доверительный интервал");
        for (int i = 0; i < resultOfConInterval.size(); i++){
            row.createCell(i + 1).setCellValue(resultOfConInterval.get(i)[0] + " - " + resultOfConInterval.get(i)[1]);
        }
     }
    
     public void corelation(Row row, ArrayList<Double> resultOfCorelation, Sheet sheet) {
        row = sheet.createRow(sheet.getLastRowNum() + 2);
        row.createCell(0).setCellValue("ковариация");
        for (int i = 1; i < variables.length + 1; i++){
            row.createCell(i).setCellValue(variables[i - 1]);
        }
        int t = 0;
        row = sheet.createRow(sheet.getLastRowNum() + 1);
        row.createCell(0).setCellValue(variables[0]);
        for (int i = 1; i < resultOfCorelation.size() + 1; i++){
            if (((i - 1) % (variables.length) == 0) && ((i - 1) != 0)) {
                row = sheet.createRow(sheet.getLastRowNum() + 1);
                row.createCell(0).setCellValue(variables[t + 1]);
                t++;
            }   
            row.createCell(i - t * (variables.length)).setCellValue(resultOfCorelation.get(i - 1));
        }
     }
     
}
