package com.miguelcr.customlistviewfruits;

/**
 * Created by miguelcampos on 6/6/16.
 */
public class Fruit {
    int icon;
    String name;
    float price;

    public Fruit(int icon, String name, float price) {
        this.icon = icon;
        this.name = name;
        this.price = price;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
