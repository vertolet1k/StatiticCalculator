/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author vika
 */
public class Calculation {
    
    private DescriptiveStatistics stats;
    
    public Double geomMean(double[] x){ 
        stats = new DescriptiveStatistics(x);
        return stats.getGeometricMean();
    }
    
    public Double arithMean(double[] x){ 
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
        stats = new DescriptiveStatistics(x);
        double leftLimit = arithMean(x) - 1.96 * (std(x) / volume(x));
        double rightLimit = arithMean(x) + 1.96 * (std(x) / volume(x));
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
