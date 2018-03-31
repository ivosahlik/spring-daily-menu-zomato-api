package cz.ivosahlik.zomatoapi.service;

import java.io.IOException;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
public interface ZomatoService {

    String getDailyMenus(String restaurantId);

//    ZomatoDailyMenuWrapperDto[] zomatoDailyMenuWrapperDtos(String restaurantId) throws IOException;

    List<String> getZomatoDailyFromJsonFile(String restaurantId) throws IOException;

    List<String> getZomatoDailyFromJsonFull(String restaurantId) throws IOException;

    List<String> getZomatoDailyFromJsonWeb(String restaurantId) throws IOException;

}
