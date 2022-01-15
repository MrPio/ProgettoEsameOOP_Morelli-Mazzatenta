package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.utility.Mathematics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MathematicsTest {
    Mathematics math;

    /**
     * Allocating two random samples to use as test. The hardcoded expected values below are calculated with <i>Matlab R2021b</i>.
     */
    @BeforeEach
    void setUp() {
        var sampleX = new ArrayList<>(List.of(17f, 26f, 29f, 29f, 29f, 30f, 26f, 26f, 29f, 26f, 27f, 21f));
        var sampleY = new ArrayList<>(List.of(26f, 36f, 39f, 39f, 39f, 40f, 36f, 36f, 39f, 36f, 37f, 31f));
        math = new Mathematics(sampleX, sampleY);
    }

    /**
     * Testing the <strong>{@link Mathematics#calculateSampleMean(boolean...) mean}</strong> of the samples.
     */
    @Test
    void calculateSampleMean() {
        assert (Math.abs(math.calculateSampleMean(false) - 26.2500f) < 0.001f);
        assert (Math.abs(math.calculateSampleMean(true) - 36.1667f) < 0.001f);
    }

    /**
     * Testing the <strong>{@link Mathematics#calculateSampleVariance(boolean...) variance}</strong> of the samples.
     */
    @Test
    void calculateSampleVariance() {
        assert (Math.abs(math.calculateSampleVariance(false) - 14.3864f) < 0.001f);
        assert (Math.abs(math.calculateSampleVariance(true) - 16.1515f) < 0.001f);
    }

    /**
     * Testing the <strong>{@link Mathematics#calculateCovariance() covariance}</strong> of the samples.
     */
    @Test
    void calculateCovariance() {
        assert (Math.abs(math.calculateCovariance() - 15.2273f) < 0.001f);
    }

    /**
     * Testing the <strong>{@link Mathematics#calculateCorrelation() correlation}</strong> of the samples.
     */
    @Test
    void calculateCorrelation() {
        assert (Math.abs(math.calculateCorrelation() - 0.9989f) < 0.001f);
    }
}