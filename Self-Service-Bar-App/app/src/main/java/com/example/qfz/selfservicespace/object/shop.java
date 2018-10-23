package com.example.qfz.selfservicespace.object;

import java.io.Serializable;

public class shop implements Serializable {
    private String shopID,
                   shopName,
                   shopPhone,
                   shopDistance,
                   shopAddress,
                   shopWeekdayAm,
                   shopWeekdayPm,
                   shopWeekendAm,
                   shopWeekendPm;

    public shop(String shopID, String shopName, String shopPhone, String shopDistance, String shopAddress, String shopWeekdayAm, String shopWeekdayPm, String shopWeekendAm, String shopWeekendPm) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopDistance = shopDistance;
        this.shopAddress = shopAddress;
        this.shopWeekdayAm = shopWeekdayAm;
        this.shopWeekdayPm = shopWeekdayPm;
        this.shopWeekendAm = shopWeekendAm;
        this.shopWeekendPm = shopWeekendPm;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopDistance() {
        return shopDistance;
    }

    public void setShopDistance(String shopDistance) {
        this.shopDistance = shopDistance;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopWeekdayAm() {
        return shopWeekdayAm;
    }

    public void setShopWeekdayAm(String shopWeekdayAm) {
        this.shopWeekdayAm = shopWeekdayAm;
    }

    public String getShopWeekdayPm() {
        return shopWeekdayPm;
    }

    public void setShopWeekdayPm(String shopWeekdayPm) {
        this.shopWeekdayPm = shopWeekdayPm;
    }

    public String getShopWeekendAm() {
        return shopWeekendAm;
    }

    public void setShopWeekendAm(String shopWeekendAm) {
        this.shopWeekendAm = shopWeekendAm;
    }

    public String getShopWeekendPm() {
        return shopWeekendPm;
    }

    public void setShopWeekendPm(String shopWeekendPm) {
        this.shopWeekendPm = shopWeekendPm;
    }
}
