package com.example.androidproject;

public class userhelper {
    String  Item;
    int quantity;
    String price;
    String regn;



    public userhelper( String item, int quantity, String price) {

        this.Item = item;
        this.quantity = quantity;
        this.price = price;
        this.regn=regn;
    }


    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}