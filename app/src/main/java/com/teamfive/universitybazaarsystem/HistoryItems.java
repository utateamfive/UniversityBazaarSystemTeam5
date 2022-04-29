package com.teamfive.universitybazaarsystem;

import com.google.firebase.database.Exclude;

public class HistoryItems {
    @Exclude
    private String key;
    private String ItemNameH;
    private String ItemValue;
    private String ItemSellerH;
    public HistoryItems(){}
    public HistoryItems(String itemNameH, String itemValue, String itemSellerH) {
        ItemNameH = itemNameH;
        ItemValue = itemValue;
        ItemSellerH = itemSellerH;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getItemNameH() {
        return ItemNameH;
    }

    public void setItemNameH(String itemNameH) {
        ItemNameH = itemNameH;
    }

    public String getItemValue() {
        return ItemValue;
    }

    public void setItemValue(String itemValue) {
        ItemValue = itemValue;
    }

    public String getItemSellerH() {
        return ItemSellerH;
    }

    public void setItemSellerH(String itemSellerH) {
        ItemSellerH = itemSellerH;
    }
}
