/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.*;
import org.apache.commons.math3.distribution.*;
import org.apache.commons.math3.stat.correlation.*;
import org.apache.commons.math3.stat.descriptive.*;

/**
 *
 * @author vika
 */
public class Calculation implements CalcationController{
    
    private DescriptiveStatistics stats;
    
    public double geomMean(double[] x){
        for (int i = 0; i < x.length; i++){
                    System.out.println(x[i]);
                }
                System.out.println("");
        for (int i = 0; i < x.length; i++){
            x[i]= Math.abs(x[i]);
        }
        
        stats = new DescriptiveStatistics(x);
        return stats.getGeometricMean();
    }
    
    public double arithMean(double[] x){ 
        stats = new DescriptiveStatistics(x);
        return stats.getMean();
    }
    
    public double std(double[] x){ 
        stats = new DescriptiveStatistics(x);
        return stats.getStandardDeviation();
    }
    
    public double range(double[] x){ 
        stats = new DescriptiveStatistics(x);
        return max(x) - min(x); 
    }
    
    public double cov(double[] x, double[] y){ //коэффициент ковариации
        stats = new DescriptiveStatistics(x);
        Covariance covariance = new Covariance();
//        System.out.println(covariance.covariance(x, y));
        return covariance.covariance(x, y);
    }
    
    public long volume(double[] x){
        stats = new DescriptiveStatistics(x);
        return stats.getN();
    }
    
    public double variation(double[] x){ //коэффициент вариации
        stats = new DescriptiveStatistics(x);
        return (std(x) / arithMean(x)) * 100; 
    }
    
    public double[] conInterval(double[] x){ //доверительный интервал 
        double confidenceLevel = 0.95;
        double alpha = 1 - confidenceLevel;
        TDistribution tDistribution = new TDistribution((int) volume(x) - 1);
        double tValue = tDistribution.inverseCumulativeProbability(alpha / 2);
        
        stats = new DescriptiveStatistics(x);
        double leftLimit = arithMean(x) - tValue * (std(x) / Math.sqrt(volume(x)));
        double rightLimit = arithMean(x) + tValue * (std(x) / Math.sqrt(volume(x)));
        double[] CI = {leftLimit, rightLimit};
        return CI;
    }
    
    public double var(double[] x){
        stats = new DescriptiveStatistics(x);
        return stats.getVariance();
    }
    
    public double min(double[] x){
        stats = new DescriptiveStatistics(x);
        return stats.getMin();
    }
    
    public double max(double[] x){
        stats = new DescriptiveStatistics(x);
        return stats.getMax();
    }
}
