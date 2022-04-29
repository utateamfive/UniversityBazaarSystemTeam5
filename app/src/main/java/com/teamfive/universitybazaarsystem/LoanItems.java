package com.teamfive.universitybazaarsystem;

public class LoanItems
{
    private String ItemNameLN;
    private int Itemtime;
    private String ItemLoaner;
    private String key;
    public LoanItems(){}

    public LoanItems(String itemNameLN, int itemtime, String itemLoaner) {
        this.ItemNameLN = itemNameLN;
        this.Itemtime = itemtime;
        this.ItemLoaner = itemLoaner;
    }

    public String getItemNameLN() {
        return ItemNameLN;
    }

    public void setItemNameLN(String itemNameLN) {
        this.ItemNameLN = itemNameLN;
    }

    public int getItemtime() {
        return Itemtime;
    }

    public void setItemtime(int itemtime) {
        this.Itemtime = itemtime;
    }

    public String getItemLoaner() {
        return ItemLoaner;
    }

    public void setItemLoaner(String itemLoaner) {
        this.ItemLoaner = itemLoaner;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
