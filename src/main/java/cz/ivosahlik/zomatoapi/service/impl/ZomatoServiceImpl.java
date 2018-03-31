package cz.ivosahlik.zomatoapi.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ivosahlik.zomatoapi.constants.Constants;
import cz.ivosahlik.zomatoapi.dto.*;
import cz.ivosahlik.zomatoapi.model.*;
import cz.ivosahlik.zomatoapi.service.ZomatoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@Slf4j
@Service
public class ZomatoServiceImpl implements ZomatoService {

    @Autowired
    private ZomatoDataRestServiceImpl zomatoDataRestService;

    /**
     * This method return list daily menus from zomato api
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Cacheable(value = Constants.ZOMATO_CACHE_DAILY_MENUS_JSON_WEB, key = "#restaurantId.concat('-daily-menu-json')")
    public List<String> getZomatoDailyFromJsonWeb(String restaurantId) throws IOException {

        byte[] bytes = zomatoDataRestService.getDailyMenus(restaurantId).getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);

        return getDailyMenuList(inputStream);
    }


    /**
     * This method return list daily menus from zomato json file
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Cacheable(value = Constants.ZOMATO_CACHE_DAILY_MENUS_JSON_FULL, key = "#restaurantId.concat('-daily-menu-json')")
    public List<String> getZomatoDailyFromJsonFull(String restaurantId) throws IOException {

        TypeReference<List<DailyMenus>> typeReference = new TypeReference<List<DailyMenus>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/dailyMenu1.json");

        return getDailyMenuList(inputStream);
    }


    /**
     * This method return list daily menus from zomato file
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Cacheable(value = Constants.ZOMATO_CACHE_DAILY_MENUS_JSON, key = "#restaurantId.concat('-daily-menu-json')")
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
     * This method parse of restaurant json
     *
     * @param restaurantId
     * @return
     * @throws IOException
     */
    public Map<String, String> getZomatoRestaurantFromJsonFile(String restaurantId) throws IOException {

        final String path = "src/main/resources/json/restaurantDetail.json";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(new File(path));

        Map<String,String> map = mapper.readValue(new FileInputStream(path),Map.class);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            map.put(entry.getKey().toString(), String.valueOf(entry.getValue()));
        }

        JsonNode locationJson = rootNode.get("location");
        Map<String, String> locationMap = mapper.convertValue(locationJson, Map.class);
        for (Map.Entry<String, String> entry : locationMap.entrySet()) {
            map.put(entry.getKey().toString(), String.valueOf(entry.getValue()));
        }

        return map;
    }


    /**
     * This method parse of json
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public List<String> getDailyMenuList(InputStream inputStream) throws IOException {

        List<String> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(inputStream).get(Constants.DAILY_MENUS_NODE_NAME);
        ZomatoDailyMenuWrapperDto[] dailyMenuWrappers = mapper.readValue(node.toString(), ZomatoDailyMenuWrapperDto[].class);

        ZomatoDailyMenuDto zomatoDailyMenuDto = dailyMenuWrappers[0].getDailyMenu();
        for (ZomatoDishWrapperDto zomatoDishWrapperDto : zomatoDailyMenuDto.getDishes()) {
            ZomatoDishDto data = zomatoDishWrapperDto.getDish();
            list.add(data.getName() + (data.getPrice().length() == 0 ? "" : ", ") + data.getPrice());
        }


        return list;
    }

}
