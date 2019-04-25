package com.example.p7;

public class product {
    String name;
    Double price;

    public product(){

    }

    public product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
