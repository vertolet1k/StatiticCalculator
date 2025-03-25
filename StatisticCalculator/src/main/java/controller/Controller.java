/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import model.*;

/**
 *
 * @author vika
 */
public class Controller {
    
    private Storage stor = new Storage();
    private ActionWithExcel awe = new ActionWithExcel();
    private short flag = 1;
    
    public short loadController(File file, int index) {
        return stor.dataStorage(file, index);
    }
    
    public short calculationController() {
        return stor.makeCalculation();
    }
    
    public short exportController(File file) {
        if (stor.getResult().isEmpty()) {
            flag = 0;
        } else {
            awe.exportData(file, stor.getVariables(), stor.getResult(), stor.getResultOfConInterval(), stor.getResultOfCorelation());
        }
        return flag;
    }
    
    public String[] getSheets() {
        return stor.getSheets();
    }
    
}
