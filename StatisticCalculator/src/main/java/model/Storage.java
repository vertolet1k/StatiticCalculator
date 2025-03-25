/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.*;

/**
 *
 * @author vika
 */
public class Storage {
    
    ArrayList<double[]> data = new ArrayList();
    private ActionWithExcel awe = new ActionWithExcel();
    private Calculation calculator = new Calculation();
    private ArrayList<Double> result = new ArrayList();
    private ArrayList<double[]> resultOfConInterval = new ArrayList();
    private ArrayList<Double> resultOfCorelation = new ArrayList();
    private short flag = 0;
    private String[] variables;
    private String[] sheets;
    
    
    public short dataStorage(File file, int index) {
        data.clear();
        data = awe.loadData(file, data, index);
        variables = awe.getVariables();
        sheets = awe.getSheets();
        if (!data.isEmpty()){
            flag = 1;
        }
        return flag;
    }
    
    public String[] getVariables() {
        return variables;
    }
    
    public short makeCalculation() {
        this.result.clear();
        resultOfConInterval.clear();
        resultOfCorelation.clear();
        if (flag == 1){
            for (int i = 0; i < data.size(); i++){
                result.add(calculator.geomMean(data.get(i)));
                result.add(calculator.arithMean(data.get(i)));
                result.add(calculator.std(data.get(i)));
                result.add(calculator.range(data.get(i)));
                result.add((double) calculator.volume(data.get(i)));
                result.add(calculator.variation(data.get(i)));
                result.add(calculator.var(data.get(i)));
                result.add(calculator.min(data.get(i)));
                result.add(calculator.max(data.get(i)));

                resultOfConInterval.add(calculator.conInterval(data.get(i)));

                for (int j = 0; j < data.size(); j++){
                    resultOfCorelation.add(calculator.cov(data.get(i), data.get(j)));
                }
            }
        }
        return flag;
    }
    
    public ArrayList<Double> getResult() {
        return result;
    }

    public ArrayList<double[]> getResultOfConInterval() {
        return resultOfConInterval;
    }

    public ArrayList<Double> getResultOfCorelation() {
        return resultOfCorelation;
    }
    
    public String[] getSheets() {
        return sheets;
    }
}
