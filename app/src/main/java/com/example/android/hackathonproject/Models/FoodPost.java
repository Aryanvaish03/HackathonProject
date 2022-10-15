package com.example.android.hackathonproject.Models;

import java.util.Date;

public class FoodPost {


    String postedBy;
    String foodItems;
    String feedCount;
    String status;
    String acceptedBy;
    String employeeName;

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    String employeeNumber;
    Date date;

    public FoodPost(){}

    public FoodPost(String foodItems, String feedCount, String status, Date date) {
        this.foodItems = foodItems;
        this.feedCount = feedCount;
        this.status = status;
        this.date = date;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String foodItems) {
        this.foodItems = foodItems;
    }

    public String getFeedCount() {
        return feedCount;
    }

    public void setFeedCount(String feedCount) {
        this.feedCount = feedCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
