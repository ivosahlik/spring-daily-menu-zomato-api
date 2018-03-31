package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ZomatoRestaurantWrapperDto {

    private ZomatoRestaurantDto restaurant;

    public ZomatoRestaurantDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ZomatoRestaurantDto restaurant) {
        this.restaurant = restaurant;
    }
}
