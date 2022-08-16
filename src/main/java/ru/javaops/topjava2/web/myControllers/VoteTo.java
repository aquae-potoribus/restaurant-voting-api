package ru.javaops.topjava2.web.myControllers;

import lombok.ToString;

@ToString
public class VoteTo {

    private Integer restaurantId;
    private Integer userId;

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        userId = userId;
    }

    public VoteTo() {
    }
}
