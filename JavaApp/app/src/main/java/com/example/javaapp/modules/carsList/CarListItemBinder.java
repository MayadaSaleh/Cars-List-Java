package com.example.javaapp.modules.carsList;

public class CarListItemBinder {
    private final String brand;
    private final String isUsed;
    private final String image;

    CarListItemBinder(String brand, String isUsed, String image) {
        this.brand = brand;
        this.isUsed = isUsed;
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public String getImage() {
        return image;
    }
}
