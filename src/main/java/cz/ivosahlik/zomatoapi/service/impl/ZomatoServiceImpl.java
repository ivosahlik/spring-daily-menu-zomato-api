package cz.ivosahlik.zomatoapi.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ivosahlik.zomatoapi.constants.CacheConstants;
import cz.ivosahlik.zomatoapi.model.*;
import cz.ivosahlik.zomatoapi.service.ZomatoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@Slf4j
@Service
public class ZomatoServiceImpl implements ZomatoService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @Value("${rest.api.menu.url}")
    private String dailyMenusByRestaurantIdUrl;

    private static final String ACCEPT_HEADER_NAME = "Accept";
    private static final String USER_KEY_HEADER_NAME = "user_key";
    private static final String REST_API_PARAM_ID = "id";

    private static final String DAILY_MENUS_NODE_NAME = "daily_menus";


    /**
     * This method return JSON String
     *
     * @param restaurantId
     * @return String
     */
    @Cacheable(value = CacheConstants.ZOMATO_CACHE_DAILY_MENUS, key = "#restaurantId.concat('-daily-menu')")
    public String getDailyMenus(String restaurantId) {

        Map<String, String> dailyMenusParams = new HashMap<>();
        dailyMenusParams.put(REST_API_PARAM_ID, restaurantId);

        HttpEntity<?> entity = getHttpEntityForRestRequest();

        String dailyMenusJSONString = null;

        try {
            log.info("dailyMenusParams {} " +  dailyMenusParams);
            dailyMenusJSONString = restTemplate.postForObject(dailyMenusByRestaurantIdUrl, entity, String.class, dailyMenusParams);
        } catch (HttpClientErrorException ex) {
            log.info("Restaurant with ID: {0} has 0 daily menus", restaurantId);
        }

        return dailyMenusJSONString;
    }


    /**
     *  This method return headers key and value
     *
     * @return HttpEntity
     */
    private HttpEntity<?> getHttpEntityForRestRequest() {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCEPT_HEADER_NAME, MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add(USER_KEY_HEADER_NAME, apiKey);

        return new HttpEntity<>(map, headers);

    }

    @Cacheable(value = CacheConstants.ZOMATO_CACHE_DAILY_MENUS_JSON, key = "#restaurantId.concat('-daily-menu-json')")
    public List<String> getZomatoDailyFromJsonFile(String restaurantId) throws IOException {

        List<String> list = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyMenus>> typeReference = new TypeReference<List<DailyMenus>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test.json");
        try {
            List<DailyMenus> users = mapper.readValue(inputStream,typeReference);

            for ( DailyMenus str : users) {
                DailyMenu dailyMenu = str.getDailyMenu();
                log.debug(String.valueOf(dailyMenu));
                for (Dishes dishes : dailyMenu.getDishes()) {
                    Dish dish = dishes.getDish();
                    log.debug(dish.getName() + " " + dish.getPrice());
                    list.add(dish.getName() + " " + dish.getPrice());
                }
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return list;

    }

}
