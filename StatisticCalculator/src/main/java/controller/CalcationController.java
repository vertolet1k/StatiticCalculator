/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

/**
 *
 * @author vika
 */
public interface CalcationController {
    double geomMean(double[] x);
    double arithMean(double[] x);
    double std(double[] x);
    double range(double[] x);
    double cov(double[] x, double[] y);
    long volume(double[] x);
    double variation(double[] x);
    double[] conInterval(double[] x);
    double var(double[] x);
    double min(double[] x);
    double max(double[] x);
}
