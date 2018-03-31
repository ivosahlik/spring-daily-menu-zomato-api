package cz.ivosahlik.zomatoapi.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
public interface ZomatoService {

    List<String> getZomatoDailyFromJsonFile(String restaurantId) throws IOException;

    List<String> getZomatoDailyFromJsonFull(String restaurantId) throws IOException;

    List<String> getZomatoDailyFromJsonWeb(String restaurantId) throws IOException;

    Map<String, String> getZomatoRestaurantFromJsonFile(String restaurantId) throws IOException;

}
