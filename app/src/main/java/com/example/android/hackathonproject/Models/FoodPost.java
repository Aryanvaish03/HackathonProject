package com.example.android.hackathonproject.Models;

import java.util.Date;

public class FoodPost {


    String postedBy, foodItems , feedCount, status;
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
