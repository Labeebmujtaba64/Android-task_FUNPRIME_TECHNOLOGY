package com.labeeb.funprimetechnology;

public class Home_Model {

    String id;
    String recyclerImage1;


    public Home_Model(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRecyclerImage1() {
        return Integer.parseInt(recyclerImage1);
    }

    public void setRecyclerImage1(String recyclerImage1) {
        this.recyclerImage1 = recyclerImage1;
    }

}
