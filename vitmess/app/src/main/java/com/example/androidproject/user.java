package com.example.androidproject;

public class user {
     String item;
     int quantity;
     String price;

    public user(String item, int quantity, String price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }
    public user(){}
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
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
