package com.pyj.customview.bean;

public class PieChartData {
    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}