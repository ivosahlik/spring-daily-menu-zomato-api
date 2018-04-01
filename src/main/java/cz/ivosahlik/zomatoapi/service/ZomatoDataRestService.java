package cz.ivosahlik.zomatoapi.service;

/**
 * Intellij Idea
 * Created by ivosahlik on 31/03/2018
 */
public interface ZomatoDataRestService {

    String getDailyMenus(String restaurantId);

    String getNearbyRestaurants(String latitude, String longtitude);

}
