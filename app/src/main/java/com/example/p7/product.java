package com.example.p7;

public class product {
    String barcode;
    String name;
    Double price;

    public product(){

    }

    public product(String barcode,String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
