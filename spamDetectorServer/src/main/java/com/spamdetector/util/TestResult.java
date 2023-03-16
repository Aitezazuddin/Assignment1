package com.spamdetector.util;

public class TestResult {
    private String filePath;
    private String actualLabel;
    private String predictedLabel;
    
    public TestResult(String filePath, String actualLabel, String predictedLabel) {
        this.filePath = filePath;
        this.actualLabel = actualLabel;
        this.predictedLabel = predictedLabel;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getActualLabel() {
        return actualLabel;
    }

    public String getPredictedLabel() {
        return predictedLabel;
    }
}

