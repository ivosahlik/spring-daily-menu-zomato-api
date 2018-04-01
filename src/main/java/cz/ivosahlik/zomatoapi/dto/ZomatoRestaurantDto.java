package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ZomatoRestaurantDto{

    /**
     * Restaurant ID (api element: res_id)
     */
    @JsonProperty("id")
    private int id;

    /**
     * Restaurant name (api element: name)
     */
    @JsonProperty("name")
    private String name;

    /**
     * Restaurant url (api element: url)
     */
    @JsonProperty("url")
    private String url;

    /**
     * Menu url (api element: menu_url)
     */
    @JsonProperty("menu_url")
    private String menuUrl;

    /**
     * Daily menus of the restaurant (api element: daily_menus)
     */
    private List<ZomatoDailyMenuDto> dailyMenus = new ArrayList<>();



    public ZomatoRestaurantDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<ZomatoDailyMenuDto> getDailyMenus() {
        return this.dailyMenus;
    }

    public void addDailyMenu(ZomatoDailyMenuDto dailyMenuEntity) {
        this.dailyMenus.add(dailyMenuEntity);
    }

}
