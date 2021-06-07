package com.example.imageclassification;

public class HistoryDataModel {

    private int id;
    private String prediction,flag,correct_value,timestamp;

    public HistoryDataModel(int id, String prediction, String flag, String correct_value, String timestamp) {
        this.id = id;
        this.prediction = prediction;
        this.flag = flag;
        this.correct_value = correct_value;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCorrect_value() {
        return correct_value;
    }

    public void setCorrect_value(String correct_value) {
        this.correct_value = correct_value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
