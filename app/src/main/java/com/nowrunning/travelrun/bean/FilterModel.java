package com.nowrunning.travelrun.bean;

public class FilterModel {

    public FilterModel(String name,int productCount,boolean selected)
    {
        this.productCount=productCount;
        this.name=name;
        this.selected=selected;
    }

    private int productCount;
    private String name;
    private boolean selected;


    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
