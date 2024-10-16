package com.skappsstore.text.repeater.Model;

public class HomeServiceModel {
    private String serviceTitle;
    private int serviceImg;

    public HomeServiceModel(String serviceTitle, int serviceImg) {
        this.serviceTitle = serviceTitle;
        this.serviceImg = serviceImg;
    }

    public HomeServiceModel() {
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public int getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(int serviceImg) {
        this.serviceImg = serviceImg;
    }
}



