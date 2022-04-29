package com.teamfive.universitybazaarsystem;

import com.google.firebase.database.Exclude;

public class CartItems
{
    @Exclude
    private String key;
    private String ItemNameCT;
    private int ItemPriceCT;
    private String ItemSellerCT;
    private String itemSeller;
    private String itemName;
    private String itemPrice;
    public CartItems(){}

    public CartItems(String itemNameCT, int itemPriceCT, String itemSellerCT) {
        ItemNameCT = itemNameCT;
        ItemPriceCT = itemPriceCT;
        ItemSellerCT = itemSellerCT;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getItemNameCT() {
        return ItemNameCT;
    }

    public void setItemNameCT(String itemNameCT) {
        ItemNameCT = itemNameCT;
    }

    public int getItemPriceCT() {
        return ItemPriceCT;
    }

    public void setItemPriceCT(int itemPriceCT) {
        ItemPriceCT = itemPriceCT;
    }

    public String getItemSellerCT() {
        return ItemSellerCT;
    }

    public void setItemSellerCT(String itemSellerCT) {
        ItemSellerCT = itemSellerCT;
    }

}
