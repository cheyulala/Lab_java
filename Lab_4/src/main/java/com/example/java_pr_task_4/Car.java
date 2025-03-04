package com.example.java_pr_task_4;

// наш класс, с которым мы будем работать (машины)
public class Car {
    private String brand ;
    private int year_production;
    private String model_name;
    private String status;
    //конструктор
    public Car(String brand, int year_production, String model_name, String status) {
        this.brand = brand;
        this.year_production = year_production;
        this.model_name = model_name;
        this.status = status;
    }
    //геттеры
    public String getBrand() { return brand; }
    public int getYear_production() { return year_production; }
    public String getModel_name() { return model_name; }
    public String getStatus() { return status; }
}
