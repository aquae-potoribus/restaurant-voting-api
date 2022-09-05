package ru.javaops.topjava2.to;

public class MenuTo {

    private Integer id;
    private Integer restaurantId;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MenuTo(Integer id, Integer restaurantId, Integer userId) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }
}
