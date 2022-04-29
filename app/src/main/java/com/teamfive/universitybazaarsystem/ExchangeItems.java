package com.teamfive.universitybazaarsystem;

public class ExchangeItems {
    private String ItemNameEX;
    private String Itemtrade;
    private String Itemtrader;
    private String key;
    public ExchangeItems(){}

    public ExchangeItems(String itemName, String itemExchange, String itemSeller) {
        this.ItemNameEX = itemName;
        this.Itemtrade = itemExchange;
        this.Itemtrader = itemSeller;
    }

    public String getItemNameEX() {
        return ItemNameEX;
    }

    public void setItemNameEX(String itemName) {
        ItemNameEX = itemName;
    }

    public String getItemtrade()
    {
        return Itemtrade;
    }

    public void setItemtrade(String itemExchange) {
        Itemtrade = itemExchange;
    }

    public String getItemtrader() {
        return Itemtrader;
    }

    public void setItemtrader(String itemSeller) {
        Itemtrader = itemSeller;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
