package com.univpm.po.NutritionStats.utility;

import java.util.ArrayList;

public class Mathematics {
    private final ArrayList<Float> sampleX;
    private final ArrayList<Float> sampleY;

    public Mathematics(ArrayList<Float> sampleX) {
        this.sampleX = sampleX;
        this.sampleY=new ArrayList<>();
    }

    public Mathematics(ArrayList<Float> sampleX, ArrayList<Float> sampleY) {
        this.sampleX = sampleX;
        this.sampleY = sampleY;
    }

    /**
     * <p><strong>∑xi = x1+x2+...+xn</strong>
     *
     * @return
     */
    public float calculateSum(boolean... useSampleY) {
        var sample = useSampleY.length == 1 && useSampleY[0] ? sampleY : sampleX;
        float sum = 0;
        for (var el : sample)
            sum += el;
        return sum;
    }
    /**
     * <p><strong>E[X] = ∑xi /n</strong>
     *
     * @return
     */
    public float calculateSampleMean(boolean... useSampleY) {
        var sample = useSampleY.length == 1 && useSampleY[0] ? sampleY : sampleX;
        float sum = 0;
        for (var el : sample)
            sum += el;
        return sum / sample.size();
    }

    /**
     * <p><strong>Var(X) = ∑(xi-xn)² / (n-1)</strong>
     *
     * @return
     */
    public float calculateSampleVariance(boolean... useSampleY) {
        var sample = useSampleY.length == 1 && useSampleY[0] ? sampleY : sampleX;
        float sum = 0;
        for (var el : sample)
            sum += Math.pow(el - calculateSampleMean(sample.equals(sampleY)), 2);
        return sum / (sample.size() - 1);
    }

    /**
     * <p><strong>Dev(X) = √(Var(X))</strong>
     *
     * @return
     */
    public float calculateSampleStandardDeviation(boolean... useSampleY) {
        return (float)Math.sqrt(calculateSampleVariance(useSampleY));
    }

    /**
     * <p><strong>Cov(X,Y) = ∑(xi*yi) /(n-1) - ∑xi ∑yi /(n(n-1))</strong>
     *
     * @return
     */
    public float calculateCovariance() {
        float sumXY = 0, sumX = 0, sumY = 0;
        int size = Math.min(sampleX.size(), sampleY.size());
        for (int i = 0; i < size; i++) {
            sumXY += sampleX.get(i) * sampleY.get(i);
            sumX += sampleX.get(i);
            sumY += sampleY.get(i);
        }
        return sumXY / (size - 1) - sumX * sumY / (size * (size - 1));
    }

    /**
     * <p><strong> ρ(X,Y) = Cov(X,Y) / √(Var(X)*Var(Y))</strong>
     *
     * @return
     */
    public float calculateCorrelation() {
        return calculateCovariance() / (float) Math.sqrt(calculateSampleVariance(false) * calculateSampleVariance(true));
    }
}