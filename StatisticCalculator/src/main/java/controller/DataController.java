/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import java.util.*;

/**
 *
 * @author vika
 */
public interface DataController {
    ArrayList<double[]> loadData();
    void exportData(ArrayList<Double> result, ArrayList<double[]> resultOfConInterval, ArrayList<Double> resultOfCorelation);
}
