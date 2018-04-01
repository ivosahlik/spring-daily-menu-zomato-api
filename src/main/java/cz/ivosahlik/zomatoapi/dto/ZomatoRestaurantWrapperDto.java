package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ZomatoRestaurantWrapperDto {

    @JsonProperty("restaurant")
    private ZomatoRestaurantDto restaurant;

    public ZomatoRestaurantDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ZomatoRestaurantDto restaurant) {
        this.restaurant = restaurant;
    }
}
