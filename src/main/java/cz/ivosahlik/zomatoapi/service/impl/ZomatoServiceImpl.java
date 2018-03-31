package cz.ivosahlik.zomatoapi.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ivosahlik.zomatoapi.constants.CacheConstants;
import cz.ivosahlik.zomatoapi.dto.ZomatoDailyMenuDto;
import cz.ivosahlik.zomatoapi.dto.ZomatoDailyMenuWrapperDto;
import cz.ivosahlik.zomatoapi.dto.ZomatoDishDto;
import cz.ivosahlik.zomatoapi.dto.ZomatoDishWrapperDto;
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
import sun.tools.jconsole.inspector.Utils;

import java.io.ByteArrayInputStream;
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


    /**
     * This method return list daily menus from zomato api
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Cacheable(value = CacheConstants.ZOMATO_CACHE_DAILY_MENUS_JSON_WEB, key = "#restaurantId.concat('-daily-menu-json')")
    public List<String> getZomatoDailyFromJsonWeb(String restaurantId) throws IOException {

        byte[] bytes = getDailyMenus(restaurantId).getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);

        return dailyMenuWrapper(inputStream);
    }


    /**
     * This method return list daily menus from zomato json file
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Cacheable(value = CacheConstants.ZOMATO_CACHE_DAILY_MENUS_JSON_FULL, key = "#restaurantId.concat('-daily-menu-json')")
    public List<String> getZomatoDailyFromJsonFull(String restaurantId) throws IOException {

        TypeReference<List<DailyMenus>> typeReference = new TypeReference<List<DailyMenus>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/dailyMenu1.json");

        return dailyMenuWrapper(inputStream);
    }


    /**
     * This method return list daily menus from zomato file
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Cacheable(value = CacheConstants.ZOMATO_CACHE_DAILY_MENUS_JSON, key = "#restaurantId.concat('-daily-menu-json')")
    public List<String> getZomatoDailyFromJsonFile(String restaurantId) throws IOException {

        List<String> list = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyMenus>> typeReference = new TypeReference<List<DailyMenus>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test.json");
        try {
            List<DailyMenus> dailyMenusList = mapper.readValue(inputStream,typeReference);

            for ( DailyMenus str : dailyMenusList) {
                DailyMenu dailyMenu = str.getDailyMenu();
                for (Dishes dishes : dailyMenu.getDishes()) {
                    Dish dish = dishes.getDish();
                    list.add(dish.getName() + " " + dish.getPrice());
                }
            }

        } catch (IOException e){
            log.error(e.getMessage());
        }
        return list;

    }


    /**
     * This method parse of json
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public List<String> dailyMenuWrapper(InputStream inputStream) throws IOException {

        List<String> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(inputStream).get(DAILY_MENUS_NODE_NAME);
        ZomatoDailyMenuWrapperDto[] dailyMenuWrappers = mapper.readValue(node.toString(), ZomatoDailyMenuWrapperDto[].class);

        ZomatoDailyMenuDto zomatoDailyMenuDto = dailyMenuWrappers[0].getDailyMenu();
        for (ZomatoDishWrapperDto zomatoDishWrapperDto : zomatoDailyMenuDto.getDishes()) {
            ZomatoDishDto data = zomatoDishWrapperDto.getDish();
            list.add(data.getName() + " " + data.getPrice());
        }

        return list;
    }

}
