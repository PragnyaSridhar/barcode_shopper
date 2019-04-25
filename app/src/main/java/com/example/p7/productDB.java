package com.example.p7;

public class productDB {

    public static final String TABLE_NAME = "products";
    public static final String COLUMN_BARCODE = "barcode";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";


    private String barcode;
    private String name;
    private Double price;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_BARCODE + " STRING PRIMARY KEY,"
                    + COLUMN_NAME + " STRING,"
                    + COLUMN_PRICE + " INTEGER"
                    + ")";
    public productsDB() {
    }


    public productDB(String barcode, String name, Double price) {
        this.barcode = barcode;
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

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
