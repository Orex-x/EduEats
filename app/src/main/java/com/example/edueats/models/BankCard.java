package com.example.edueats.models;

public class BankCard {
    private int id;
    private String FIO;
    private String number;
    private String Date;
    private String CVC;

    public BankCard() {
    }

    public BankCard(int id, String FIO, String number, String date, String CVC) {
        this.id = id;
        this.FIO = FIO;
        this.number = number;
        Date = date;
        this.CVC = CVC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }
}
