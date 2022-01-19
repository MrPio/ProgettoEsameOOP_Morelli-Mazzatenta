package com.univpm.po.NutritionStats.utility;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.statistic.Correlation;
import com.univpm.po.NutritionStats.service.statistic.Mean;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviation;
import com.univpm.po.NutritionStats.service.statistic.Statistic;

import java.util.ArrayList;

/**
 * <b>{@code Mathematics} is a pretty Mathematics-based class which purpose is to follow the theory of statistics and
 * to calculate some estimators based on a provided random sample.</b>
 *
 * <p><i>Note that the statistics are calculated based on their definition, the most of which recursively calls the sample mean.</i>
 *
 * <p>This class is instantiated inside the underclasses of {@link Statistic}.
 *
 * @see Mean#calculateStatistic(Diary)
 * @see StandardDeviation#calculateStatistic(Diary)
 * @see Correlation#calculateStatistic(Diary)
 */
public class Mathematics {
    /**
     * the first sample.
     */
    private final ArrayList<Float> sampleX;
    /**
     * the second sample.
     */
    private final ArrayList<Float> sampleY;

    /**
     * The first constructor. It is used when only one random sample is provided.
     *
     * @param sampleX the first sample.
     */
    public Mathematics(ArrayList<Float> sampleX) {
        this.sampleX = sampleX;
        this.sampleY = new ArrayList<>();
    }

    /**
     * The second constructor. It is used when two random sample are provided.
     *
     * <p><i>Correlation (thus Covariance) needs two samples</i>
     *
     * @param sampleX the first sample.
     * @param sampleY the second sample.
     */
    public Mathematics(ArrayList<Float> sampleX, ArrayList<Float> sampleY) {
        this.sampleX = sampleX;
        this.sampleY = sampleY;
    }

    /**
     * @return <p><strong>∑xi = x1+x2+...+xn</strong>
     */
    public float calculateSum(boolean... useSampleY) {
        var sample = useSampleY.length == 1 && useSampleY[0] ? sampleY : sampleX;
        float sum = 0;
        for (var el : sample)
            sum += el;
        return sum;
    }

    /**
     * @return <p><strong>E[X] = ∑xi /n</strong>
     */
    public float calculateSampleMean(boolean... useSampleY) {
        var sample = useSampleY.length == 1 && useSampleY[0] ? sampleY : sampleX;
        float sum = 0;
        for (var el : sample)
            sum += el;
        return sum / sample.size();
    }

    /**
     * @return <p><strong>Var(X) = ∑(xi-xn)² / (n-1)</strong>
     */
    public float calculateSampleVariance(boolean... useSampleY) {
        var sample = useSampleY.length == 1 && useSampleY[0] ? sampleY : sampleX;
        float sum = 0;
        for (var el : sample)
            sum += Math.pow(el - calculateSampleMean(sample.equals(sampleY)), 2);
        return sum / (sample.size() - 1);
    }

    /**
     * @return <p><strong>Dev(X) = √(Var(X))</strong>
     */
    public float calculateSampleStandardDeviation(boolean... useSampleY) {
        return (float) Math.sqrt(calculateSampleVariance(useSampleY));
    }

    /**
     * @return <p><strong>Cov(X,Y) = ∑(xi*yi) /(n-1) - ∑xi ∑yi /(n(n-1))</strong>
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
     * @return <p><strong> ρ(X,Y) = Cov(X,Y) / √(Var(X)*Var(Y))</strong>
     */
    public float calculateCorrelation() {
        return calculateCovariance() / (float) Math.sqrt(calculateSampleVariance(false) * calculateSampleVariance(true));
    }
}