package com.teamfive.universitybazaarsystem;

import com.google.firebase.database.Exclude;

public class SalesItems
{
    @Exclude
    private String key;
    private String ItemName;
    private int ItemPrice;
    private String ItemSeller;
    public SalesItems(){}
    public SalesItems(String ItemName, int ItemPrice, String ItemSeller)
    {
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.ItemSeller = ItemSeller;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemSeller() {
        return ItemSeller;
    }

    public void setItemSeller(String itemSeller) {
        ItemSeller = itemSeller;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
